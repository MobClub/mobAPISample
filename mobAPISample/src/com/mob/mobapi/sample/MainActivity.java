/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package com.mob.mobapi.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.mob.mobapi.API;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.sample.cook.CookAPIActivity;
import com.mob.mobapi.sample.custom.CustomAPIActivity;
import com.mob.mobapi.sample.mobile.MobileAPIActivity;
import com.mob.mobapi.sample.postcode.PostcodeAPIActivity;
import com.mob.mobapi.sample.weather.WeatherAPIActivity;

public class MainActivity extends Activity implements OnClickListener {
	private static final String APPKEY = "从Mob开发者后台得到的APPKEY";
	private EditText etAppkey;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btnIntegrated).setOnClickListener(this);
		findViewById(R.id.btnCustomAPI).setOnClickListener(this);
		findViewById(R.id.btnWeatherAPI).setOnClickListener(this);
		findViewById(R.id.btnCookAPI).setOnClickListener(this);
		findViewById(R.id.btnPostcodeAPI).setOnClickListener(this);
		findViewById(R.id.btnMobileAPI).setOnClickListener(this);
		etAppkey = (EditText) findViewById(R.id.etAppkey);
		etAppkey.setText(APPKEY);

	}


	public void onClick(View v) {
		// 要在任何操作之前至少要调用一次initSDK来完成SDK的初始化
		MobAPI.initSDK(this, etAppkey.getText().toString().trim());

		switch (v.getId()) {
			case R.id.btnIntegrated: showIntegratedAPIs(); break;
			case R.id.btnCustomAPI: startActivity(new Intent(this, CustomAPIActivity.class)); break;
			case R.id.btnWeatherAPI: startActivity(new Intent(this, WeatherAPIActivity.class)); break;
			case R.id.btnCookAPI: startActivity(new Intent(this, CookAPIActivity.class)); break;
			case R.id.btnPostcodeAPI: startActivity(new Intent(this, PostcodeAPIActivity.class)); break;
			case R.id.btnMobileAPI: startActivity(new Intent(this, MobileAPIActivity.class)); break;
		}
	}

	private void showIntegratedAPIs() {
		API[] apis = MobAPI.listAPI();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		StringBuffer sb = new StringBuffer();
		for (API api : apis) {
			sb.append("\n\t").append(api.getName());
		}
		String msg = getString(R.string.integrated_apis_x, sb.toString());
		builder.setMessage(msg);
		builder.setPositiveButton(R.string.close, null);
		builder.show();
	}

}
