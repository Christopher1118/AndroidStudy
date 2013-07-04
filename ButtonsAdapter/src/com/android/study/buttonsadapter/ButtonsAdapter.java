package com.android.study.buttonsadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ButtonsAdapter extends BaseAdapter {
	
	List<HashMap<String, Object>> mData;
	private Context context;
	private int listViewId;
	String[] idNames;
	int[] ids;
	View.OnClickListener[] listeners;
	int listenerFlag;
	
	private LayoutInflater mInflater;
	
	public class ViewHolder{
		ArrayList<View> views;
		public ViewHolder(){
			views = new ArrayList<View>();
		}
	}
	
	ButtonsAdapter(Context context, List<HashMap<String, Object>> data, int id, String[] idNames, int[] ids, View.OnClickListener[] ls) {
		mData = data;
		this.context = context;
		listViewId = id;
		this.idNames = idNames;
		this.ids = ids;
		listeners = ls;
		listenerFlag = 0;
		this.mInflater = LayoutInflater.from(context);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(listViewId, null); 
			for(int i = 0; i<ids.length ; i++){
				View view = convertView.findViewById(ids[i]);
				holder.views.add(view);
			}
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		for(int i = 0; i < holder.views.size(); i++){
			View view = holder.views.get(i);
			if (view instanceof TextView){
				TextView tv = (TextView)view;
				try{
				  tv.setText((String)mData.get(position).get(idNames[i]));
				}catch (RuntimeException e){
					Log.e("ButtonsAdapter", "Improper value type passed to TextView!");
				}

			}
			if (view instanceof EditText){
				EditText et = (EditText)view;
				try{
				  et.setText((String)mData.get(position).get(idNames[i]));
				}catch (RuntimeException e){
					Log.e("ButtonsAdapter", "Improper value type passed to EditText!");
				}

				
			}
			if (view instanceof ImageView){
				ImageView iv = (ImageView)view;
				try{
					int resId = Integer.parseInt(mData.get(position).get(idNames[i]).toString());
					resId = (Integer)mData.get(position).get(idNames[i]);                   //           1 of 2
					Bitmap bm = BitmapFactory.decodeResource(context.getResources(),resId);
					iv.setImageBitmap(bm);
				}catch (RuntimeException e){
					Log.e("ButtonsAdapter", "Improper value type passed to ImageView");
				}

			}
			
			if (view instanceof Button){
				Button bt = (Button)view;
				try{
					bt.setText((String)mData.get(position).get(idNames[i]));
					if (listenerFlag > listeners.length-1){
						listenerFlag = listeners.length-1;
					}
					bt.setOnClickListener(listeners[listenerFlag]);
					listenerFlag++;
				}catch (RuntimeException e){
					Log.e("ButtonsAdapter", "Improper value type passed to ImageView");
				}
			}

			
		}
		
		return convertView;
	}

}
