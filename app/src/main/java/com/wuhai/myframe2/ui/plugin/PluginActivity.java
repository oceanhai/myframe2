package com.wuhai.myframe2.ui.plugin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.PathClassLoader;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：
 * Android插件化浅析
 * https://blog.csdn.net/u012124438/article/details/51814867
 *
 * 我感觉就是同一个sharedUserId下资源共享，不知道我这样理解是否妥？
 */
public class PluginActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mListview;
    private RelativeLayout mRelativeLayout;


    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PluginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_plugin);

        Button button = (Button) findViewById(R.id.button);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow(view);
            }


        });

    }

    @Override
    public void setStatistical() {

    }

    private void showPopWindow(View v) {
        View popview = getLayoutInflater().inflate(R.layout.popwindow_layout, null);
        ListView listView = (ListView) popview.findViewById(R.id.listview);
        PopupWindow popupwindow = new PopupWindow(popview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupwindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.global_bg_blue1));
        popupwindow.setFocusable(true);
        popupwindow.setOutsideTouchable(true);

        List<Map<String, String>> pluginList = findPluginList();

        if (pluginList == null || pluginList.size() == 0) {
            Toast.makeText(this, "手机里并没有插件哦！", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, pluginList, android.R.layout.simple_list_item_1, new String[]{"label"}, new int[]{android.R.id.text1});
        listView.setAdapter(simpleAdapter);

        popupwindow.setHeight(100 * pluginList.size());
        popupwindow.setWidth(300);
        popupwindow.showAsDropDown(v);

        listView.setOnItemClickListener(this);
    }

    private List<Map<String, String>> findPluginList() {
        List<Map<String, String>> pluginList = new ArrayList<Map<String, String>>();
        //如何获取插件列表？
        PackageManager packageManager = this.getPackageManager();
        //获取已经安卓的app
        List<PackageInfo> packages = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);

        //获取当前应用的包信息
        try {
            PackageInfo currentPackageInfo = packageManager.getPackageInfo(getPackageName(), 0);

            for (PackageInfo packageInfo : packages) {
                String packageName = packageInfo.packageName;
                String shareUserId = packageInfo.sharedUserId;
                //判断当前的包，是不是我们需要的插件
                //如果是以下三种情况，就不是我们的插件，直接返回
                if (currentPackageInfo.packageName.equals(packageName) || !currentPackageInfo.sharedUserId.equals(shareUserId) || TextUtils.isEmpty(shareUserId)) {
                    continue;
                }
                //就是我们的插件
                Map<String, String> pluginMap = new HashMap<String, String>();
                //获取应用程序的名字
                String label = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                pluginMap.put("packageName", packageName);
                pluginMap.put("label", label);
                pluginList.add(pluginMap);

                Log.e(TAG, "pluginMap " + pluginMap.toString());
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return pluginList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //点击插件，加载资源
        //资源需要通过资源加载器进行加载－－context
        //记住是plugin的context
        //1.获取插件的上下文

        Context pluginContext = findPluginContext(position);
        //2.从插件上下文加载资源
        int resId = findResoucesId(pluginContext, position);
        if (resId != 0) {
            Drawable drawable = pluginContext.getResources().getDrawable(resId);
            mRelativeLayout.setBackgroundDrawable(drawable);

        }
    }

    private Context findPluginContext(int position) {
        Map<String, String> map = this.findPluginList().get(position);
        String packageName = map.get("packageName");
        try {
            return createPackageContext(packageName, CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int findResoucesId(Context pluginContext, int position) {
        //使用反射机制
        ClassLoader classLoader = new PathClassLoader(pluginContext.getPackageResourcePath(), PathClassLoader.getSystemClassLoader());
        String pluginPackageName = this.findPluginList().get(position).get("packageName");
        try {
            //获取R下的静态类drawable
            Class<?> drawableClass = Class.forName(pluginPackageName + ".R$drawable", true, classLoader);
            //获取里面的属性
            Field[] fields = drawableClass.getFields();
            for (Field field : fields) {
                //获取属性名称
                String name = field.getName();
                if ("icon1".equals(name)) {
                    //获取资源的id
                    return field.getInt(R.drawable.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
