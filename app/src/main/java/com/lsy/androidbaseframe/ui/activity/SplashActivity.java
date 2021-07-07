package com.lsy.androidbaseframe.ui.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.lsy.androidbaseframe.R;
import com.lsy.androidbaseframe.app.AppActivity;

import org.jetbrains.annotations.NotNull;

public class SplashActivity extends AppActivity {

    LottieAnimationView lottieAnimationView;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        super.initView();

        lottieAnimationView=findViewById(R.id.lav_splash_lottie);

        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                lottieAnimationView.removeAnimatorListener(this);
                MainActivity.start(getContext());
                finish();
            }
        });

    }

    @NonNull
    @NotNull
    @Override
    public ImmersionBar getStatusBarConfig() {
        return super.getStatusBarConfig().hideBar(BarHide.FLAG_HIDE_BAR);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    protected void initActivity() {
        //问题及方案：https://www.cnblogs.com/net168/p/5722752.html
        // 如果当前 Activity 不是任务栈中的第一个 Activity
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            // 如果当前 Activity 是通过桌面图标启动进入的
            if (intent != null && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
                    && Intent.ACTION_MAIN.equals(intent.getAction())) {
                // 对当前 Activity 执行销毁操作，避免重复实例化入口
                finish();
                return;
            }
        }
        super.initActivity();
    }

    @Override
    public void onBackPressed() {
        //禁用返回键
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        // 因为修复了一个启动页被重复启动的问题，所以有可能 Activity 还没有初始化完成就已经销毁了
        // 所以如果需要在此处释放对象资源需要先对这个对象进行判空，否则可能会导致空指针异常
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}