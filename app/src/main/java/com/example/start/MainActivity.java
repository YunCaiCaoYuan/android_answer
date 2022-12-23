package com.example.start;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView textview;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioGroup rg;

    private Integer titleId;
    private Integer titleIndex = 0;
    private String req_content;
    private Integer titleNum = 0;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        req_content = bundle.getString("question");
        this.setNext();
        jsonArray = new JSONArray();

        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(ChangeRadioGroup);
    }

    private void setNext() {
        try {
            JSONArray jArray = new JSONArray(req_content);
            JSONObject jObject = jArray.getJSONObject(titleIndex);
            titleNum = jArray.length();

            String titleStr = jObject.getString("title");
            JSONArray jsonArr = jObject.getJSONArray("opts");
            titleId = jObject.getInt("id");

            textview = findViewById(R.id.title);
            textview.setText(titleStr);

            rb1 = findViewById(R.id.option1);
            rb1.setText(jsonArr.getString(0));
            rb2 = findViewById(R.id.option2);
            rb2.setText(jsonArr.getString(1));
            rb3 = findViewById(R.id.option3);
            rb3.setText(jsonArr.getString(2));
            rb4 = findViewById(R.id.option4);
            rb4.setText(jsonArr.getString(3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private RadioGroup.OnCheckedChangeListener ChangeRadioGroup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int opt = 0;
            if (i == rb1.getId() && rb1.isChecked()) {
                opt = 1;
            }
            if (i == rb2.getId() && rb2.isChecked()) {
                opt = 2;
            }
            if (i == rb3.getId() && rb3.isChecked()) {
                opt = 3;
            }
            if (i == rb4.getId() && rb4.isChecked()) {
                opt = 4;
            }

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("title_id", titleId);
                jsonObject.put("answer_opt", opt);
                jsonArray.put(titleIndex, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            titleIndex++;
            setNext();
            if (titleIndex < titleNum) {
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(false);
            }
            if (Objects.equals(titleNum, titleIndex)) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        //请求详情
                        PostUtils.Post("http://192.168.0.17:8080/commit_answer_list", jsonArray.toString().getBytes());
                    }}).start();
            }
        }
    };


}