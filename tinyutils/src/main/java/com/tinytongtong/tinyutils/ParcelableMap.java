package com.tinytongtong.tinyutils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 序列化map供Bundle传递map使用
 *
 * @Author wangjianzhou
 * @Date 2019-08-01 11:39
 * @Version
 */
public class ParcelableMap implements Parcelable {
    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.map.size());
        for (Map.Entry<String, String> entry : this.map.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public ParcelableMap() {
    }

    protected ParcelableMap(Parcel in) {
        int mapSize = in.readInt();
        this.map = new HashMap<String, String>(mapSize);
        for (int i = 0; i < mapSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.map.put(key, value);
        }
    }

    public static final Creator<ParcelableMap> CREATOR = new Creator<ParcelableMap>() {
        @Override
        public ParcelableMap createFromParcel(Parcel source) {
            return new ParcelableMap(source);
        }

        @Override
        public ParcelableMap[] newArray(int size) {
            return new ParcelableMap[size];
        }
    };
}
