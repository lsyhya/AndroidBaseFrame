package com.lsy.androidbaseframe.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.hjq.base.BaseActivity;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.lsy.androidbaseframe.R;
import com.lsy.androidbaseframe.app.AppActivity;
import com.lsy.androidbaseframe.http.LoginApi;
import com.lsy.androidbaseframe.other.AppConfig;
import com.lsy.androidbaseframe.other.PermissonsUtil;
import com.lsy.androidbaseframe.other.PhotoUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;

/**
 * @author lsy
 * 2021/5/27
 */
public class  MainActivity extends AppActivity {

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private android.widget.ImageView mImage;
    private TitleBar mTitle;

    /**
     * 跳转MainActivity
     */
    public static void start(Context context){
        Intent intent=new Intent(context,MainActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        //获取权限
        PermissonsUtil.getPermissons(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mImage = (ImageView) findViewById(R.id.image);
        mTitle = (TitleBar) findViewById(R.id.title);
        setOnClickListener(mBtn1,mBtn2,mBtn3,mBtn4);
    }

    @Override
    public void onRightClick(View view) {
        toast("设置");
    }

    @Override
    protected boolean isHideStatusBar() {
        return !super.isHideStatusBar();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn1:

                EasyHttp.get(this).api(new LoginApi().setUuid("3343")).request(new HttpCallback<LoginApi.LoginStoreBean>(this){

                    @Override
                    public void onStart(Call call) {
                        super.onStart(call);
                    }

                    @Override
                    public void onEnd(Call call) {
                        super.onEnd(call);
                    }

                    @Override
                    public void onSucceed(LoginApi.LoginStoreBean result) {
                        super.onSucceed(result);
                    }

                    @Override
                    public void onFail(Exception e) {
                        super.onFail(e);
                    }

                });

                break;

            case R.id.btn2:

                PhotoUtils.getInstance().takePhoto(this,new BaseActivity.OnActivityCallback() {
                    @Override
                    public void onActivityResult(int i, @Nullable @org.jetbrains.annotations.Nullable Intent intent) {

                        if(i==RESULT_OK&&intent!=null){
                            mImage.setImageURI(intent.getData());
                        }else {
                            toast("失败："+i);
                        }

                    }
                });

                break;

            case R.id.btn3:

                PhotoUtils.getInstance().takeCarame(this, new PhotoUtils.TakeCarameCallback() {
                    @Override
                    public void onTakeCarameResult(int i, @Nullable @org.jetbrains.annotations.Nullable Intent var2, Uri uri) {

                        if(i==RESULT_OK&&uri!=null){
                            mImage.setImageURI(uri);
                        }else {
                            toast("失败："+i);
                        }

                    }
                });

                break;

            case R.id.btn4:

                toast("btn4");

                break;

            default:

                break;

        }

    }

}
