package com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.manual;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.Book;

import java.util.List;

/**
 * Desc:    手写的AIDL文件
 * Created by tiny on 2018/3/5.
 * Time: 00:47
 * Version:
 */

public interface IBookManagerManual extends IInterface {
    public static final String DESCRIPTOR = "com.tiny.demo.firstlinecode.kfysts.chapter02.aidl.manual.IBookManagerManual";

    public static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    public static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;

    public static class BookManagerManualImpl extends Binder implements IBookManagerManual {
        /**
         * Construct the stub at attach it to the interface
         */
        public BookManagerManualImpl() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast as IBinder object into an IBookManagerManual interface, generating a proxy
         * if needed.
         *
         * @param obj
         * @return
         */
        public static IBookManagerManual asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && iin instanceof IBookManagerManual) {
                return (IBookManagerManual) iin;
            }
            return new BookManagerManualImpl.Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION:
                    reply.writeString(DESCRIPTOR);
                    return true;
                case TRANSACTION_getBookList:
                    data.enforceInterface(DESCRIPTOR);
                    List<Book> result = this.getBookList();
                    reply.writeNoException();
                    reply.writeTypedList(result);
                    return true;
                case TRANSACTION_addBook:
                    data.enforceInterface(DESCRIPTOR);
                    Book arg0;
                    if (0 != data.readInt()) {
                        arg0 = Book.CREATOR.createFromParcel(data);
                    } else {
                        arg0 = null;
                    }
                    this.addBook(arg0);
                    reply.writeNoException();
                    return true;

            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            // TODO: 2018/3/5 待实现
            return null;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            // TODO: 2018/3/5 待实现                                                               t
        }

        public static class Proxy implements IBookManagerManual {
            private IBinder mRemote;

            public Proxy(IBinder mRemote) {
                this.mRemote = mRemote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public List<Book> getBookList() throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                List<Book> result;
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(TRANSACTION_getBookList, data, reply, 0);
                    reply.readException();
                    result = reply.createTypedArrayList(Book.CREATOR);
                } finally {
                    reply.recycle();
                    data.recycle();
                }
                return result;
            }

            @Override
            public void addBook(Book book) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    if (book != null) {
                        data.writeInt(1);
                        book.writeToParcel(data, 0);
                    } else {
                        data.writeInt(0);
                    }
                    mRemote.transact(TRANSACTION_addBook, data, reply, 0);
                    reply.readException();
                } finally {
                    data.recycle();
                    reply.recycle();
                }

            }
        }
    }
}
