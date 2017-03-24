package com.example.project_myqq2.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_myqq2.R;
import com.example.project_myqq2.bean.UserInfoWrapper;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class MyNewsAdapter extends BaseAdapter{
	private List<UserInfoWrapper.UserInfo> infos;
	private Context context;
	
	public MyNewsAdapter(Context context,List<UserInfoWrapper.UserInfo> userInfo) {
		this.context=context;
		this.infos=userInfo;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View mView;
		if(convertView!=null){
			mView=convertView;
		}else {
			mView=View.inflate(context, R.layout.item_listview_msg, null);
		}
		SmartImageView smartImageView= (SmartImageView) mView.findViewById(R.id.smartImageView);
		TextView name=(TextView) mView.findViewById(R.id.name);
		TextView signature=(TextView) mView.findViewById(R.id.signature);

		UserInfoWrapper.UserInfo userInfo=infos.get(position);
		//1.请求的URL地址，2.显示请求失败的图片。3.正在请求的图片
		smartImageView.setImageUrl(userInfo.getHead(),R.drawable.image1,R.drawable.image2);
		name.setText(userInfo.getName());
		signature.setText(userInfo.getSignature());

		return mView;
	}


}
