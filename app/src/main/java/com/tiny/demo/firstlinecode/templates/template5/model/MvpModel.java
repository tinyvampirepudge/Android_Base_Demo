package com.tiny.demo.firstlinecode.templates.template5.model;

import com.tiny.demo.firstlinecode.templates.template5.contract.MvpContract;

import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


public class MvpModel implements MvpContract.IModel {

    @Override
    public void getServerData(Map<String, String> params, Observer<ResponseBody> observer) {

    }
}
