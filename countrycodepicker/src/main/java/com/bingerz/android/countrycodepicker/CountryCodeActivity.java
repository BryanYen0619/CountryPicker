package com.bingerz.android.countrycodepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hanbing on 15/5/13.
 */
public class CountryCodeActivity extends Activity {
    private EditText searchEditText;

    private ArrayList<CountryCode> mOriginCountryCodes;
    private ArrayList<CountryCode> mCountryCodes;

    protected CountryCodeAdapter mAdapter;

    private String searchKeyword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_code);
        initView();
        parseCountryCodeData();
    }

    private void initView() {
        mCountryCodes = new ArrayList<>();
        mOriginCountryCodes = new ArrayList<>();
        mAdapter = new CountryCodeAdapter(this, mCountryCodes);
        ListView mListView = findViewById(R.id.lv_list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CountryCode country = mCountryCodes.get(position);
                Intent intent = new Intent();
                intent.putExtra(CountryCodePicker.EXTRA_CODE, country);
                CountryCodeActivity.this.setResult(RESULT_OK, intent);
                CountryCodeActivity.this.finish();
            }
        });
        searchEditText = findViewById(R.id.editText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listViewFilter(searchEditText.getText().toString());
            }
        });
    }

    private void parseCountryCodeData() {
        ArrayList<CountryCode> countryCodes = new ArrayList<>();
        try {
            for (int i = 0; i <= 238; i++) {    // 國家數量: 238
                String fileName = String.format("c%03d", i);
                int mResId = getResources().getIdentifier(fileName, "array", getPackageName());
                String[] codeArray = getResources().getStringArray(mResId);
                int code = Integer.parseInt(codeArray[1]);
                countryCodes.add(new CountryCode(i, codeArray[0], codeArray[2], code));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (!countryCodes.isEmpty()) {
            mCountryCodes.clear();
            mCountryCodes.addAll(countryCodes);
            mOriginCountryCodes.clear();
            mOriginCountryCodes.addAll(countryCodes);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void listViewFilter(String keyword) {
        if (!searchKeyword.equals(keyword)) {
            ArrayList<CountryCode> newArrayList = new ArrayList<>();

            for (int i = 0; i < mOriginCountryCodes.size(); i++) {
                if (mOriginCountryCodes.get(i).getName().contains(keyword)) {
                    newArrayList.add(mOriginCountryCodes.get(i));
                }
                if (mOriginCountryCodes.get(i).getRegionCode().contains(keyword.toUpperCase())) {
                    newArrayList.add(mOriginCountryCodes.get(i));
                }
            }

            mCountryCodes.clear();
            mCountryCodes.addAll(newArrayList);
            mAdapter.notifyDataSetChanged();

            searchKeyword = keyword;
        }
    }
}
