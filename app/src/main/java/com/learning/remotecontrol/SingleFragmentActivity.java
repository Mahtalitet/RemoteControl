package com.learning.remotecontrol;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class SingleFragmentActivity extends AppCompatActivity {
	
	protected abstract Fragment createFragment();
	
	protected int getLayoutResuorceId() {
		return R.layout.activity_fragment;
	}

	protected int getFragmentContainerId() {
		return R.id.fragmentContainer;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResuorceId());

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(getFragmentContainerId());
		if (fragment == null) {
			fragment = createFragment();
			fm.beginTransaction().add(getFragmentContainerId(), fragment).commit();
		}
	}
}
