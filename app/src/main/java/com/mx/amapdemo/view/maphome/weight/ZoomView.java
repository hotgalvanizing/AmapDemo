package com.mx.amapdemo.view.maphome.weight;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mx.amapdemo.R;

public class ZoomView extends LinearLayout implements View.OnClickListener {

    private ImageView mBtnZoomIn, mBtnZoomOut;
    IZoomListener mZoomListener = null;

    public interface IZoomListener {
        void onZoomIn(View view);

        void onZoomOut(View view);
    }

    private boolean mIsZoomInClickable = true, mIsZoomOutClickable = true;

    public ZoomView(Context context) {
        super(context);
        init(context, null);
    }

    public ZoomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ZoomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ZoomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        onFindViews();
        onInitData();
    }

    private void onFindViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.zoom_view, this);
        setOrientation(LinearLayout.VERTICAL);
        mBtnZoomIn = findViewById(R.id.btn_zoom_in);
        mBtnZoomOut = findViewById(R.id.btn_zoom_out);
    }

    private void onInitData() {
        mBtnZoomIn.setOnClickListener(this);
        mBtnZoomOut.setOnClickListener(this);
    }

    public void setZoomListener(IZoomListener zoomListener) {
        mZoomListener = zoomListener;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnZoomIn)) {
            if (mZoomListener != null) {
                mZoomListener.onZoomIn(this);
            }

        } else if (v.equals(mBtnZoomOut)) {
            if (mZoomListener != null) {
                mZoomListener.onZoomOut(this);
            }
        }
    }

    /**
     * set zoom-in button enable status
     *
     * @param enalbe
     */
    public void setZoomInEnalbe(boolean enalbe) {
        mBtnZoomIn.setEnabled(enalbe);
    }

    /**
     * set zoom-out button enable status
     *
     * @param enable
     */
    public void setZoomOutEnable(boolean enable) {
        mBtnZoomOut.setEnabled(enable);
    }

    public boolean getZoomInEnable() {
        return mBtnZoomIn.isEnabled();
    }

    public boolean getZoomOutEnable() {
        return mBtnZoomOut.isEnabled();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
