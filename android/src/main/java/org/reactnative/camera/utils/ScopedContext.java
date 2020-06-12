package org.reactnative.camera.utils;

import android.content.Context;
import android.content.pm.PackageManager;

import java.io.File;

/**
 * Created by jgfidelis on 23/01/18.
 */

public class ScopedContext {

    private File cacheDirectory = null;
    private Context context;

    public ScopedContext(Context context) {
        this.context = context;
        createCacheDirectory(context);
    }

    public void createCacheDirectory(Context context) {
        cacheDirectory = new File(context.getCacheDir() + "/Camera/");
    }

    public File getCacheDirectory() {
        return cacheDirectory;
    }

    public Context getContext() {
        return context.getApplicationContext();
    }

    public PackageManager getPackageManager() {
        return context.getPackageManager();
    }
}
