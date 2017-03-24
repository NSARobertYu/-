package com.example.project_myqq2.HomeActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_myqq2.Http.HttpDownLoad;
import com.example.project_myqq2.R;
import com.example.project_myqq2.util.Message;
import com.example.project_myqq2.util.MessageDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/1.
 */

public class MessageFragment extends Fragment implements View.OnClickListener{

    private MessageDao dao;
    private ListView lv;
    private Button btn;
    private RadioGroup rg;
    private EditText et;
    private SimpleDateFormat simpleDateFormat;
    private MessageAdapter adapter;
    private View head;
    //      保存当前页数   总页数         默认查询每页显示的个数
    private int page = 1,totalpage = 0,size = 5;
    //保存第一条记录的添加时间
    private long first;

    private List<Message> datas = new ArrayList<Message>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_contact, null);

        lv = (ListView) view.findViewById(R.id.main_list);
        rg = (RadioGroup) view.findViewById(R.id.radio_group);
        btn = (Button) view.findViewById(R.id.btn_sumit);
        et = (EditText) view.findViewById(R.id.et_input);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        dao = new MessageDao(getContext());
        btn.setOnClickListener(this);

        //添加头部显示内容
        head = LayoutInflater.from(getContext()).inflate(R.layout.item_list_top,null);
        head.setId(0x11);
        lv.addHeaderView(head);
        head.setOnClickListener(this);

        adapter = new MessageAdapter();
        lv.setAdapter(adapter);

        first = System.currentTimeMillis();

        //获取历史记录的页数
        long total = dao.total(first);
        totalpage = (int)(total%size==0?total/size:total/size+1);


        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sumit:
                send();
                break;
            case 0x11://点击查看历史记录
                find(first);
                break;
        }
    }

    //查看及时记录
    public void find(long first){
        if (page <= totalpage){
            datas.addAll(0,dao.queryByPage(page++,size,first));
            //更新页面
            adapter.notifyDataSetChanged();
        }
        if (page>totalpage){
            //没有更多数据进行显示
            TextView tv = (TextView) head.findViewById(R.id.top_tv);
            tv.setText("--没有更多数据--");
            head.setOnClickListener(null);
        }
    }

    //发送消息的时候，并在页面中显示，清空EditText
    private void send(){
        String message = et.getText().toString();
        if (message == null||"".equals(message)){

            Toast.makeText(getContext(),"信息不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        int type = rg.getCheckedRadioButtonId()==R.id.rb_relay?0:1;
        long time = System.currentTimeMillis();
        Message m  = new Message(message,type,time);
        dao.add(m);
        //显示在页面
        datas.add(m);
        adapter.notifyDataSetChanged();
        //clear 输入
        et.setText("");
        //设置当前位置选中
        lv.setSelection(datas.size()-1);
    }

    //适配数据的适配器类
    class MessageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Message getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            Integer id = datas.get(position).getId();

            return id==null?0:id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MessageHolder holder = null;
            if (convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat,null);
                holder = new MessageHolder();

                holder.left = (TextView)convertView.findViewById(R.id.tv_left);
                holder.leftTime = (TextView)convertView.findViewById(R.id.tv_left_time);
                holder.right = (TextView)convertView.findViewById(R.id.tv_right);
                holder.rightTime = (TextView)convertView.findViewById(R.id.tv_right_time);
                convertView.setTag(holder);

            }else {
                holder = (MessageHolder)convertView.getTag();
            }
            //赋值
            Message m = getItem(position);
            if (m.getType()==1 ){//1表示接收
                holder.right.setVisibility(View.GONE);
                holder.rightTime.setVisibility(View.GONE);
                holder.left.setVisibility(View.VISIBLE);
                holder.leftTime.setVisibility(View.VISIBLE);

                holder.left.setText(m.getMessage());
                holder.leftTime.setText(simpleDateFormat.format(new Date(m.getTime())));
            }else {
                holder.left.setVisibility(View.GONE);
                holder.leftTime.setVisibility(View.GONE);
                holder.right.setVisibility(View.VISIBLE);
                holder.rightTime.setVisibility(View.VISIBLE);

                holder.right.setText(m.getMessage());
                holder.rightTime.setText(simpleDateFormat.format(new Date(m.getTime())));
            }
            return convertView;
        }
    }

    class MessageHolder {
        public TextView left;
        public TextView leftTime;
        public TextView right;
        public TextView rightTime;


    }

}
