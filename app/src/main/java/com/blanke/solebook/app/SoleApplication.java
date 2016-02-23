package com.blanke.solebook.app;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.blanke.solebook.bean.Book;
import com.blanke.solebook.bean.BookColumn;
import com.blanke.solebook.bean.SoleUser;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by Blanke on 16-2-19.
 */
public class SoleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initAvos();
        initImageLoader();
    }
    private void initAvos(){
        AVUser.alwaysUseSubUserClass(SoleUser.class);
        AVObject.registerSubclass(Book.class);
        AVObject.registerSubclass(BookColumn.class);
        AVOSCloud.initialize(this, "l8eot9jDXBhCt40q1BPJqH9a-gzGzoHsz", "fAYpLpd3IBwlaiixg0bM20Rm");
        AVOSCloud.setDebugLogEnabled(true);
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
//                .memoryCacheExtraOptions(500,700)
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 10 * 1000))
//                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }
}
