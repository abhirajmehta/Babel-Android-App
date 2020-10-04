package com.example.babel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EncodeActivity extends AppCompatActivity {

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);

        int unicode = 0x2764;
        TextView txt = (TextView) findViewById(R.id.abhishek);
        String emoji = getEmojiByUnicode(unicode);
        String text = "Abhishek Mehta ";
        txt.setText(text + emoji);

        final Button copy = (Button) findViewById(R.id.copyButton);
        copy.setVisibility(View.GONE);

        final Button share = (Button) findViewById(R.id.shareButton);
        share.setVisibility(View.GONE);

        Button bab = (Button) findViewById(R.id.babelButton);

        bab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input = findViewById(R.id.messageToCode);
                String inp = input.getText().toString();

                final String babelText = getCode(inp);

                TextView txt = (TextView) findViewById(R.id.babelText);
                txt.setText(babelText);

                //To copy the babel text
                copy.setVisibility(View.VISIBLE);

                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        String text = babelText;
                        myClip = ClipData.newPlainText("text", text);
                        myClipboard.setPrimaryClip(myClip);
                        Toast.makeText(getApplicationContext(), "Text Copied",Toast.LENGTH_SHORT).show();
                    }
                });

                share.setVisibility(View.VISIBLE);

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, babelText);
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent,null);
                        startActivity(shareIntent);
                    }
                });

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

        String coded = codeIt(leftHalf,-3) + codeIt(righHalf,2);
        return coded;
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