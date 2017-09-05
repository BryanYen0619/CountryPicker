/*
 * Copyright (c) 2014 Amberfog.
 *
 * This source code is Amberfog Confidential Proprietary
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse
 * engineer the software. Otherwise this violation would be treated by law and
 * would be subject to legal prosecution. Legal use of the software provides
 * receipt of a license from the right holder only.
 */

package com.bingerz.android.countrycodepicker;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryCode implements Parcelable {

    int mFlagId;
    public int mCountryCode;
    private int mPriority;

    String mName;
    private String mRegionCode;
    private String sortLetters;

    private CountryCode() {
    }

    CountryCode(int id, String countryName, String region, int country) {
        mFlagId = id;
        mName = countryName;
        mRegionCode = region;
        mCountryCode = country;
    }

    String getCountryCodeStr() {
        return "+" + mCountryCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mRegionCode);
        dest.writeInt(this.mCountryCode);
        dest.writeInt(this.mPriority);
        dest.writeInt(this.mFlagId);
        dest.writeString(this.sortLetters);
    }

    private CountryCode(Parcel in) {
        this.mName = in.readString();
        this.mRegionCode = in.readString();
        this.mCountryCode = in.readInt();
        this.mPriority = in.readInt();
        this.mFlagId = in.readInt();
        this.sortLetters = in.readString();
    }

    public static final Creator<CountryCode> CREATOR = new Creator<CountryCode>() {
        public CountryCode createFromParcel(Parcel source) {
            return new CountryCode(source);
        }

        public CountryCode[] newArray(int size) {
            return new CountryCode[size];
        }
    };

    public String getName() {
        return mName;
    }

    String getRegionCode() {
        return mRegionCode;
    }
}
