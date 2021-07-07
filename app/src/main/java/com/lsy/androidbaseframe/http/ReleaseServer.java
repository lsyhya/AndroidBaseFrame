package com.lsy.androidbaseframe.http;

import com.hjq.http.config.IRequestServer;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/EasyHttp
 *    time   : 2019/05/19
 *    desc   : 正式环境
 */
public class ReleaseServer implements IRequestServer {

    @Override
    public String getHost() {
        return "http://124.70.196.135:9090/";
    }

    @Override
    public String getPath() {
        return "";
    }
}