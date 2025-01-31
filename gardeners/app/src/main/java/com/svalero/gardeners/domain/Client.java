package com.svalero.gardeners.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class Client implements Parcelable {

    private long id;
    private String name;
    private String phone;
    private String address;
    private int gardenSize;
    private LocalDate contractEnd;
    private boolean vip;
    private double latitude;
    private double longitude;

    public Client(long id, String name, String phone, String address, int gardenSize, LocalDate contractEnd, boolean vip, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gardenSize = gardenSize;
        this.contractEnd = contractEnd;
        this.vip = vip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Client(Parcel in) {
        id = in.readLong();
        name = in.readString();
        phone = in.readString();
        address = in.readString();
        gardenSize = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(int gardenSize) {
        this.gardenSize = gardenSize;
    }

    public LocalDate getContractEnd() {
        return contractEnd;
    }

    public void setContractEnd(LocalDate contractEnd) {
        this.contractEnd = contractEnd;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeInt(gardenSize);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}

