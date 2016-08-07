package com.yluo.yluorun;

import com.example.yluorun.R;
import com.yluo.yluorun.ui.widget.RotateLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private RotateLayout rv_rotate;
	float rotateDegree = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		rv_rotate = (RotateLayout) findViewById(R.id.rv_rotate);
	}
	
	public void rotate(View view) {
		rotateDegree += 5;
		rv_rotate.setRotateDegree(rotateDegree);
	}

}
