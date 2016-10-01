package com.example.handlerlearn.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.handlerlearn.R;

public class HandlerLearnActivity extends Activity {

    private static final String TAG = HandlerLearnActivity.class.getSimpleName();

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_learn);
        initViews();
    }

    private void initViews() {
        mTextView = (TextView) findViewById(R.id.txt_show);
        //使用Handler的post方法更新UI
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("update text in post of mHandler...");
            }
        });

        //在子线程里边实现消息机制
        Thread testThread = new Thread() {
            private Handler handler;

            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        Log.d(TAG, "handleMessage in sub thread..., msg.what: " + msg.what);
                        super.handleMessage(msg);
                    }
                };

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                }).start();
                Looper.loop();
            }
        };
        testThread.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "msg.what: " + msg.what);
            super.handleMessage(msg);
        }
    };
}
