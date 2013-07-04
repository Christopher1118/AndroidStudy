package com.android.study.buttonsadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String idNameImage = "ItemImage", idNameTitle = "ItemTitle", idNameText = "ItemText", idNameClick = "ItemClick";
		int idImage = R.id.ItemImage, idTitle = R.id.ItemTitle, idText = R.id.ItemText, idClick = R.id.ItemClick;
		
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put(idNameImage,R.drawable.ic_launcher );
		item1.put(idNameTitle, "title 1");
		item1.put(idNameText, "ItemText 1");
		item1.put(idNameClick, "click");
		
		List<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
		itemList.add((HashMap<String, Object>) item1);
		
		View.OnClickListener listener = new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Click Button", Toast.LENGTH_LONG).show();
				
			}
			
		};
		
		View.OnClickListener[] listeners ={listener};
		
		//SimpleAdapter sa = new SimpleAdapter(this, itemList, R.layout.list_view, new String[]{idNameImage, idNameTitle, idNameText}, new int[]{idImage, idTitle, idText});
		ButtonsAdapter ba = new ButtonsAdapter(this, itemList, R.layout.list_view, new String[]{idNameImage, idNameTitle, idNameText, idNameClick}, new int[]{idImage, idTitle, idText, idClick},listeners);
		ListView lv = (ListView)findViewById(R.id.ListView01);
		lv.setAdapter(ba);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
