package com.tiny.demo.firstlinecode.kfysts.chapter02.binderpool;

import android.os.RemoteException;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/3/18 下午7:02
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
