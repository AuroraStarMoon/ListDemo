package com.android.listdemo.VoicePrompt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.listdemo.R;

public class ViewUtil {


    private static final Handler mhandler = new Handler();

    /**
     * 滚屏的线程
     */
    private static Runnable ScrollRunnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            int off = linearLayout.getMeasuredWidth() - horizontalScrollView.getWidth();// 判断高度
            if (off > horizontalScrollView.getScrollX()) {
                horizontalScrollView.scrollBy(1, 0);
                if (horizontalScrollView.getScaleY() == off) {
                    Thread.currentThread().interrupt();
                } else {
                    mhandler.postDelayed(this, 30);
                }
            }else {
                horizontalScrollView.scrollBy(-off, 0);
                if (horizontalScrollView.getScaleY() == off) {
                    Thread.currentThread().interrupt();
                } else {
                    mhandler.postDelayed(this, 30);
                }
            }
        }
    };
    private static LinearLayout linearLayout;
    private static HorizontalScrollView horizontalScrollView;
    private static Context context;

    public static void showAlertDialog(Activity activity) {
        context = activity.getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.dialog);
        AlertDialog alertDialog =builder.create();
        String[] voicePrompt1 = new String[]{"来一首歌","来一首豫剧","帮我切歌","切换原唱","切换伴唱","帮我重唱","帮我暂停","帮我快进/后退","帮我播放","帮我静音","取消静音","开启/关闭评分","帮我快进/后退","已点列表","上/下一页"};
        String[] voicePrompt2 = new String[]{"帮我播放","帮我静音","取消静音","开启/关闭评分","帮我快进/后退","已点列表","上/下一页","来一首歌","来一首豫剧","帮我切歌","切换原唱","切换伴唱","帮我重唱","帮我暂停","帮我快进/后退"};
        int[] Colors = new int[]{R.color.one,R.color.two,R.color.three,R.color.four,R.color.five};

        horizontalScrollView = new HorizontalScrollView(activity.getApplicationContext());
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        linearLayout = new LinearLayout(activity.getApplicationContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setClipChildren(false);

        TextView textView = new TextView(activity.getApplicationContext());
        textView.setText("你可以这么说...");
        textView .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setTextSize(25);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(20,0,20,0);
        textView.setLayoutParams(layoutParams);

        SendVoiceNewView sendVoiceNewView = new SendVoiceNewView(activity);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(300,200);
        layoutParams1.setMargins(500,0,0,0);
        sendVoiceNewView.setLayoutParams(layoutParams1);
        sendVoiceNewView.onStart();
        sendVoiceNewView.setmSendVoiceListener(new SendVoiceNewView.SendVoiceListener() {
            @Override
            public void onSendClick() {
            }
        });

        LinearLayout linearLayout1 = new LinearLayout(activity.getApplicationContext());
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout1.addView(textView);
        linearLayout1.addView(sendVoiceNewView);

        //linearLayout.addView(linearLayout1);
        linearLayout.addView(getNewLayout(voicePrompt1,Colors));
        int[] colors_copy = new int[Colors.length];
        for (int i = 0;i<Colors.length;i++){
            colors_copy[Colors.length-i-1] = Colors[i];
        }
        linearLayout.addView(getNewLayout(voicePrompt2,colors_copy));

        horizontalScrollView.addView(linearLayout);
        LinearLayout baseLine = new LinearLayout(activity.getApplicationContext());
        baseLine.setOrientation(LinearLayout.VERTICAL);
        baseLine.addView(linearLayout1);
        baseLine.addView(horizontalScrollView);
        alertDialog.setView(baseLine);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = alertDialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = d.getWidth(); //宽度设置为屏幕
        alertDialog.getWindow().setAttributes(p); //设置生效
        mhandler.post(ScrollRunnable);
    }

    private static LinearLayout getNewLayout(String[] strings,int[] colors) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        int count = 0;
        for (int i = 0;i < strings.length;i++){
            GradientDrawable drawable = new GradientDrawable();
            count = i;
            if (count>4){
                int a = i/4;
                int b = i - a*4;
                count = b;
            }
            drawable.setColor(context.getResources().getColor(colors[count]));

            drawable.setCornerRadius(120);

            TextView textView = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100);
            layoutParams.setMargins(10,20,10,20);
            textView.setText(strings[i]);
            textView.setTextColor(Color.WHITE);
            textView.setBackground(drawable);
            textView.setTextSize(16);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(50,25,50,25);
            textView.setLayoutParams(layoutParams);
            linearLayout.addView(textView);

        }
        return linearLayout;
    }
}
