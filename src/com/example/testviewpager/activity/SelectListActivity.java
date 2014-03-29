package com.example.testviewpager.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.testviewpager.R;
import com.example.testviewpager.domain.People;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectListActivity extends Activity {

	private ListView lv_selected;
	List<People> thisPageList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_selected);
		Intent intent = getIntent();
		ArrayList arrayList = (ArrayList) intent
				.getSerializableExtra("sectedList");
		thisPageList = (List<People>) arrayList.get(0);
		lv_selected = (ListView) findViewById(R.id.lv_selected);
		lv_selected.setAdapter(new MyAdapter());
	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return thisPageList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = new TextView(getApplicationContext());
			if (thisPageList.get(position).isSelected()) {
				tv.setTextColor(Color.BLACK);
				tv.setText("" + thisPageList.get(position).getId()
						+ "  -->true");
			} else {
				tv.setTextColor(Color.GRAY);
				tv.setText("" + thisPageList.get(position).getId()
						+ "  -->false");
			}
			return tv;
		}

	}

}
