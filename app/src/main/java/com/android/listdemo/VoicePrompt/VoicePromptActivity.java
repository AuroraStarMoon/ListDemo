package com.android.listdemo.VoicePrompt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.listdemo.MainActivity;
import com.android.listdemo.R;

import java.util.Random;

public class VoicePromptActivity extends AppCompatActivity {

    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;
    private int countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_prompt);
        TextView hellow = findViewById(R.id.hellow);
        //final SendVoiceView sendVoiceView = findViewById(R.id.sendVoice);

        hellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtil.showAlertDialog(VoicePromptActivity.this);

            }
        });
    }


}
