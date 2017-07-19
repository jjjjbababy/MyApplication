package com.tsing.myapplication;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by ${Tsing} on 2017/7/19.
 * 基类Activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected PopupWindow popWindow;
    protected ViewTitle viewTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(getLayoutId());
        initData();
        initView();
    }

    protected abstract void initView();

    protected abstract void initStatusBar();

    protected abstract int getLayoutId();

    protected abstract void initData();

    /**
     * 设置标题，标题栏左侧有返回键，点击关闭
     *
     * @param resid 标题文字(e.g., R.string.title)
     */
    protected void initTitleAndBack(int resid) {
        viewTitle = (ViewTitle) findViewById(R.id.titlebar);
        viewTitle.setTitleName(resid);
        View ivLeft = viewTitle.activateView(ViewTitle.TitleType.BACK);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 设置标题
     *
     * @param resid 标题文字(e.g., R.string.title)
     */
    protected void setTitleName(int resid) {
        viewTitle = (ViewTitle) findViewById(R.id.titlebar);
        viewTitle.setTitleName(resid);
    }

    /**
     * 设置标题
     *
     * @param name 标题文字(String类型)
     */
    protected void setTitleName(String name) {
        viewTitle = (ViewTitle) findViewById(R.id.titlebar);
        viewTitle.setTitleName(name);
    }


    protected View initPopWindow(int resource) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View pView = inflater.inflate(resource, null);

        popWindow = new PopupWindow(pView);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                blurredBackground(1.0f);
            }
        });
        return pView;
    }

    /**
     * 从屏幕底部弹出PopupWindow
     */
    protected void showPwFromBottom() {
        if (popWindow == null)
            return;
        popWindow.setAnimationStyle(R.style.PopupAnimation);
        popWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        // 背景变暗
        blurredBackground(0.7f);
    }

    /**
     * 背景变暗
     *
     * @param alpha 0.7f变暗;1.0f回复原状
     */
    protected void blurredBackground(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }

    /**
     * 获取屏幕宽高
     *
     * @return int[0] width(宽度);int[1] height(高度)
     */
    protected int[] getDisplayWidthHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return new int[]{displaymetrics.widthPixels, displaymetrics.heightPixels};
    }

}
