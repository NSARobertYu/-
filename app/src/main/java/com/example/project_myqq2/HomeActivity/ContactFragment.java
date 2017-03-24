package com.example.project_myqq2.HomeActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroupManager;
import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.example.project_myqq2.Http.HttpDownLoad;
import com.example.project_myqq2.R;

/**
 * Created by Administrator on 2017/3/1.
 */

public class ContactFragment extends Fragment {

    private ListView listView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_fragment_msg, null);

        listView = (ListView) view.findViewById(R.id.listview);

        //网络获取联系人列表
        HttpDownLoad httpDownLoad = new HttpDownLoad(listView, getContext());
        httpDownLoad.execute();

        return view;

    }

}
