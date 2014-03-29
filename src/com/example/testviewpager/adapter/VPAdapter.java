package com.example.testviewpager.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.testviewpager.domain.People;
import com.example.testviewpager.fragment.ListViewFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class VPAdapter extends FragmentPagerAdapter {
	private ArrayList<List<People>> everyPagePeoplesList;

	public VPAdapter(FragmentManager fragmentManager, ArrayList<List<People>> everyPagePeoplesList) {
		super(fragmentManager);
		this.everyPagePeoplesList = everyPagePeoplesList;
	}

	@Override
	public Fragment getItem(int position) {
		List<People> list = everyPagePeoplesList.get(position);
		
		return new ListViewFragment().getInstance(list);
	}

	@Override
	public int getCount() {
		
		return everyPagePeoplesList.size();
	}

}
