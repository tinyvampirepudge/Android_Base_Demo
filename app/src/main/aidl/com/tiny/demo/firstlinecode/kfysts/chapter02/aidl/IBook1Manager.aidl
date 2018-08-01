// IBookManager.aidl
package com.tiny.demo.firstlinecode.kfysts.chapter02.aidl;

import com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.Book;
import com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.IOnNewBookArrivedListener;

interface IBook1Manager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
