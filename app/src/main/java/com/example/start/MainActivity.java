package com.example.start;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textview;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioGroup rg;
    private Integer titleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String req_content = bundle.getString("key");
        String titleStr = "";
        JSONArray jsonArr = null;

        try {
            JSONObject jObject = new JSONObject(req_content);
            titleStr = jObject.getString("title");
            jsonArr = jObject.getJSONArray("opts");
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

        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(ChangeRadioGroup);
    }

    private RadioGroup.OnCheckedChangeListener ChangeRadioGroup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int opt = 0;
            if (i == rb1.getId() && rb1.isChecked()) {
                opt = 1;
//                Toast.makeText(MainActivity.this, rb1.getText()+"被选择", Toast.LENGTH_LONG).show();
            }
            if (i == rb2.getId() && rb2.isChecked()) {
                opt = 2;
//                Toast.makeText(MainActivity.this, rb2.getText()+"被选择", Toast.LENGTH_LONG).show();
            }
            if (i == rb3.getId() && rb3.isChecked()) {
                opt = 3;
//                Toast.makeText(MainActivity.this, rb3.getText()+"被选择", Toast.LENGTH_LONG).show();
            }
            if (i == rb4.getId() && rb4.isChecked()) {
                opt = 4;
//                Toast.makeText(MainActivity.this, rb4.getText()+"被选择", Toast.LENGTH_LONG).show();
            }

            int finalOpt = opt;
            new Thread(new Runnable(){
                @Override
                public void run() {
                    //请求详情
                    PostUtils.CommitByPost(titleId, finalOpt);
                }}).start();
        }
    };

}