package com.freeray.otaku.entity.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DbDemo  implements Parcelable {
    @Id(autoincrement = true)
    private Long id;
    @Index(unique = true)
    private Integer goodsId;
    private String name;
    private String icon;
    private String info;
    private String type;
    public static final Creator<DbDemo> CREATOR = new Creator<DbDemo>() {
        @Override
        public DbDemo createFromParcel(Parcel in) {
            return new DbDemo(in);
        }

        @Override
        public DbDemo[] newArray(int size) {
            return new DbDemo[size];
        }
    };

    @Generated(hash = 2117289783)
    public DbDemo(Long id, Integer goodsId, String name, String icon, String info,
            String type) {
        this.id = id;
        this.goodsId = goodsId;
        this.name = name;
        this.icon = icon;
        this.info = info;
        this.type = type;
    }

    @Generated(hash = 1753064503)
    public DbDemo() {
    }

    public DbDemo(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
