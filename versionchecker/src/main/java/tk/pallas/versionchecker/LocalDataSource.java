package tk.pallas.versionchecker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;

class LocalDataSource {
    private final SharedPreferences mSharedPreferences;
    private final String KEY = "KEY";
    private final MutableLiveData<String> mResult;
    private final SharedPreferences.OnSharedPreferenceChangeListener mListener;

    LocalDataSource(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        mResult = new MutableLiveData<>();
        mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key == KEY) {
                    String res = sharedPreferences.getString(key, null);
                    mResult.setValue(res);
                }
            }
        };
        mSharedPreferences.registerOnSharedPreferenceChangeListener(mListener);
        String res = mSharedPreferences.getString(KEY, null);
        mResult.setValue(res);
    }
    public LiveData<String> getData() {
        return mResult;
    }

    void saveData(String result) {
        mSharedPreferences.edit().putString(KEY, result).apply();
    }
}
