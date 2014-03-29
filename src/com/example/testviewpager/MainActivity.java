package com.example.testviewpager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testviewpager.activity.ShowActivity;
import com.example.testviewpager.domain.People;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "MainActivity";
	private EditText et_count;
	private Button bt_ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt_ok = (Button) findViewById(R.id.bt_ok);
		et_count = (EditText) findViewById(R.id.et_count);
		bt_ok.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_ok:
			String string = et_count.getText().toString();
			if (!TextUtils.isEmpty(string)) {
				int count = Integer.parseInt(string);
				Toast.makeText(getApplicationContext(), count + "", 0).show();
				ArrayList peoples = new ArrayList();
				for (int i = 0; i < count; i++) {
					People p = new People();
					p.setName("aa" + i);
					p.setAge(18 + i);
					p.setId(i);
					peoples.add(p);
					p = null;
				}
				Intent intent = new Intent(MainActivity.this, ShowActivity.class);
				intent.putExtra("persons", peoples);
				startActivity(intent);

			} else {
				Toast.makeText(getApplicationContext(), "ÇëÊäÈëÊý¾Ý", 0).show();
			}
			break;
		}
	}

}
