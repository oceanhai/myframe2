package com.wuhai.myframe2.ui.routable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NewUserActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle intentExtras = getIntent().getExtras();
        // Corresponds to the ":name" above
        String name = (String) intentExtras.get("name");
        // Corresponds to the ":zip" above
        String zip = (String) intentExtras.get("zip");

        Log.e("Router", "name="+name+",zip="+zip);
    }
}
