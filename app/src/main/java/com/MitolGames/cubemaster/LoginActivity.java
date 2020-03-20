package com.MitolGames.cubemaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class LoginActivity extends AppCompatActivity {

    EditText passEdt;
    EditText loginEdt;

    SharedPreferences sPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button skipBtn = findViewById(R.id.skipAuth_button2);
        Button authBtn = findViewById(R.id.login_button);
        Button regBtn = findViewById(R.id.reg_button);
        loginEdt = findViewById(R.id.login_textR);
        passEdt = findViewById(R.id.password_TextR);



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Authorization auth = new Authorization();
            auth.execute();
            }
        });
    }


    //String reqBody = "{ \"userName\": \""+loginEdt.getText()+"\", \"password\": \"1234567\"}";
    class Authorization extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String reqBody = "{ \"userName\": \""+loginEdt.getText()+"\", \"password\": \""+passEdt.getText()+"\"}";
            String url = "http://192.168.0.12:8080/login";
            String result;

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            HttpEntity<String> request = new HttpEntity<String>(reqBody,headers);
            ResponseEntity<String> resp;
            resp = restTemplate.postForEntity(url,request,String.class);

            HttpHeaders authHeader = resp.getHeaders();
            result = authHeader.getAuthorization();

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            sPrefs = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor ed = sPrefs.edit();
            ed.putString("JWT", s);
            ed.apply();

            Log.e("Token: ", s);
            if(s!=null){
                Toast toast = Toast.makeText(LoginActivity.this, "Authorization complete" , Toast.LENGTH_SHORT);
                toast.show();}

            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntent);
        }
    }
}
