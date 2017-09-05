# CountryPicker
--------
![alt text](https://github.com/BryanYen0619/CountryPicker/sdreenshot/device-2017-09-05-110311.png)

* 適用於Android項目的國家代碼選擇器。

* 國家代碼的數據參考了谷歌、Facebook等一些應用中的數據，逐條驗證過信息（國家名稱，國旗，國家代碼等）的準確性，並查漏補缺，最終數據來源於WiKi。
	
* 收入`238`國家數

## 使用

### API
```java
/**
 * 跳轉到選擇器Activity畫面
 */
new CountryCodePicker().start(this);

/**
 * 在當下的界面接收選擇的國家代號
 */
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
        case CountryCodePicker.REQUEST_CODE_PICKER:
            if (data == null) {
                Toast.makeText(this, "Country is null", Toast.LENGTH_SHORT).show();
                return;
            }
            CountryCode countryCode = data.getParcelableExtra(CountryCodePicker.EXTRA_CODE);
            if (countryCode != null) {
                //這邊加入自己的程式碼，處理返回結果
                Toast.makeText(this, countryCode.mCountryCode + "", Toast.LENGTH_SHORT).show();
            }
            break;
    }
}
```

## 如何使用

### Integration
依據步驟加入專案。

* Step 1. Add the JitPack repository to your build file

	Add it in your root build.gradle at the end of repositories:
	
	```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

* Step 2. Add the dependency

	```groovy
	dependencies {
        compile 'com.github.BryanYen0619:CountryPicker:-SNAPSHOT'
	}
	```
## 詳細說明

1. Clone來源：[bingerz/CountryCodePicker](https://github.com/bingerz/CountryCodePicker)
2. 加入多國語系支援，目前支援EN, 繁中, 簡中
3. 加入搜尋欄，參考 : [mukeshsolanki/country-picker-android](https://github.com/mukeshsolanki/country-picker-android) 