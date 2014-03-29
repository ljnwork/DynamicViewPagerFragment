package com.example.testviewpager.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testviewpager.R;
import com.example.testviewpager.R.id;
import com.example.testviewpager.R.layout;
import com.example.testviewpager.domain.People;

public class ListViewFragment extends Fragment implements OnItemClickListener {

	private static final String THIS_PAGE_LIST = "this_page_list";
	private static final String TAG = "ListViewFragment";
	private List<People> thisPageList;
	private ListView listView;

	/**
	 * 初始化一个fragment
	 * 
	 * @param list
	 *            该fragment包含的信息的list
	 * @return 一个新创建的fragment
	 */
	public ListViewFragment getInstance(List<People> list) {
		ListViewFragment fragment = new ListViewFragment();
		Bundle bundle = new Bundle();
		ArrayList arrayList = new ArrayList();
		arrayList.add(list);
		bundle.putParcelableArrayList(THIS_PAGE_LIST, arrayList);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List list = new ArrayList();
		list = getArguments().getParcelableArrayList(THIS_PAGE_LIST);
		Log.i(TAG, "arrayList size" + list.size());
		thisPageList = (List<People>) list.get(0);// 得到该页要显示的list
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, null);
		listView = (ListView) view.findViewById(R.id.lv_fragment);
		MyAdapter adapter = new MyAdapter();
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		Log.i(TAG, "lv_choose_word:" + listView.getCount());
		return view;
	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return thisPageList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			People people = thisPageList.get(position);
			View view;
			ViewHolder holder;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View
						.inflate(getActivity(), R.layout.item_listview, null);
				holder = new ViewHolder();
				holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
				holder.tv_id = (TextView) view.findViewById(R.id.tv_id);
				holder.cb_select = (CheckBox) view.findViewById(R.id.cb_select);
				view.setTag(holder);
			}

			holder.tv_name.setText("姓名：" + people.getName());
			holder.tv_id.setText("年龄：" + people.getId());
			holder.cb_select.setChecked(people.isSelected());

			return view;
		}

		public class ViewHolder {
			TextView tv_name;
			TextView tv_id;
			CheckBox cb_select;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		People people = thisPageList.get(position);
		CheckBox cb = (CheckBox) view.findViewById(R.id.cb_select);
		if (people.isSelected()) {
			cb.setChecked(false);
			people.setSelected(false);
		} else {
			cb.setChecked(true);
			people.setSelected(true);
		}
		Log.i(TAG, "---------------------");
		for(int i=0;i<thisPageList.size();i++){
			Log.i(TAG, thisPageList.get(i).getId()+"："+thisPageList.get(i).isSelected());
		}
	}
}
