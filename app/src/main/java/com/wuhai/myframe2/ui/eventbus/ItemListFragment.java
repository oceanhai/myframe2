package com.wuhai.myframe2.ui.eventbus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



public class ItemListFragment extends ListFragment
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Register
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Unregister
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        // 开启线程加载列表
        new Thread()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(2000); // 模拟延时
                    // 发布事件，在后台线程发的事件
                    EventBus.getDefault().post(new Event.ItemListEvent(Item.ITEMS));

                    //TODO eventInheritance默认是true 注册了这个事件父类的方法也会收到通知
                    //  所以如果我们不想父类收到通知，需要设置成false，需要如下操作
                    //  但这样就不是像getDefault 创建的是单例，所以估计需要自己在进行一下封装成单例
                    EventBus.builder().eventInheritance(false).build();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //子线程发送  UI线程更新view
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Event.ItemListEvent event)
    {
        Log.e(Enventbus1Activity.TAG, "收到消息onEventMainThread:" + event);
        setListAdapter(new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, event.getItems()));
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position,
                                long id)
    {
        super.onListItemClick(listView, view, position, id);
        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }

}
