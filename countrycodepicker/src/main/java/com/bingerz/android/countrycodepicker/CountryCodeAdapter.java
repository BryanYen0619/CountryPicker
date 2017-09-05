package com.bingerz.android.countrycodepicker;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryCodeAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private ArrayList<CountryCode> mDataList = new ArrayList<>();

    CountryCodeAdapter(Context ctx, ArrayList<CountryCode> list) {
        this.mContext = ctx;
        this.mDataList = list;
        mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_country_code_list, null);
            holder = new ViewHolder();
            holder.ivCountryIcon = convertView.findViewById(R.id.iv_list_country_icon);
            holder.tvCountryName = convertView.findViewById(R.id.tv_list_country_name);
            holder.tvCountryCode = convertView.findViewById(R.id.tv_list_country_code);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CountryCode country = mDataList.get(position);

        String fileName = String.format("f%03d", country.mFlagId);
        int mResId = mContext.getResources().getIdentifier(fileName, "drawable", mContext.getPackageName());
        holder.ivCountryIcon.setImageResource(mResId);
        holder.tvCountryName.setText(country.mName);
        holder.tvCountryCode.setText(country.getCountryCodeStr());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView ivCountryIcon;
        TextView tvCountryName;
        TextView tvCountryCode;
    }
}
