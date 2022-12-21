package com.example.start;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView textview;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.title);
        textview.setText("下面哪个是中国的四大发明");

        rb1 = findViewById(R.id.option1);
        rb1.setText("火药");
        rb2 = findViewById(R.id.option2);
        rb2.setText("中药");
        rb3 = findViewById(R.id.option3);
        rb3.setText("火锅");
        rb4 = findViewById(R.id.option4);
        rb4.setText("指北针");

        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(ChangeRadioGroup);
    }

    private RadioGroup.OnCheckedChangeListener ChangeRadioGroup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == rb1.getId() && rb1.isChecked()) {
                Toast.makeText(MainActivity.this, rb1.getText()+"被选择", Toast.LENGTH_LONG).show();
            }
            if (i == rb2.getId() && rb2.isChecked()) {
                Toast.makeText(MainActivity.this, rb2.getText()+"被选择", Toast.LENGTH_LONG).show();
            }
            if (i == rb3.getId() && rb3.isChecked()) {
                Toast.makeText(MainActivity.this, rb3.getText()+"被选择", Toast.LENGTH_LONG).show();
            }
            if (i == rb4.getId() && rb4.isChecked()) {
                Toast.makeText(MainActivity.this, rb4.getText()+"被选择", Toast.LENGTH_LONG).show();
            }

        }
    };

}