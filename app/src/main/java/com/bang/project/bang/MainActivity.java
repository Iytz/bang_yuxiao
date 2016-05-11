package com.bang.project.bang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 以下是主界面5个按钮的onClick 事件
        // 进入私信消息登录界面
        Button btMainMessage = (Button) findViewById(R.id.bt_mainMessage);
        btMainMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });

        // 进入我 界面
        Button btMainMe = (Button) findViewById(R.id.bt_mainMe);
        btMainMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MeActivity.class);
                startActivity(intent);
            }
        });

        // 进入资源圈 界面
        Button btMainResources = (Button) findViewById(R.id.bt_mainResources);
        btMainResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResourcesActivity.class);
                startActivity(intent);
            }
        });

        // 进入校友圈 界面
        Button btMainSchoolmates = (Button) findViewById(R.id.bt_mainSchoolmates);
        btMainSchoolmates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SchoolmatesActivity.class);
                startActivity(intent);
            }
        });

    }
}
