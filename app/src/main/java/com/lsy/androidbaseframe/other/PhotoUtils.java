package com.lsy.androidbaseframe.other;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.hjq.base.BaseActivity;

import java.io.File;

public class PhotoUtils {

    private static final String filePath = Environment.getExternalStorageDirectory().getPath() + "/base";
    private static File fileUri =null;

    private static PhotoUtils photoUtils=null;

    private PhotoUtils(){
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        if(fileUri==null){
            fileUri=new File(filePath+"/"+ SystemClock.currentThreadTimeMillis() + ".jpg");
        }

    };

    public static PhotoUtils getInstance(){
        if(photoUtils==null){
            synchronized (PhotoUtils.class){
                if (photoUtils==null){
                    photoUtils=new PhotoUtils();
                }
            }
        }
        return photoUtils;
    }


    /**
     * 获取相册里的照片
     * @param baseActivity
     * @param activityCallback
     */
    public  void takePhoto(BaseActivity baseActivity,BaseActivity.OnActivityCallback activityCallback ){
        //调用自己的图库
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        baseActivity.startActivityForResult(i, activityCallback);
    }

    public  void takeCarame(BaseActivity baseActivity,TakeCarameCallback callback){
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 通过 FileProvider 创建一个 Content 类型的 Uri 文件
            imageUri = FileProvider.getUriForFile(baseActivity, AppConfig.getPackageName() + ".provider", fileUri);
        } else {
            imageUri = Uri.fromFile(fileUri);
        }

        Intent intentCamera = new Intent();
        intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // 将拍取的照片保存到指定 Uri
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        baseActivity.startActivityForResult(intentCamera, new BaseActivity.OnActivityCallback() {
            @Override
            public void onActivityResult(int i, @Nullable @org.jetbrains.annotations.Nullable Intent intent) {
                callback.onTakeCarameResult(i,intent,imageUri);
            }
        });
    }

    public static interface TakeCarameCallback{

        void onTakeCarameResult(int var1, @Nullable Intent var2,Uri uri);

    }


}
