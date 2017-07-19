package com.tsing.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 自定义标题栏
 * Created by Lei on 2017/01/10.
 */
public class ViewTitle extends LinearLayout {
    public enum TitleType {
        BACK, RTEXT, RMENU
    }

    private TextView name, tvRight;
    private ImageView ivLeft, ivRight;

    public ViewTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            Log.e("ViewTitle", "isInEditMode");
            return;
        }

        LayoutInflater.from(context).inflate(R.layout.view_title, this);
        name = (TextView) findViewById(R.id.tv_name);

        ivLeft = (ImageView) findViewById(R.id.iv_left);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        tvRight = (TextView) findViewById(R.id.tv_right);
    }

    // 设置标题栏标题文字
    public void setTitleName(String text) {
        name.setText(text);
        name.setVisibility(VISIBLE);
    }

    // 设置标题栏标题文字
    public void setTitleName(int resid) {
        name.setText(resid);
        name.setVisibility(View.VISIBLE);
    }

    // 设置标题栏右侧图标
    public void setRightImg(int resid) {
        ivRight.setImageResource(resid);
    }

    // 隐藏标题栏右侧图标
    public void hidRightImg() {
        ivRight.setVisibility(GONE);
    }

    //设置标题左侧图标
    public void setLeftImg(int resid){
        ivLeft.setImageResource(resid);
    }

    //设置标题栏右侧文字
    public void  setRightTv(int resid){
        tvRight.setText(resid);
        tvRight.setVisibility(VISIBLE);
    }

    // 激活并使用标题栏上的某个按钮
    public View activateView(TitleType titleTypes) {
        switch (titleTypes) {
            case BACK:// 标题栏左侧返回按钮
                ivLeft.setVisibility(View.VISIBLE);
                return ivLeft;
            case RMENU:// 标题栏右侧图标
                ivRight.setVisibility(View.VISIBLE);
                return ivRight;
            case RTEXT:// 标题栏右侧文字
                tvRight.setVisibility(View.VISIBLE);
                return tvRight;
            default:
                return null;
        }
    }

}