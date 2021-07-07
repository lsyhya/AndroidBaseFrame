package com.lsy.androidbaseframe.app;

import android.app.Application;

import com.hjq.http.EasyConfig;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestInterceptor;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.lsy.androidbaseframe.BuildConfig;
import com.lsy.androidbaseframe.http.ReleaseServer;
import com.lsy.androidbaseframe.http.RequestHandler;
import com.lsy.androidbaseframe.http.TestServer;
import com.lsy.androidbaseframe.manager.ActivityManager;
import com.lsy.androidbaseframe.other.AppConfig;
import com.lsy.androidbaseframe.other.DebugLoggerTree;

import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * 应用入口
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSdk(this);
    }

    /**
     * 初始化第三方SDk
     */
    private void initSdk(AppApplication appApplication) {

        /**
         * 权限
         * 设置调试模式
         */
        XXPermissions.setDebugMode(AppConfig.isDebug());
        /**
         * 吐司
         */
        ToastUtils.init(appApplication);
        /**
         * Activity 栈管理初始化
         */
        ActivityManager.getInstance().init(appApplication);
        /**
         * 初始化日志打印
         */
        if (AppConfig.isLogEnable()) {
            Timber.plant(new DebugLoggerTree());
        }
        /**
         * 初始化网络请求框架
         */
        initHttp();
//        /**
//         * 初始化Bugly
//         */
//        Bugly.init(getApplicationContext(), "后台提供的BuglyID", false);

    }

    private void initHttp(){
        // 网络请求框架初始化
        IRequestServer server;
        if (BuildConfig.DEBUG) {
            server = new TestServer();
        } else {
            server = new ReleaseServer();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        EasyConfig.with(okHttpClient)
                // 是否打印日志
                .setLogEnabled(BuildConfig.DEBUG)
                // 设置服务器配置
                .setServer(server)
                // 设置请求处理策略
                .setHandler(new RequestHandler(this))
                // 设置请求参数拦截器
                .setInterceptor(new IRequestInterceptor() {
                    @Override
                    public void interceptArguments(IRequestApi api, HttpParams params, HttpHeaders headers) {
                        headers.put("timestamp", String.valueOf(System.currentTimeMillis()));
                    }
                })
                // 设置请求重试次数
                .setRetryCount(1)
                // 设置请求重试时间
                .setRetryTime(2000)
                // 添加全局请求参数
                //.addParam("token", "6666666")
                // 添加全局请求头
                //.addHeader("time", "20191030")
                .into();
    }
}
