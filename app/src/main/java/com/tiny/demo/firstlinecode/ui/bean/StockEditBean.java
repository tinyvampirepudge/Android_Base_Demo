package com.tiny.demo.firstlinecode.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.tiny.demo.firstlinecode.common.bean.NoProguard;


/**
 * Desc:    自选编辑页面的javabean。
 * Created by tiny on 2017/10/27.
 * Version:
 */

public class StockEditBean extends NoProguard implements Parcelable {
    private String code;
    private String name;
    private boolean isChecked;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    public StockEditBean() {
    }

    protected StockEditBean(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Creator<StockEditBean> CREATOR = new Creator<StockEditBean>() {
        @Override
        public StockEditBean createFromParcel(Parcel source) {
            return new StockEditBean(source);
        }

        @Override
        public StockEditBean[] newArray(int size) {
            return new StockEditBean[size];
        }
    };

    @Override
    public String toString() {
        return "StockEditBean{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockEditBean that = (StockEditBean) o;

        if (isChecked != that.isChecked) return false;
        if (!code.equals(that.code)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (isChecked ? 1 : 0);
        return result;
    }
}
