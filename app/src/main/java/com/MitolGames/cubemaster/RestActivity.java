package com.MitolGames.cubemaster;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MitolGames.cubemaster.models.Msg;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RestActivity extends AppCompatActivity {
    private TextView tv, msgtv;

    private String JwtToken;

    SharedPreferences sPrefs;

    Msg msg;

    public String s;
    private EditText Ip_edt;
    JsonNode root = null;
    private List<Map<String, String>> messages = new ArrayList<Map<String,String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        tv = findViewById(R.id.MainText);
        msgtv = findViewById(R.id.Message_Text);

        Button b = findViewById(R.id.bb);
        Button postBtn = findViewById(R.id.post_button);

        sPrefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        JwtToken = sPrefs.getString("JWT","notFound");


        Ip_edt = findViewById(R.id.IP_editText);

        msg = new Msg();
        msg.setId((long)161);


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePost mt = new makePost();
                mt.execute();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeGet mt = new makeGet();
                mt.execute();
            }
        });

    }


    class makePost extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            String url = "http://"+Ip_edt.getText()+":8080/message";
            msg.setText(msgtv.getText().toString());

            //Adding JWT token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("AUTHORIZATION", JwtToken );

            RestTemplate restTemplate = new RestTemplate();
            //restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


            HttpEntity<Msg> request =
                    new HttpEntity<Msg>(msg, headers);

            Msg msg1 = restTemplate.postForObject(url, request, Msg.class);
            Log.e(" asd", msg1.getText());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast toast = Toast.makeText(RestActivity.this, "Http code: 201" , Toast.LENGTH_SHORT);
            toast.show();
        }
    }



    class makeGet extends AsyncTask<Void, Void, ResponseEntity<String>> {



        @Override
        protected ResponseEntity<String> doInBackground(Void... params) {

            String url = "http://"+Ip_edt.getText()+":8080/message";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("AUTHORIZATION", JwtToken);

            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            ResponseEntity<String> response;


            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers),String.class);

            ObjectMapper mapper = new ObjectMapper();

            root = null;

            try {
                root = mapper.readTree(response.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(ResponseEntity<String> result) {
            super.onPostExecute(result);
            Toast toast = Toast.makeText(RestActivity.this, "Http code: " + result.getStatusCode().toString(), Toast.LENGTH_SHORT);
            toast.show();

            ObjectMapper mapper = new ObjectMapper();

            tv.setText("");

            List<Msg> messages;
            try {
                messages = mapper.readValue(result.getBody(), new TypeReference<List<Msg>>() {

                });

                for (Msg msg: messages) {
                    tv.setText(tv.getText()+"\n id: "+msg.getId()+" text: "+msg.getText());

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
