package com.MitolGames.cubemaster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.MitolGames.cubemaster.models.TimeRecord;
import com.MitolGames.cubemaster.models.User;
import com.MitolGames.cubemaster.util.GlobalConstants;
import com.MitolGames.cubemaster.util.ScrambleGenerator;
import com.MitolGames.cubemaster.util.Turn;
import com.auth0.android.jwt.JWT;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


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

    private ImageView tile0_0;
    private ImageView tile0_1;
    private ImageView tile0_2;
    private ImageView tile0_3;
    private ImageView tile0_4;
    private ImageView tile0_5;
    private ImageView tile0_6;
    private ImageView tile0_7;
    private ImageView tile0_8;
    private ImageView tile0_9;
    private ImageView tile0_10;
    private ImageView tile0_11;
    private ImageView tile0_12;
    private ImageView tile0_13;
    private ImageView tile0_14;
    private ImageView tile0_15;
    private ImageView tile0_16;
    private ImageView tile0_17;

    private ImageView tile1_0;
    private ImageView tile1_1;
    private ImageView tile1_2;
    private ImageView tile1_3;
    private ImageView tile1_4;
    private ImageView tile1_5;
    private ImageView tile1_6;
    private ImageView tile1_7;
    private ImageView tile1_8;
    private ImageView tile1_9;
    private ImageView tile1_10;
    private ImageView tile1_11;
    private ImageView tile1_12;
    private ImageView tile1_13;
    private ImageView tile1_14;
    private ImageView tile1_15;
    private ImageView tile1_16;
    private ImageView tile1_17;

    private ImageView tile2_0;
    private ImageView tile2_1;
    private ImageView tile2_2;
    private ImageView tile2_3;
    private ImageView tile2_4;
    private ImageView tile2_5;
    private ImageView tile2_6;
    private ImageView tile2_7;
    private ImageView tile2_8;
    private ImageView tile2_9;
    private ImageView tile2_10;
    private ImageView tile2_11;
    private ImageView tile2_12;
    private ImageView tile2_13;
    private ImageView tile2_14;
    private ImageView tile2_15;
    private ImageView tile2_16;
    private ImageView tile2_17;

    LinearLayout settings_layout;
    androidx.constraintlayout.widget.ConstraintLayout error_layout;

    Button confirmErrorBtn;
    Button authBtn;
    Button stopBtn;
    Button toolBarBtn;
    Button recordsBtn;
    Button settingsBtn;

    private TextView tv_timer;
    private TextView tv_scrable;

    private Boolean connected = false;
    private Boolean authenticated = false;

    //Секундомер
    private Handler customHandler = new Handler();
    private long startTime = 0L;
    private long updatedTime = 0L;

    TextView bestTv;
    TextView avg5Tv;
    TextView avg12Tv;
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
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,proxSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void setTypeFaces(Typeface face){
        TextView untv = findViewById(R.id.userName_tv);
        TextView phtv = findViewById(R.id.placeHere_tv);
        TextView tv4 = findViewById(R.id.textView4);
        avg12Tv = findViewById(R.id.avg12_tv);
        bestTv = findViewById(R.id.best_Tv);
        avg5Tv = findViewById(R.id.avg5_tv);
        untv.setTypeface(face);
        phtv.setTypeface(face);
        avg12Tv.setTypeface(face);
        bestTv.setTypeface(face);
        avg5Tv.setTypeface(face);
        tv4.setTypeface(face);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface face = Typeface.createFromAsset(getAssets(), GlobalConstants.mainFontPath);

        tv_timer = findViewById(R.id.tv_timer);
        tv_timer.setTypeface(face);
        tv_scrable = findViewById(R.id.scrable_tv);
        tv_scrable.setTypeface(face);
        settings_layout = findViewById(R.id.settings_layout);
        authBtn = findViewById(R.id.auth_btn);
        authBtn.setTypeface(face);
        stopBtn = findViewById(R.id.button_stop);
        recordsBtn = findViewById(R.id.records_btn);
        recordsBtn.setTypeface(face);
        confirmErrorBtn = findViewById(R.id.confirmErrorBtn);
        confirmErrorBtn.setTypeface(face);
        error_layout = findViewById(R.id.errorConnectionAlert);
        toolBarBtn = findViewById(R.id.toolbar_btn);
        settingsBtn = findViewById(R.id.settings_btn);
        settingsBtn.setTypeface(face);
        initializeTiles();

        setTypeFaces(face);

        stopBtn.setEnabled(false);
        state = States.NoCube;
        sgr = new ScrambleGenerator();

        //Проверка соединения с сервером
        CheckConnection ch = new CheckConnection();
        ch.execute();

        //Проверка наличия JWT
        sPrefs = getSharedPreferences("MyPref", MODE_PRIVATE);


        //Показания с датчика
         sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
         proxSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
         sm.registerListener(this,proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
         //Секундомер

         stopBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               StopTimer();
               state=States.NoCube;
            }
         });

         recordsBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), recordsActivity.class);
                 startActivity(intent);
             }
         });

        //Сброс аутентификацияя
        authBtn.setOnClickListener(new View.OnClickListener() {
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

        toolBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settings_layout.getVisibility()==View.GONE)
                    settings_layout.setVisibility(View.VISIBLE);
                else
                    settings_layout.setVisibility(View.GONE);
            }
        });

        confirmErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error_layout.setVisibility(View.GONE);
            }
        });


        //Генерация скрамбла

        genNewScr();


    }

    public void initializeTiles(){
        tile0_0 = findViewById(R.id.cubeview_0_0);

        tile0_1=findViewById(R.id.cubeview_0_1);
        tile0_2=findViewById(R.id.cubeview_0_2);
        tile0_3=findViewById(R.id.cubeview_0_3);
        tile0_4=findViewById(R.id.cubeview_0_4);
        tile0_5=findViewById(R.id.cubeview_0_5);
        tile0_6=findViewById(R.id.cubeview_0_6);
        tile0_7=findViewById(R.id.cubeview_0_7);
        tile0_8=findViewById(R.id.cubeview_0_8);
        tile0_9=findViewById(R.id.cubeview_0_9);
        tile0_10=findViewById(R.id.cubeview_0_10);
        tile0_11=findViewById(R.id.cubeview_0_11);
        tile0_12=findViewById(R.id.cubeview_0_12);
        tile0_13=findViewById(R.id.cubeview_0_13);
        tile0_14=findViewById(R.id.cubeview_0_14);
        tile0_15=findViewById(R.id.cubeview_0_15);
        tile0_16=findViewById(R.id.cubeview_0_16);
        tile0_17=findViewById(R.id.cubeview_0_17);
        tile1_0=findViewById(R.id.cubeview_1_0);
        tile1_1=findViewById(R.id.cubeview_1_1);
        tile1_2=findViewById(R.id.cubeview_1_2);
        tile1_3=findViewById(R.id.cubeview_1_3);
        tile1_4=findViewById(R.id.cubeview_1_4);
        tile1_5=findViewById(R.id.cubeview_1_5);
        tile1_6=findViewById(R.id.cubeview_1_6);
        tile1_7=findViewById(R.id.cubeview_1_7);
        tile1_8=findViewById(R.id.cubeview_1_8);
        tile1_9=findViewById(R.id.cubeview_1_9);
        tile1_10=findViewById(R.id.cubeview_1_10);
        tile1_11=findViewById(R.id.cubeview_1_11);
        tile1_12=findViewById(R.id.cubeview_1_12);
        tile1_13=findViewById(R.id.cubeview_1_13);
        tile1_14=findViewById(R.id.cubeview_1_14);
        tile1_15=findViewById(R.id.cubeview_1_15);
        tile1_16=findViewById(R.id.cubeview_1_16);
        tile1_17=findViewById(R.id.cubeview_1_17);
        tile2_0=findViewById(R.id.cubeview_2_0);
        tile2_1=findViewById(R.id.cubeview_2_1);
        tile2_2=findViewById(R.id.cubeview_2_2);
        tile2_3=findViewById(R.id.cubeview_2_3);
        tile2_4=findViewById(R.id.cubeview_2_4);
        tile2_5=findViewById(R.id.cubeview_2_5);
        tile2_6=findViewById(R.id.cubeview_2_6);
        tile2_7=findViewById(R.id.cubeview_2_7);
        tile2_8=findViewById(R.id.cubeview_2_8);
        tile2_9=findViewById(R.id.cubeview_2_9);
        tile2_10=findViewById(R.id.cubeview_2_10);
        tile2_11=findViewById(R.id.cubeview_2_11);
        tile2_12=findViewById(R.id.cubeview_2_12);
        tile2_13=findViewById(R.id.cubeview_2_13);
        tile2_14=findViewById(R.id.cubeview_2_14);
        tile2_15=findViewById(R.id.cubeview_2_15);
        tile2_16=findViewById(R.id.cubeview_2_16);
        tile2_17=findViewById(R.id.cubeview_2_17);
    }

    //ГенерацияСкрамбла и обновление текст вью
    private void genNewScr(){
        scrambleString = sgr.GenerateScramble();
        tv_scrable.setText(scrambleString);
        ApplyScramble(sgr.getScramble());
    }

    //Лисенер показаний датчика
    @Override
    public void onSensorChanged(SensorEvent event) {

            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                    //near

                    if(state==States.NoCube) {
                        state = States.CubePlaced;
                        tv_timer.setTextColor(Color.GREEN);
                    }

                } else {
                    //far

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
        stopBtn.setEnabled(true);
        stopBtn.setVisibility(View.VISIBLE);
    }

    private void StopTimer(){
        startTime = 0L;
        customHandler.removeCallbacks(updaterTimerThread);
        sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //даем экрану гаснуть
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stopBtn.setEnabled(false);
        stopBtn.setVisibility(View.GONE);

        //Отправка данных на сервеер
        if(connected && authenticated) {
            String req = "{ \"scramble\": \""+ scrambleString +"\", \"time\": \""+updatedTime+"\", \"userID\": \""+userID+"\"}";
            Log.e("user id", userID);
            sendTimeRecord str = new sendTimeRecord();
            str.execute(req);
        }
        genNewScr();

    }

    @SuppressLint("StaticFieldLeak")
    class getTimeRecords extends AsyncTask<Void, Void, ResponseEntity<String>> {
        @Override
        protected ResponseEntity<String> doInBackground(Void... params) {
            String url = GlobalConstants.IP+"/api/GetPersonalRecords/"+username;

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("AUTHORIZATION", JWToken);

            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            ResponseEntity<String> response;


            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers),String.class);


            return response;
        }

        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(ResponseEntity<String> result) {
            super.onPostExecute(result);

            ObjectMapper mapper = new ObjectMapper();

            List<TimeRecord> timeRecords;



            try{
                timeRecords = mapper.readValue(result.getBody(), new TypeReference<List<TimeRecord>>(){});

                Collections.sort(timeRecords, new Comparator<TimeRecord>() {
                    public int compare(TimeRecord o1, TimeRecord o2) {
                        return o2.getCreationDate().compareTo(o1.getCreationDate());
                    }
                });
                //Ищем лучшее
                long minTime = 99999999L;
                for(TimeRecord tr: timeRecords) {
                    if(tr.getTime()<minTime)
                        minTime = tr.getTime();
                }

                int secs = (int) (minTime/ 1000);
                int mins = secs / 60;
                secs = secs % 60;
                int milliseconds = (int) (minTime % 1000);
                bestTv.setText("BEST: "+ mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
                //Считаем avg5
                long tempmin = 99999999999L;
                long tempMax = 0;
                for(int i=0;i<5;i++){
                    if(timeRecords.get(i).getTime()<tempmin)
                        tempmin=timeRecords.get(i).getTime();
                    if(timeRecords.get(i).getTime()>tempMax)
                        tempMax=timeRecords.get(i).getTime();
                }
                long tempsum = 0;
                for(int i=0;i<5;i++){
                    if(timeRecords.get(i).getTime()==tempmin || timeRecords.get(i).getTime()==tempMax) {
                    }
                    else
                        tempsum+=timeRecords.get(i).getTime();
                }
                tempsum=tempsum/3;
                secs = (int) (tempsum/ 1000);
                mins = secs / 60;
                secs = secs % 60;
                milliseconds = (int) (tempsum % 1000);
                avg5Tv.setText("AVG5: "+ mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));

                tempmin = 99999999999L;
                tempMax = 0;
                for(int i=0;i<12;i++){
                    if(timeRecords.get(i).getTime()<tempmin)
                        tempmin=timeRecords.get(i).getTime();
                    if(timeRecords.get(i).getTime()>tempMax)
                        tempMax=timeRecords.get(i).getTime();
                }
                tempsum = 0;
                for(int i=0;i<12;i++){
                    if(timeRecords.get(i).getTime()==tempmin || timeRecords.get(i).getTime()==tempMax) {
                    }
                    else
                        tempsum+=timeRecords.get(i).getTime();
                }
                tempsum=tempsum/10;
                secs = (int) (tempsum/ 1000);
                mins = secs / 60;
                secs = secs % 60;
                milliseconds = (int) (tempsum % 1000);
                avg12Tv.setText("AVG12: "+ mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));

            } catch (IOException e){
                e.printStackTrace();
            }
        }


    }


    //Отправка данных о сборке
    @SuppressLint("StaticFieldLeak")
    class sendTimeRecord extends  AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {

            String url = GlobalConstants.IP + "/api/timeRecord";

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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getTimeRecords gtr = new getTimeRecords();
            gtr.execute();
        }
    }
    //TODO Понадобится позже для получения данных о сборке, затем убрать
    //Получение данных о пользователе
    @SuppressLint("StaticFieldLeak")
    class getUserInformation extends AsyncTask<Void, Void, User>{
        @Override
        protected User doInBackground(Void... voids) {
            String url = GlobalConstants.IP + "/api/user/"+username;

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
            if(user == null){
                Log.e("usr", "not found");
            }
        }
    }

    //Поток для таймера
    private Runnable updaterTimerThread = new Runnable() {
        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        @Override
        public void run() {
            long timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            long timeSwapBuff = 0L;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            tv_timer.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);

        }
    };

    @SuppressLint("StaticFieldLeak")
    private class CheckConnection extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                int timeoutMs = 1500;
                Socket sock = new Socket();
                SocketAddress sockaddr = new InetSocketAddress(GlobalConstants.clearIP, GlobalConstants.port);

                sock.connect(sockaddr, timeoutMs);
                sock.close();

                return true;
            } catch (IOException e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            connected = aBoolean;

            if(connected) {

                setupJWT();

                getTimeRecords gtr = new getTimeRecords();
                gtr.execute();
            }
            else
                recordsBtn.setEnabled(true);//TODO изменить на false при релизе

            if(!aBoolean)
                errorConnectionAlert();

        }
    }

    private void setupJWT(){
        JWToken = sPrefs.getString("JWT","notFound");

        //В случае отсутствия JWT переход на страницу авторизации
        assert JWToken != null;
        boolean JWTNotexists = JWToken.equals("notFound");
        if(JWTNotexists){
            Log.e("Not found jwt", "!!!");
            authenticated = false;
            Toast.makeText(this, "Вы не авторизованны", Toast.LENGTH_SHORT).show();
        }
        else{//Получение данных из JWT токена
            authenticated = true;
            recordsBtn.setEnabled(true);
            JWT jwt = new JWT(JWToken.substring(7, JWToken.length()));
            username = jwt.getSubject();
            //Получение информации о пользователе
            if(connected) {
                Log.e("gu", "starting");
                getUserInformation g = new getUserInformation();
                g.execute();
            }
        }
    }

    private void errorConnectionAlert(){
        error_layout.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Сервер не отвечает", Toast.LENGTH_LONG).show();
    }



    //Блок отрисовки развертки кубика
    private String[][] cubeInChars = {
            //   L                  F                   R               B                   U            D   x - строка y - столбец
//          y:0    1    2       3    4    5       6    7   8        9   10   11       12   13  14     15   16   17
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"},//x:0
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"},//X:1
            {"o", "o", "o",    "g", "g", "g",    "r", "r", "r",    "b", "b", "b",    "w", "w", "w",   "y", "y", "y"} //X:2
    };



    private void TurnB(){
        //Сохранение страых значений
        String t0 = cubeInChars[0][12];
        String t1 = cubeInChars[0][13];
        String t2 = cubeInChars[0][14];
        //Перестановка
        {
            cubeInChars[0][12]=cubeInChars[0][8];
            cubeInChars[0][13]=cubeInChars[1][8];
            cubeInChars[0][14]=cubeInChars[2][8];

            cubeInChars[0][8]=cubeInChars[2][17];
            cubeInChars[1][8]=cubeInChars[2][16];
            cubeInChars[2][8]=cubeInChars[2][15];

            cubeInChars[2][15]=cubeInChars[0][0];
            cubeInChars[2][16]=cubeInChars[1][0];
            cubeInChars[2][17]=cubeInChars[2][0];

            cubeInChars[0][0]=t2;
            cubeInChars[1][0]=t1;
            cubeInChars[2][0]=t0;}

        t0 = cubeInChars[0][9];
        t1 = cubeInChars[0][10];

        cubeInChars[0][10] = cubeInChars[1][9];
        cubeInChars[0][9] = cubeInChars[2][9];
        cubeInChars[1][9] = cubeInChars[2][10];

        cubeInChars[2][9] = cubeInChars[2][11];

        cubeInChars[2][10] = cubeInChars[1][11];
        cubeInChars[2][11] = cubeInChars[0][11];
        cubeInChars[1][11] = t1;
        cubeInChars[0][11] = t0;
    }

    private void TurnD(){
        //Сохранение страых значений
        String t0 = cubeInChars[2][3];
        String t1 = cubeInChars[2][4];
        String t2 = cubeInChars[2][5];
        //Перестановка
        {
            cubeInChars[2][5]=cubeInChars[2][2];
            cubeInChars[2][4]=cubeInChars[2][1];
            cubeInChars[2][3]=cubeInChars[2][0];

            cubeInChars[2][2]=cubeInChars[2][11];
            cubeInChars[2][1]=cubeInChars[2][10];
            cubeInChars[2][0]=cubeInChars[2][9];

            cubeInChars[2][11]=cubeInChars[2][8];
            cubeInChars[2][10]=cubeInChars[2][7];
            cubeInChars[2][9]=cubeInChars[2][6];

            cubeInChars[2][8]=t2;
            cubeInChars[2][7]=t1;
            cubeInChars[2][6]=t0;}

        t0 = cubeInChars[0][17];
        t1 = cubeInChars[0][16];

        cubeInChars[0][17] = cubeInChars[0][15];
        cubeInChars[0][16] = cubeInChars[1][15];
        cubeInChars[0][15] = cubeInChars[2][15];
        cubeInChars[1][15] = cubeInChars[2][16];
        cubeInChars[2][15] = cubeInChars[2][17];
        cubeInChars[2][16] = cubeInChars[1][17];
        cubeInChars[2][17] = t0;
        cubeInChars[1][17] = t1;
    }
    //Работает
    private void TurnU(){
        //Сохранение страых значений
        String t0 = cubeInChars[0][3];
        String t1 = cubeInChars[0][4];
        String t2 = cubeInChars[0][5];
        //Перестановка
        {
            cubeInChars[0][3]=cubeInChars[0][6];
            cubeInChars[0][4]=cubeInChars[0][7];
            cubeInChars[0][5]=cubeInChars[0][8];

            cubeInChars[0][6]=cubeInChars[0][9];
            cubeInChars[0][7]=cubeInChars[0][10];
            cubeInChars[0][8]=cubeInChars[0][11];

            cubeInChars[0][9]=cubeInChars[0][0];
            cubeInChars[0][10]=cubeInChars[0][1];
            cubeInChars[0][11]=cubeInChars[0][2];

            cubeInChars[0][0]=t0;
            cubeInChars[0][1]=t1;
            cubeInChars[0][2]=t2;}

        t0 = cubeInChars[0][12];
        t1 = cubeInChars[0][13];

        cubeInChars[0][13] = cubeInChars[1][12];
        cubeInChars[0][12] = cubeInChars[2][12];
        cubeInChars[1][12] = cubeInChars[2][13];
        cubeInChars[2][12] = cubeInChars[2][14];
        cubeInChars[2][13] = cubeInChars[1][14];
        cubeInChars[2][14] = cubeInChars[0][14];
        cubeInChars[1][14] = t1;
        cubeInChars[0][14] = t0;
    }
    //Работает
    private void TurnF(){
        //Сохранение страых значений
        String t0 = cubeInChars[2][12];
        String t1 = cubeInChars[2][13];
        String t2 = cubeInChars[2][14];
        //Перестановка
        {
            cubeInChars[2][12]=cubeInChars[2][2];
            cubeInChars[2][13]=cubeInChars[1][2];
            cubeInChars[2][14]=cubeInChars[0][2];

            cubeInChars[0][2]=cubeInChars[0][15];
            cubeInChars[1][2]=cubeInChars[0][16];
            cubeInChars[2][2]=cubeInChars[0][17];

            cubeInChars[0][15]=cubeInChars[2][6];
            cubeInChars[0][16]=cubeInChars[1][6];
            cubeInChars[0][17]=cubeInChars[0][6];

            cubeInChars[2][6]=t2;
            cubeInChars[1][6]=t1;
            cubeInChars[0][6]=t0;}


        t0 = cubeInChars[0][3];
        t1 = cubeInChars[0][4];

        cubeInChars[0][4] = cubeInChars[1][3];
        cubeInChars[0][3] = cubeInChars[2][3];
        cubeInChars[1][3] = cubeInChars[2][4];

        cubeInChars[2][3] = cubeInChars[2][5];

        cubeInChars[2][4] = cubeInChars[1][5];
        cubeInChars[2][5] = cubeInChars[0][5];
        cubeInChars[1][5] = t1;
        cubeInChars[0][5] = t0;
    }
    //Работает
    private void TurnL(){
        //Сохранение страых значений
        String t0 = cubeInChars[0][3];
        String t1 = cubeInChars[1][3];
        String t2 = cubeInChars[2][3];
        //Перестановка
        {
            cubeInChars[0][3]=cubeInChars[0][12];
            cubeInChars[1][3]=cubeInChars[1][12];
            cubeInChars[2][3]=cubeInChars[2][12];

            cubeInChars[0][12]=cubeInChars[2][11];
            cubeInChars[1][12]=cubeInChars[1][11];
            cubeInChars[2][12]=cubeInChars[0][11];

            cubeInChars[0][11]=cubeInChars[2][15];
            cubeInChars[1][11]=cubeInChars[1][15];
            cubeInChars[2][11]=cubeInChars[0][15];

            cubeInChars[0][15]=t0;
            cubeInChars[1][15]=t1;
            cubeInChars[2][15]=t2;}


        t0 = cubeInChars[0][0];
        t1 = cubeInChars[1][0];

        cubeInChars[0][0] = cubeInChars[2][0];
        cubeInChars[1][0] = cubeInChars[2][1];
        cubeInChars[2][0] = cubeInChars[2][2];

        cubeInChars[2][1] = cubeInChars[1][2];

        cubeInChars[2][2] = cubeInChars[0][2];
        cubeInChars[1][2] = cubeInChars[0][1];
        cubeInChars[0][2] = t0;
        cubeInChars[0][1] = t1;
    }
    //Работает
    private void TurnR(){
        //Сохранение страых значений
        String t0 = cubeInChars[0][5];
        String t1 = cubeInChars[1][5];
        String t2 = cubeInChars[2][5];
        //Перестановка
        {
            cubeInChars[0][5]=cubeInChars[0][17];
            cubeInChars[1][5]=cubeInChars[1][17];
            cubeInChars[2][5]=cubeInChars[2][17];

            cubeInChars[0][17]=cubeInChars[2][9];
            cubeInChars[1][17]=cubeInChars[1][9];
            cubeInChars[2][17]=cubeInChars[0][9];

            cubeInChars[0][9]=cubeInChars[2][14];
            cubeInChars[1][9]=cubeInChars[1][14];
            cubeInChars[2][9]=cubeInChars[0][14];

            cubeInChars[0][14]=t0;
            cubeInChars[1][14]=t1;
            cubeInChars[2][14]=t2;}


        t0 = cubeInChars[0][6];
        t1 = cubeInChars[1][6];

        cubeInChars[0][6] = cubeInChars[2][6];
        cubeInChars[1][6] = cubeInChars[2][7];
        cubeInChars[2][6] = cubeInChars[2][8];

        cubeInChars[2][7] = cubeInChars[1][8];

        cubeInChars[2][8] = cubeInChars[0][8];
        cubeInChars[1][8] = cubeInChars[0][7];
        cubeInChars[0][8] = t0;
        cubeInChars[0][7] = t1;



    }


    private void ApplyScramble(Turn[] scramble) {
        cubeInChars =
                new String[][]{
                        //   L                  F                   R               B                   U            D   x - строка y - столбец
                        // y:0    1    2       3    4    5       6    7   8        9   10   11       12   13  14     15   16   17
                        {"o", "o", "o", "g", "g", "g", "r", "r", "r", "b", "b", "b", "w", "w", "w", "y", "y", "y"},//x:0
                        {"o", "o", "o", "g", "g", "g", "r", "r", "r", "b", "b", "b", "w", "w", "w", "y", "y", "y"},//X:1
                        {"o", "o", "o", "g", "g", "g", "r", "r", "r", "b", "b", "b", "w", "w", "w", "y", "y", "y"} //X:2
                };
        for (Turn turn : scramble) {
            switch (turn.getName()) {
                case "R": {
                    TurnR();
                    break;
                }
                case "R2": {
                    TurnR();
                    TurnR();
                    break;
                }
                case "R`": {
                    TurnR();
                    TurnR();
                    TurnR();
                    break;
                }
                case "L": {
                    TurnL();
                    break;
                }
                case "L2": {
                    TurnL();
                    TurnL();
                    break;
                }
                case "L`": {
                    TurnL();
                    TurnL();
                    TurnL();
                    break;
                }
                case "F": {
                    TurnF();
                    break;
                }
                case "F2": {
                    TurnF();
                    TurnF();
                    break;
                }
                case "F`": {
                    TurnF();
                    TurnF();
                    TurnF();
                    break;
                }
                case "U": {
                    TurnU();
                    break;
                }
                case "U2": {
                    TurnU();
                    TurnU();
                    break;
                }
                case "U`": {
                    TurnU();
                    TurnU();
                    TurnU();
                    break;
                }
                case "D": {
                    TurnD();
                    break;
                }
                case "D`": {
                    TurnD();
                    TurnD();
                    TurnD();
                    break;
                }
                case "D2": {
                    TurnD();
                    TurnD();
                    break;
                }
                case "B": {
                    TurnB();
                    break;
                }
                case "B2": {
                    TurnB();
                    TurnB();
                    break;
                }
                case "B`": {
                    TurnB();
                    TurnB();
                    TurnB();
                    break;
                }
            }
        }
        reDrawCube();

    }


    private void setTileColor(ImageView img, String color){
        switch (color){
            case "o":
                img.setImageResource(R.drawable.orange_tile);
                break;
            case "g":
                img.setImageResource(R.drawable.green_tile);
                break;
            case "r":
                img.setImageResource(R.drawable.re_tile);
                break;
            case "b":
                img.setImageResource(R.drawable.blue_tile);
                break;
            case "w":
                img.setImageResource(R.drawable.whit_tile);
                break;
            case "y":
                img.setImageResource(R.drawable.yellow_tile);
                break;

        }
    }

    private void reDrawCube(){
        setTileColor(tile0_0, cubeInChars[0][0]);
        setTileColor(tile0_1, cubeInChars[0][1]);
        setTileColor(tile0_2, cubeInChars[0][2]);
        setTileColor(tile0_3, cubeInChars[0][3]);
        setTileColor(tile0_4, cubeInChars[0][4]);
        setTileColor(tile0_5, cubeInChars[0][5]);
        setTileColor(tile0_6, cubeInChars[0][6]);
        setTileColor(tile0_7, cubeInChars[0][7]);
        setTileColor(tile0_8, cubeInChars[0][8]);
        setTileColor(tile0_9, cubeInChars[0][9]);
        setTileColor(tile0_10, cubeInChars[0][10]);
        setTileColor(tile0_11, cubeInChars[0][11]);
        setTileColor(tile0_12, cubeInChars[0][12]);
        setTileColor(tile0_13, cubeInChars[0][13]);
        setTileColor(tile0_14, cubeInChars[0][14]);
        setTileColor(tile0_15, cubeInChars[0][15]);
        setTileColor(tile0_16, cubeInChars[0][16]);
        setTileColor(tile0_17, cubeInChars[0][17]);

        setTileColor(tile1_0, cubeInChars[1][0]);
        setTileColor(tile1_1, cubeInChars[1][1]);
        setTileColor(tile1_2, cubeInChars[1][2]);
        setTileColor(tile1_3, cubeInChars[1][3]);
        setTileColor(tile1_4, cubeInChars[1][4]);
        setTileColor(tile1_5, cubeInChars[1][5]);
        setTileColor(tile1_6, cubeInChars[1][6]);
        setTileColor(tile1_7, cubeInChars[1][7]);
        setTileColor(tile1_8, cubeInChars[1][8]);
        setTileColor(tile1_9, cubeInChars[1][9]);
        setTileColor(tile1_10, cubeInChars[1][10]);
        setTileColor(tile1_11, cubeInChars[1][11]);
        setTileColor(tile1_12, cubeInChars[1][12]);
        setTileColor(tile1_13, cubeInChars[1][13]);
        setTileColor(tile1_14, cubeInChars[1][14]);
        setTileColor(tile1_15, cubeInChars[1][15]);
        setTileColor(tile1_16, cubeInChars[1][16]);
        setTileColor(tile1_17, cubeInChars[1][17]);

        setTileColor(tile2_0, cubeInChars[2][0]);
        setTileColor(tile2_1, cubeInChars[2][1]);
        setTileColor(tile2_2, cubeInChars[2][2]);
        setTileColor(tile2_3, cubeInChars[2][3]);
        setTileColor(tile2_4, cubeInChars[2][4]);
        setTileColor(tile2_5, cubeInChars[2][5]);
        setTileColor(tile2_6, cubeInChars[2][6]);
        setTileColor(tile2_7, cubeInChars[2][7]);
        setTileColor(tile2_8, cubeInChars[2][8]);
        setTileColor(tile2_9, cubeInChars[2][9]);
        setTileColor(tile2_10, cubeInChars[2][10]);
        setTileColor(tile2_11, cubeInChars[2][11]);
        setTileColor(tile2_12, cubeInChars[2][12]);
        setTileColor(tile2_13, cubeInChars[2][13]);
        setTileColor(tile2_14, cubeInChars[2][14]);
        setTileColor(tile2_15, cubeInChars[2][15]);
        setTileColor(tile2_16, cubeInChars[2][16]);
        setTileColor(tile2_17, cubeInChars[2][17]);

    }


    //Не используется, нужен для реализации SensorEventListener
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}