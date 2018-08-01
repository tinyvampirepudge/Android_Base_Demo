// IBinderPool.aidl
package com.tiny.demo.firstlinecode.kfysts.chapter02.binderpool;

// Declare any non-default types here with import statements
import android.os.IBinder;

interface IBinderPool {

    /**
    *
    */
   IBinder queryBinder(int binderCode);
}
