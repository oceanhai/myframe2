package com.wuhai.lotteryticket.utils;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetNew<E> extends TreeSet<E> {

    @NonNull
    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.toString();
            sb.append(',');
        }

    }
}
