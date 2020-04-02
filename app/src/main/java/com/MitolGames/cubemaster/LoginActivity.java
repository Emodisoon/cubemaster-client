package com.MitolGames.cubemaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MitolGames.cubemaster.util.GlobalConstants;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class LoginActivity extends AppCompatActivity {

    EditText passEdt;
    EditText loginEdt;

    SharedPreferences sPrefs;

    private void SetTypeFaces(Typeface face){
        TextView tv3 = findViewById(R.id.textView3);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv = findViewById(R.id.textView);
        tv3.setTypeface(face);
        tv.setTypeface(face);
        tv2.setTypeface(face);
        passEdt.setTypeface(face);
        loginEdt.setTypeface(face);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface face = Typeface.createFromAsset(getAssets(), GlobalConstants.mainFontPath);
        Button skipBtn = findViewById(R.id.skipAuth_button2);
        skipBtn.setTypeface(face);
        Button authBtn = findViewById(R.id.login_button);
        authBtn.setTypeface(face);
        Button regBtn = findViewById(R.id.reg_button);
        regBtn.setTypeface(face);
        loginEdt = findViewById(R.id.login_textR);
        passEdt = findViewById(R.id.password_TextR);
        SetTypeFaces(face);


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
            CheckConnection auth = new CheckConnection();
            auth.execute();
            }
        });
    }



    //String reqBody = "{ \"userName\": \""+loginEdt.getText()+"\", \"password\": \"1234567\"}";
    class Authorization extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String reqBody = "{ \"userName\": \""+loginEdt.getText()+"\", \"password\": \""+passEdt.getText()+"\"}";
            String url = GlobalConstants.IP +"/login";
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

    private void errorConnectionAlert(){
        Toast.makeText(this, "Сервер не отвечает", Toast.LENGTH_LONG).show();
    }

    class CheckConnection extends AsyncTask<Void, Void, Boolean>{

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
            if(aBoolean){
                Authorization auth = new Authorization();
                auth.execute();

            }
            else{
                errorConnectionAlert();
            }
        }
    }
}
