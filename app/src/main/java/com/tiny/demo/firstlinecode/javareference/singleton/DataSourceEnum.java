package com.tiny.demo.firstlinecode.javareference.singleton;

/**
 * Desc:
 * Created by tiny on 2017/12/18.
 * Version:
 */

public enum DataSourceEnum {
    DATASOURCE;

    private DBConnection connection = null;

    DataSourceEnum() {
        connection = new DBConnection();
    }

    public DBConnection getConnection() {
        return connection;
    }
}
