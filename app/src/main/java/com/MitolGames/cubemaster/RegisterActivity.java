package com.MitolGames.cubemaster;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RegisterActivity extends AppCompatActivity {

    EditText passEdt;
    EditText loginEdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button regBtn = findViewById(R.id.registrate_button);
        loginEdt = findViewById(R.id.login_textR);
        passEdt = findViewById(R.id.password_TextR);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration reg = new registration();
                reg.execute();
            }
        });
    }

    class registration extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String reqBody = "{ \"userName\": \""+loginEdt.getText()+"\", \"password\": \""+passEdt.getText()+"\"}";
            String url = "http://192.168.0.12:8080/main/registration";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            HttpEntity<String> request = new HttpEntity<String>(reqBody,headers);
            ResponseEntity<String> resp;
            resp = restTemplate.postForEntity(url,request,String.class);

            return null;
        }
    }
}
