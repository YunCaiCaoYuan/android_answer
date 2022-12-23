package com.example.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Button button;
        button = findViewById(R.id.startBtn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                new Thread(new Runnable(){
//                    @Override
//                    public void run() {
//                        //请求详情
//                        PostUtils.GetQues("http://192.168.0.17:8080/pull_question");
//                    }}).start();

                // 显示跳转第二页面
                Intent intent = new Intent(Start.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "下面哪个是中国的四大发明?");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
}