// ISecurityCenter.aidl
package com.tiny.demo.firstlinecode.kfysts.chapter02.binderpool;

// Declare any non-default types here with import statements

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}
