package com.tiny.demo.firstlinecode.templates.temp1.model;

import com.tiny.demo.firstlinecode.templates.temp1.contract.MvpTestContract;

import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


public class MvpTestModel implements MvpTestContract.IModel {

    @Override
    public void getServerData(Map<String, String> params, Observer<ResponseBody> observer) {

    }
}
