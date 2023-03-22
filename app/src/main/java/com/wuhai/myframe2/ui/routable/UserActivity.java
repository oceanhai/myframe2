package com.wuhai.myframe2.ui.routable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class UserActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle intentExtras = getIntent().getExtras();
        // Note this extra, and how it corresponds to the ":id" above
        String userId = (String) intentExtras.get("id");
        Log.e("Router", "userId="+userId);
    }
}
