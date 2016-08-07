package com.yluo.yluorun.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class RotateLayout extends RelativeLayout {

	private static final String TAG = "RotateView";
	
	private Camera mCamera;
	private Matrix mMatrix;
	private Paint mPaint;
	private float rotateDegree;
	
	private Scroller mScroller;
	
	private float mBaseScaleFactor = 0.8f;
	
	private float mCurScale = 1.0f;
	
	private float tranZ = 0;

	public RotateLayout(Context context) {
		this(context, null);

	}

	public RotateLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RotateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mCamera = new Camera();
		
		mMatrix = new Matrix();
		
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		mPaint.setColor(Color.RED);
		
		mScroller = new Scroller(getContext());
		
		
	}
	
	public void setRotateDegree(float rotateDegree) {
		
		mScroller.startScroll(0, 0, -360, 400, 3000);
		
		invalidate();
		
		
	}
	@Override
	public void computeScroll() {
		if(mScroller.computeScrollOffset()) {
			
			if(mScroller.getCurrY() <= 200) {
				mCurScale = 1.0f -  mScroller.getCurrY() / 1000.0f;
//				tranZ = mScroller.getCurrY();
			} else {
				mCurScale = 0.8f +  (mScroller.getCurrY() - 200) / 1000.0f;
				
//				tranZ = getMeasuredWidth() - mScroller.getCurrY();
			}
			
			rotateDegree = mScroller.getCurrX();
			
			invalidate();
		}
		
//		
		
    }
	@SuppressLint("DrawAllocation")
	protected void dispatchDraw(Canvas canvas) {
		
		mCamera.save();
		mCamera.rotateY(rotateDegree);
		mCamera.getMatrix(mMatrix);
		mCamera.restore();
		mMatrix.postScale(mCurScale, mCurScale);
		mMatrix.preTranslate(-getMeasuredWidth()/2,-getMeasuredHeight()/2);
		mMatrix.postTranslate(getMeasuredWidth()/2,getMeasuredHeight()/2);
		
		canvas.save();
		canvas.concat(mMatrix);
		super.dispatchDraw(canvas);
//		super.onDraw(canvas);
//		canvas.drawRect(new Rect(0,0,getMeasuredWidth(),getMeasuredHeight()),
//				mPaint);
		canvas.restore();
	}
}

















