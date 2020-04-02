package com.MitolGames.cubemaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MitolGames.cubemaster.models.TimeRecord;
import com.MitolGames.cubemaster.models.User;
import com.MitolGames.cubemaster.util.GlobalConstants;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class recordsActivity extends AppCompatActivity {

    SharedPreferences sPrefs;
    String JWToken;
    String username;
    String userID;

    TextView userName_tv;

    
    LinearLayout recordsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Typeface face = Typeface.createFromAsset(getAssets(), GlobalConstants.mainFontPath);
        userName_tv = findViewById(R.id.userName_tv);
        TextView urtv = findViewById(R.id.yourrecords_tv);
        urtv.setTypeface(face);
        userName_tv.setTypeface(face);
        recordsLayout = findViewById(R.id.records_layout);


        //Получаем токен
        sPrefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        JWToken = sPrefs.getString("JWT","notFound");
        JWT jwt = new JWT(JWToken.substring(7, JWToken.length()));
        username = jwt.getSubject();
        userName_tv.setText(username);


        
        
        getUserInformation gu = new getUserInformation();
        getTimeRecords gtr = new getTimeRecords();
        gtr.execute();
        gu.execute();

    }

    //'/api/GetPersonalRecords/'+this.$route.params.username

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

            ObjectMapper mapper = new ObjectMapper();

            return response;
        }

        @SuppressLint("SetTextI18n")
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

                for (final TimeRecord tr: timeRecords) {

                    //Заполнение данными линеар лэйаута
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 250);
                    layoutParams.topMargin = 50;
                    layoutParams.leftMargin=30;
                    layoutParams.rightMargin=30;

                    final TextView newTv = new TextView(getApplicationContext());

                    newTv.setLayoutParams(layoutParams);

                    newTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                    newTv.setTextColor(Color.BLACK);
                    newTv.setBackgroundResource(R.drawable.toolbar_bg);
                    newTv.setGravity(Gravity.CENTER);

                    Typeface face = Typeface.createFromAsset(getAssets(), GlobalConstants.mainFontPath);
                    newTv.setTypeface(face);
                    LocalDateTime dateTime = LocalDateTime.parse(tr.getCreationDate());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd, HH:mm");
                    DateTimeFormatter shorFormatter = DateTimeFormatter.ofPattern("dd/MM");
                    final String formatDateTime = dateTime.format(formatter);


                    int secs = (int) (tr.getTime() / 1000);
                    int mins = secs / 60;
                    secs = secs % 60;
                    int milliseconds = (int) (tr.getTime() % 1000);
                    newTv.setText( mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds)+"          |          " +dateTime.format(shorFormatter));
                    final String temp = String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
                    newTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), TimeRecordsDetails.class);
                            intent.putExtra("Scramble", tr.getScramble());
                            intent.putExtra("Time", temp);
                            intent.putExtra("Date", formatDateTime);
                            intent.putExtra("id", tr.getId());
                            startActivity(intent);
                        }
                    });

                    recordsLayout.addView(newTv);

                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }


    }




    //Получение данных о пользователе
    @SuppressLint("StaticFieldLeak")
    class getUserInformation extends AsyncTask<Void, Void, User> {
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
        }
    }
}
