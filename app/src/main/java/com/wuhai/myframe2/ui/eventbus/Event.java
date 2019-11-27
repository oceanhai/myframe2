package com.wuhai.myframe2.ui.eventbus;

import java.util.List;

public class Event extends BaseEvent
{
    /** 列表加载事件 */
    public static class ItemListEvent
    {
        private List<Item> items;

        public ItemListEvent(List<Item> items)
        {
            this.items = items;
        }

        public List<Item> getItems()
        {
            return items;
        }
    }

}
