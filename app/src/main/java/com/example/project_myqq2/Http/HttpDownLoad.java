package com.example.project_myqq2.Http;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.project_myqq2.Adapter.MyNewsAdapter;
import com.example.project_myqq2.bean.UserInfoWrapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Administrator on 2017/3/1.
 */

public class HttpDownLoad extends AsyncTask<String, Void, String> {

    private URL url;
    private HttpURLConnection connection;
    private ListView listView;
    private Context context;

    public HttpDownLoad(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;

    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        BufferedReader br = null;
        try {
            url = new URL("http://cloud.bmob.cn/d9f6840be6bb07cf/friends_test?clive=contacts");
            connection = (HttpURLConnection) url.openConnection();
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            Gson gson=new Gson();
            UserInfoWrapper wrrap = new Gson().fromJson(s, UserInfoWrapper.class);
            MyNewsAdapter adapter=new MyNewsAdapter(context, wrrap.userInfo);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
