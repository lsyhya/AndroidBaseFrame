package com.lsy.androidbaseframe.other;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.lsy.androidbaseframe.app.AppActivity;

import java.util.List;

/**
 * 权限获取工具类
 *
 * @author lsy
 */
public class PermissonsUtil {

    /**
     * 权限数组，以后需要什么权限在里面添加
     */
    private static final String[] PERMISSONS = {Permission.MANAGE_EXTERNAL_STORAGE, Permission.CAMERA};

    /**
     * 获取所有权限
     * @param context 上下文引用对象
     */
    public static void getPermissons(AppActivity context) {

        XXPermissions.with(context)
                .permission(PERMISSONS)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {

                        if (all) {
                            context.toast("获取权限成功");
                        }else {
                            context.toast("部分权限未授予，请重新启动授予权限");
                        }

                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {

                        if (never) {
                            context.toast("获取权限失败,请前往权限页面开启所有权限");
                            XXPermissions.startPermissionActivity(context, permissions);
                        } else {
                            context.toast("获取权限失败,请重新启动授予权限");
                        }

                    }
                });

    }

}
