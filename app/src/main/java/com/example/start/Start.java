package com.example.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Button button;
        button = findViewById(R.id.startBtn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        //请求详情
                        String res = PostUtils.GetQues("http://192.168.0.17:8080/pull_question");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                // 显示跳转第二页面
                                Intent intent = new Intent(Start.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("key", res);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                    }}).start();

            }
        });

    }
}