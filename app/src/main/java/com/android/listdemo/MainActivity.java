package com.android.listdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.listdemo.VoicePrompt.VoicePromptActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  Button voice_prompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voice_prompt = findViewById(R.id.voice_prompt);
        voice_prompt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.voice_prompt:
                startActivity(new Intent(MainActivity.this, VoicePromptActivity.class));
                break;
        }
    }
}
