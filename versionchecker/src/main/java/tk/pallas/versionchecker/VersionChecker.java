package tk.pallas.versionchecker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.google.gson.Gson;

import static tk.pallas.versionchecker.Utils.checkNotNull;

public class VersionChecker {

    private final IVersionCheckerClient mVersionCheckerClient;
    private final Gson mGson;
    private final ConnectivityManager mConnectivityManager;
    private final PackageInfo mPackageInfo;
    private final VersionRepository versionRepository;

    private VersionChecker(IVersionCheckerClient versionCheckerClient) {
        mVersionCheckerClient = versionCheckerClient;
        mGson = new Gson();
        mConnectivityManager = resolveConnectivityManager();
        mPackageInfo = resolvePackageInfo();
        versionRepository = VersionRepository.getInstance(getAppContext(), getUrl());
    }

    private String getUrl() {
        return mVersionCheckerClient.getUrl();
    }

    public void start(final AppCompatActivity activity, final String tag) {
        getLiveResult().observe(activity, new Observer<Resource<String>>() {
            @Override
            public void onChanged(@Nullable Resource<String> resource) {
                String result = resource.data;
                Version<Object> version = Version.getInstance(
                        activity,
                        getPackageInfo(),
                        getNetworkInfo(),
                        resultAdapter(result, getResultClass()),
                        tag);
                getAppCondition().whatHappen(version);
            }
        });
    }

    private IVersionCheckerClient getVersionCheckerClient() {
        return mVersionCheckerClient;
    }

    private Context getAppContext() {
        return mVersionCheckerClient.getAppContext();
    }

    private ConnectivityManager resolveConnectivityManager() {
        return (ConnectivityManager) getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private PackageInfo resolvePackageInfo() {
        Context context = getAppContext();
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo;
    }

    private ICondition<Object> getAppCondition() {
        return mVersionCheckerClient.getAppCondition();
    }

    private Class getResultClass() {
        return mVersionCheckerClient.getResultClass();
    }

    private Object resultAdapter(String result, Class resultClass) {
        return mGson.fromJson(result, resultClass);
    }

    private NetworkInfo getNetworkInfo() {
        return mConnectivityManager.getActiveNetworkInfo();
    }

    private PackageInfo getPackageInfo() {
        return mPackageInfo;
    }

    private LiveData<Resource<String>> getLiveResult() {
        return versionRepository.getResult();
    }

    public static final class Builder {

        public static Builder getInstance() {
            return new Builder();
        }

        private IVersionCheckerClient mVersionCheckerClient;

        public Builder() {
        }

        public Builder(VersionChecker versionChecker) {
            mVersionCheckerClient = versionChecker.getVersionCheckerClient();
        }

        public Builder versionCheckerClient(@NonNull IVersionCheckerClient versionCheckerClient) {
            mVersionCheckerClient = checkNotNull(versionCheckerClient, "versionCheckerClient == null");
            return this;
        }

        public VersionChecker build() {
            if (mVersionCheckerClient == null) {
                throw new IllegalStateException("Client required.");
            }
            return new VersionChecker(mVersionCheckerClient);
        }
    }
}
