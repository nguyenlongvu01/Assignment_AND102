package com.vunlph30245.assignment;

import android.app.Application;
import com.cloudinary.android.MediaManager;
import java.util.HashMap;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Khởi tạo MediaManager một lần duy nhất
        HashMap<String, String> config = new HashMap<>();
        config.put("cloud_name", "vunlph30245");
        config.put("api_key", "958934268436276");
        config.put("api_secret", "JHjTfWMeH8XqMmalxpIsDUO8xn4");

        MediaManager.init(this, config);  // Chỉ gọi một lần ở đây
    }
}
