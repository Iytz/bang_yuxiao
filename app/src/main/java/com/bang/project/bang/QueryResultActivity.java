package com.bang.project.bang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mad on 16-5-14.
 */
public class QueryResultActivity extends AppCompatActivity implements OnClickListener{
    ListView listview;
    RelativeLayout progress;
    LinearLayout layout_no_result;
    String type,content;
    Button download,add;
    protected QuickAdapter<item> myAdapter;

    public void initialData() {
        setContentView(R.layout.activity_query_result);

        listview = (ListView)findViewById(R.id.list_query);
        progress = (RelativeLayout)findViewById(R.id.progress);
        layout_no_result = (LinearLayout)findViewById(R.id.layout_no_result);
        download = (Button)findViewById(R.id.download);
        add = (Button)findViewById(R.id.add);

        download.setOnClickListener(this);
        add.setOnClickListener(this);

        if(myAdapter == null) {

            myAdapter = new QuickAdapter<item>() {

                @Override
                protected void convert(BaseAdapterHelper helper, item item) {
                    helper.setText(tv_title,item.getTitle())
                            .setText(tv_describe, item.getDescribe())
                            .setText(tv_size,item.getMysize())
                            .setText(tv_owner,item.getOwner_name());
                }
            };
        }

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        content = intent.getStringExtra("content");
        listview.setAdapter(myAdapter);
        List<item> items = null;
        try {
            items = query();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<item> finalItems = items;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                assert finalItems != null;
                item temp = finalItems.get(i);


            }
        });
    }

    public void DL(item it) {
        DownLoad task = new DownLoad(it.getUrl(), "/storage/emulated/legacy/",this);
        task.execute();
    }
    public List<item> parseJsonMulti(String str,String type,String content) {
        try {
            JSONArray vec = new JSONArray(str);
            String s = "";
            List<item> items = null;
            for(int i=0;i<vec.length();++i) {
                JSONObject temp = (JSONObject)vec.get(i);

                if(temp.getString("kind") == type && (temp.getString("name") == content || content.length()==0)) {
                    item NewItem = new item();
                    NewItem.setTitle(content);
                    NewItem.setType(type);
                    NewItem.setHaveKey(temp.getBoolean("isEncrypt"));
                    NewItem.setDescribe(temp.getString("desc"));
                    NewItem.setMysize(temp.getString("size"));
                    NewItem.setOwner_name(temp.getString("owner"));
                    NewItem.setUrl(temp.getString("url"));
                    if(temp.getBoolean("isEncrypt")) {
                        NewItem.setKey(temp.getString("password"));
                    }
                    items.add(NewItem);
                }

            }

            return items;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<item> search(String type,String content) throws IOException {
        List<item> items;

        Map<String,String> map = new HashMap<String, String>();
        String url = "http://localhost:8080/BangServer/android/viewResource.jsp?mode=android";

        HttpPost request = new HttpPost(url);
        JSONObject param = new JSONObject();

        StringEntity se = new StringEntity(param.toString());
        request.setEntity(se);

        HttpResponse httpResponse = new DefaultHttpClient().execute(request);
        String retSrc = EntityUtils.toString(httpResponse.getEntity());

        return parseJsonMulti(retSrc,type,content);
    }

    public List<item> query() throws IOException {
        listview.setVisibility(View.VISIBLE);
        layout_no_result.setVisibility(View.GONE);

        List<item> items;
        items = search(type,content);
        myAdapter.clear();
        if(items == null || items.size() == 0) {
            showError();
            myAdapter.notifyDataSetChanged();
            return null;
        }
        progress.setVisibility(View.GONE);
        myAdapter.addAll(items);
        listview.setAdapter(myAdapter);

        return items;
    }

    public void showError() {
        progress.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        layout_no_result.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialData();

    }

    @Override
    public void onClick(View view) {

    }


}
