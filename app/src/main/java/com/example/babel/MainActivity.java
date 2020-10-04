package com.example.babel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int unicode = 0x2764;
        TextView txt = (TextView) findViewById(R.id.abhishek);
        String emoji = getEmojiByUnicode(unicode);
        String text = "Abhishek Mehta ";
        txt.setText(text + emoji);

        Button button = (Button) findViewById(R.id.button_encode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this,EncodeActivity.class);
                startActivity(i);
            }
        });

        Button button2 = (Button) findViewById(R.id.button_decode);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DecodeActivity.class);
                startActivity(i);
            }
        });

    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}