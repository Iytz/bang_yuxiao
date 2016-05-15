package com.bang.project.bang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ResourcesActivity extends AppCompatActivity implements View.OnClickListener {

    EditText query_content;
    Button type1, type2, type3;
    ImageButton ib;
    String content,type;

    public void initData() {

        //布局
        setContentView(R.layout.activity_resources);

        query_content = (EditText)findViewById(R.id.ed_resources_search);
        type1 = (Button)findViewById(R.id.button);
        type2 = (Button)findViewById(R.id.button2);
        type3 = (Button)findViewById(R.id.button3);
        ib = (ImageButton)findViewById(R.id.imageButton);

        //监听
        type1.setOnClickListener(this);
        type2.setOnClickListener(this);
        type3.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public void onClick(View view) {
        content = query_content.getText().toString();

        if(view == type1) {
            type = "类别一";
        }
        else if(view == type2) {
            type = "类别二";
        }
        else if(view == type3) {
            type = "类别三";
        }
        else if(view == ib) {
            Intent intent = new Intent(this,QueryResultActivity.class);
            intent.putExtra("type",type);
            intent.putExtra("content",content);
            startActivity(intent);
        }
    }

}
