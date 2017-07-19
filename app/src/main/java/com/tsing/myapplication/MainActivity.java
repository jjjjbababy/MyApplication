package com.tsing.myapplication;

public class MainActivity extends BaseActivity {

    @Override
    protected void initView() {
        initTitleAndBack(R.string.app_name);
    }

    @Override
    protected void initStatusBar() {
        StatusBarCompat.compat(this,-1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

}
