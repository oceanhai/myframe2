package com.wuhai.myframe2.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
   private MutableLiveData<Integer> counterLiveData = new MutableLiveData<>();
   private int counter = 0;

   public CounterViewModel() {
      counterLiveData.setValue(counter);
   }

   public MutableLiveData<Integer> getCounterLiveData() {
      return counterLiveData;
   }

   public void incrementCounter() {
      counter++;
      counterLiveData.setValue(counter);
   }
}

