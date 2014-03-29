package com.example.testviewpager.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testviewpager.R;
import com.example.testviewpager.adapter.VPAdapter;
import com.example.testviewpager.domain.People;
import com.example.testviewpager.fragment.ListViewFragment;

public class ShowActivity extends FragmentActivity implements OnClickListener {
	private static final String TAG = "ShowActivity";
	ArrayList<People> peoples;
	ArrayList<List<People>> everyPagePeoplesList;
	private ViewPager vp;
	List<ListViewFragment> fragmentList;
	Button bt_open;
	int currentPage = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		bt_open = (Button) findViewById(R.id.bt_open);
		bt_open.setOnClickListener(this);
		Intent intent = getIntent();
		// 获取到传递过来的list信息
		peoples = (ArrayList<People>) intent.getSerializableExtra("persons");
		initData(10, 5);
		initFragment();
		VPAdapter adapter = new VPAdapter(getSupportFragmentManager(),
				everyPagePeoplesList);
		vp = (ViewPager) findViewById(R.id.vp);
		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				currentPage = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initFragment() {
		fragmentList = new ArrayList<ListViewFragment>();
		for (int i = 0; i < everyPagePeoplesList.size(); i++) {
			// 这里需要添加自己定义的带有listview的fragment 并且传入该fragment要显示的信息的list
			ListViewFragment fragment = new ListViewFragment()
					.getInstance(everyPagePeoplesList.get(i));
			fragmentList.add(fragment);
			fragment = null;
		}
	}

	/**
	 * 设置list分类的方式
	 * 
	 * @param normalSize
	 *            每页正常显示的个数
	 * @param minSize
	 *            最后一页显示的最少个数的限制
	 */
	private void initData(int normalSize, int minSize) {
		int size = peoples.size();
		int zu = 0;
		if (size <= normalSize) {
			zu = 1;
		} else if (size > normalSize && size % normalSize >= minSize) {
			zu = size / 10 + 1;
		} else if (size > normalSize && size % normalSize < minSize) {
			zu = size / normalSize;
		}
		Log.i(TAG, "分组数:" + zu);
		everyPagePeoplesList = new ArrayList<List<People>>();
		for (int i = 0; i < zu; i++) {
			int start = i * normalSize;
			int end;
			if (i == zu - 1) {
				end = size - i * normalSize + start;
			} else {
				end = (i + 1) * normalSize;
			}

			Log.i(TAG, "从" + start + "到" + end);
			// 得到每一页的list
			Log.i(TAG, "peoples.size=" + peoples.size());
			List<People> subList = peoples.subList(start, end);
			everyPagePeoplesList.add(subList);
		}
		Log.i(TAG, "分了" + everyPagePeoplesList.size() + "页");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_open:
			Intent intent = new Intent();
			intent.setClass(ShowActivity.this, SelectListActivity.class);
			List<People> thisPageList = everyPagePeoplesList.get(currentPage);
			ArrayList arrayList = new ArrayList();
			arrayList.add(thisPageList);
			intent.putExtra("sectedList", arrayList);
			startActivity(intent);
			break;
		}
	}

}
