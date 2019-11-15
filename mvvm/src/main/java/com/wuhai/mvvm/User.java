package com.wuhai.mvvm;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class User {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();
}
