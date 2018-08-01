// IOnNewBookArrivedListener.aidl
package com.tiny.demo.firstlinecode.kfysts.chapter02.aidl;

// Declare any non-default types here with import statements
import com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.Book;

interface IOnNewBookArrivedListener {
   void onNewBookArrived(in Book newBook);
}
