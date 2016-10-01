package com.example.handlerlearn.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.handlerlearn.R;

import java.lang.ref.WeakReference;

public class HandlerMemoryLeakActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_memory_leak);

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //do something
//            }
//        }, 10 * 60 * 1000);

        mHandler.postDelayed(sRunnable, 10 * 60 * 1000);
//        finish();
    }

//    private final Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            //...
//        }
//    };

    private final MyHandler mHandler = new MyHandler(this);
    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() {
            //do something
        }
    };

    private static class MyHandler extends Handler {
        WeakReference<HandlerMemoryLeakActivity> mActivity;

        public MyHandler(HandlerMemoryLeakActivity activity) {
            this.mActivity = new WeakReference<HandlerMemoryLeakActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerMemoryLeakActivity activity = mActivity.get();
            if (activity != null) {
                //do something with activity
            }

            super.handleMessage(msg);
        }
    }
}
