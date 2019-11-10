package com.example.java_lib.designpattern.observer.observer2;

/**
 * 抽象主题角色，watched：被观察
 */
public interface Watched {
    void addWatcher(Watcher watcher);
    void removeWatcher(Watcher watcher);
    void notifyWatchers(String str);
}
