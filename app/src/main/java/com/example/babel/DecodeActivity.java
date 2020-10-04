package com.example.babel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DecodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);

        int unicode = 0x2764;
        TextView txt = (TextView) findViewById(R.id.abhishek);
        String emoji = getEmojiByUnicode(unicode);
        String text = "Abhishek Mehta ";
        txt.setText(text + emoji);

        Button unbabelButton = (Button) findViewById(R.id.unBabelButton);
        unbabelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView babelText = (TextView) findViewById(R.id.messageToDecode);
                String bab = babelText.getText().toString();

                final String unbabText = getCode(bab);

                TextView ans = (TextView) findViewById(R.id.unBabelText);
                ans.setText(unbabText);

            }
        });
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

    private String getCode(String inp){

        int len = inp.length();
        String leftHalf = inp.substring(0,len);
        String righHalf = inp.substring(len);

        String decoded = codeIt(leftHalf,+3) + codeIt(righHalf,-2);
        return decoded;
    }

    private String codeIt(String message, int key) {

        char[] arr = message.toCharArray();
        String ans = "";

        for(int i = 0; i < arr.length; i++) {

            char c = (char) (arr[i] + key);
            ans = ans + c;

        }

        return ans;
    }
}
