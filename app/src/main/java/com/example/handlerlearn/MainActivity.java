package com.example.handlerlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.handlerlearn.activity.AsyncTaskActivity;
import com.example.handlerlearn.activity.HandlerLearnActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        Button btnAsyncTask = (Button) findViewById(R.id.btn_async_task);
        Button btnHandlerLearn = (Button) findViewById(R.id.btn_handler_learn);

        btnAsyncTask.setOnClickListener(btnClickListener);
        btnHandlerLearn.setOnClickListener(btnClickListener);

    }

    private View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_async_task:
                    startActivity(AsyncTaskActivity.class);
                    break;
                case R.id.btn_handler_learn:
                    startActivity(HandlerLearnActivity.class);
                    break;
            }
        }
    };

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}
