package com.MitolGames.cubemaster;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    EditText passEdt;
    EditText loginEdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Typeface face = Typeface.createFromAsset(getAssets(), GlobalConstants.mainFontPath);
        Button regBtn = findViewById(R.id.registrate_button);
        loginEdt = findViewById(R.id.login_textR);
        passEdt = findViewById(R.id.password_TextR);

        TextView tv3 = findViewById(R.id.textView3);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv = findViewById(R.id.textView);
        tv3.setTypeface(face);
        tv.setTypeface(face);
        tv2.setTypeface(face);
        passEdt.setTypeface(face);
        loginEdt.setTypeface(face);
        regBtn.setTypeface(face);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckConnection reg = new CheckConnection();
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
            String url = GlobalConstants.IP + "/main/registration";

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
                registration reg = new registration();
                reg.execute();

            }
            else{
                errorConnectionAlert();
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        }
    }
}
