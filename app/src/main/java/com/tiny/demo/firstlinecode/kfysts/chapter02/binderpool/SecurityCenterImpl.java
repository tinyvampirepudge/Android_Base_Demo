package com.tiny.demo.firstlinecode.kfysts.chapter02.binderpool;

import android.os.RemoteException;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/3/18 下午7:02
 */

public class SecurityCenterImpl extends ISecurityCenter.Stub {
    private static final char SECRET_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            chars[j] ^= SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
