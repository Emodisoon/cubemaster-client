package com.MitolGames.cubemaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.MitolGames.cubemaster.models.User;
import com.MitolGames.cubemaster.util.CubeView;
import com.MitolGames.cubemaster.util.ScrambleGenerator;
import com.auth0.android.jwt.JWT;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SharedPreferences sPrefs;

    //Датчик
    private static final int SENSOR_SENSITIVITY = 1;
    private SensorManager sm;
    private Sensor proxSensor;

    private enum States{
        NoCube,
        CubePlaced,
        CubeTaken;
    }

    private ScrambleGenerator sgr;

    States state;

    CubeView cubeView;

    Button go_to_rest_btn;
    Button deleteAuth_btn;

    Button startBtn;
    Button stopBtn;

    private TextView tv_timer;
    private TextView tv_scrable;

    //Секундомер
    private Handler customHandler = new Handler();
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;

    private String username;
    private String userID;
    private String scrambleString;
    String JWToken;
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username_tv = findViewById(R.id.userName_tv);
        tv_timer = findViewById(R.id.tv_timer);
        tv_scrable = findViewById(R.id.scrable_tv);
        deleteAuth_btn = findViewById(R.id.delete_auth_btn);
        go_to_rest_btn = findViewById(R.id.go_to_rest_btn2);
        startBtn = findViewById(R.id.button_start);
        stopBtn = findViewById(R.id.button_stop);
        cubeView = findViewById(R.id.cubeView);

        stopBtn.setEnabled(false);
        state = States.NoCube;
        sgr = new ScrambleGenerator();



        //Переход на страницу дебага запросов
        go_to_rest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), RestActivity.class);
                //startActivity(intent);

                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) cubeView.getLayoutParams(); // получаем параметры
                int MyHeight = 400; // желаемая высота, будет меняться по условиям
                params.height = params.height+100; // меняем высоту. Если уползёт выравнивание, то imageView.getLayoutParams().width = MyHeight;
                cubeView.setLayoutParams(params); // меняем параметр
                cubeView.invalidate();
            }
        });

        //Проверка наличия JWT
        sPrefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        JWToken = sPrefs.getString("JWT","notFound");

        //В случае отсутствия JWT переход на страницу авторизации
        assert JWToken != null;
        boolean JWTNotexists = JWToken.equals("notFound");
        if(JWTNotexists){
            Log.e("Not found jwt", "!!!");
            //TODO: Сделать предложение авторизации
        }
        else{//Получение данных из JWT токена
            JWT jwt = new JWT(JWToken.substring(7, JWToken.length()));
            username = jwt.getSubject();
            username_tv.setText("Добро пожаловать, " + username);
            //Получение информации о пользователе
            getUserInformation g = new getUserInformation();
            g.execute();
        }

        //Показания с датчика
         sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
         proxSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
         sm.registerListener(this,proxSensor, sm.SENSOR_DELAY_NORMAL);
         //TODO: Перед билдом включить опрос датчика
         //Секундомер
         startBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                    StartTimer();

            }
         });

         stopBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               StopTimer();
               state=States.NoCube;
            }
         });

        //Сброс аутентификацияя
        deleteAuth_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = sPrefs.edit();
                ed.putString("JWT", "notFound");
                ed.apply();
                Intent authIntent = new Intent(getApplicationContext(), LoginActivity.class);
                Log.e("before checkong jwt: ", sPrefs.getString("JWT","notFound"));
                startActivity(authIntent);
            }
        });

        //Генерация скрамбла

        genNewScr();

    }


    //ГенерацияСкрамбла и обновление текст вью
    private void genNewScr(){
        scrambleString = sgr.GenerateScramble();
        tv_scrable.setText(scrambleString);
    }

    //Лисенер показаний датчика
    @Override
    public void onSensorChanged(SensorEvent event) {

            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                    //near
                    Toast.makeText(getApplicationContext(), "near " + event.values[0], Toast.LENGTH_SHORT).show();

                    if(state==States.NoCube) {
                        state = States.CubePlaced;
                        tv_timer.setTextColor(Color.GREEN);
                    }

                } else {
                    //far
                    Toast.makeText(getApplicationContext(), "far", Toast.LENGTH_SHORT).show();

                    if(state==States.CubePlaced) {
                        state=States.CubeTaken;
                        StartTimer();
                        tv_timer.setTextColor(Color.BLACK);
                    }
                }
            }


    }

    private void StartTimer(){
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updaterTimerThread, 0);
        sm.unregisterListener(this);
        //Не даем экрану гаснуть
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        startBtn.setEnabled(false);
        stopBtn.setEnabled(true);

    }

    private void StopTimer(){
        startTime = 0L;
        customHandler.removeCallbacks(updaterTimerThread);
        sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //даем экрану гаснуть
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        startBtn.setEnabled(true);
        stopBtn.setEnabled(false);

        //Отправка данных на сервеер
        String req = "{ \"scramble\": \""+ scrambleString +"\", \"time\": \""+updatedTime+"\", \"userID\": \""+userID+"\"}";
        sendTimeRecord str = new sendTimeRecord();
        str.execute(req);
        genNewScr();
    }


    //Отправка данных о сборке
    class sendTimeRecord extends  AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {

            String url = "http://192.168.0.12:8080/api/timeRecord";

            //Adding JWT token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("AUTHORIZATION", JWToken );

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            ResponseEntity<String> response;
            response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(strings[0], headers), String.class);
            return null;
        }

    }

    //Получение данных о пользователе
    class getUserInformation extends AsyncTask<Void, Void, User>{
        @Override
        protected User doInBackground(Void... voids) {
            String url = "http://192.168.0.12:8080/api/user/"+username;

            //Adding JWT token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("AUTHORIZATION", JWToken );

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<User> response;
            try {
                response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), User.class);
                return response.getBody();
            }catch (RuntimeException e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            if(user != null)
            userID = user.getId();
        }
    }

    //Поток для таймера
    private Runnable updaterTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            tv_timer.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);

        }
    };

    //Не используется, нужен для реализации SensorEventListener
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}