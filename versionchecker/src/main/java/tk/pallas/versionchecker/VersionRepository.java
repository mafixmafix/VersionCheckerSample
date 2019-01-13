package tk.pallas.versionchecker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.android.volley.toolbox.Volley;

class VersionRepository {

    static VersionRepository getInstance(Context context, String url) {
        return new VersionRepository(
                new LocalDataSource(PreferenceManager.getDefaultSharedPreferences(context)),
                new RemoteDataSource(Volley.newRequestQueue(context), url));
    }

    private final LocalDataSource mLocalDataSource;
    private final RemoteDataSource mRemoteDataSource;
    private final MutableLiveData<Resource<String>> mResult;

    private VersionRepository(LocalDataSource localDataSource,
                             RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;

        mResult = new MutableLiveData<>();
//        mResult.setValue(Resource.loading(null));
        mLocalDataSource.getData().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String string) {
                mResult.setValue(Resource.success(string));
            }
        });
        checkForUpdate();
    }

    private void checkForUpdate() {
        if (shouldUpdateFromRemote()) {
            updateLocalDataSource();
        }
    }

    private void updateLocalDataSource() {
//        mResult.setValue(Resource.loading(null));

        mRemoteDataSource.getData().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String response) {
                if (response != null) {
                    mLocalDataSource.saveData(response);
                } else {
//                    mResult.setValue(Resource.error("RemoteError", null));
                }
            }
        });
    }

    private boolean shouldUpdateFromRemote() {
        return true;
    }

    public LiveData<Resource<String>> getResult() {
        return mResult;
    }

}
