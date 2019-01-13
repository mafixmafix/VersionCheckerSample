package tk.pallas.versionchecker;

import android.content.pm.PackageInfo;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by Mafix <ahangarani.amir@gmail.com>
 */

public class Version<R> implements IVersion<R> {

    public static <R> Version<R> getInstance(AppCompatActivity activity, PackageInfo packageInfo, NetworkInfo networkInfo, R result, String tag) {
        return new Version<>(activity, packageInfo, networkInfo, result, tag);
    }


    private final R mResult;
    private final PackageInfo mPackageInfo;
    private final NetworkInfo mNetworkInfo;
    private final WeakReference<AppCompatActivity> mActivity;
    private final String mTag;

    public Version(AppCompatActivity activity, PackageInfo packageInfo, NetworkInfo networkInfo, R result, String tag) {
        mActivity = new WeakReference<>(activity);
        mPackageInfo = packageInfo;
        mNetworkInfo = networkInfo;
        mResult = result;
        mTag = tag;
    }

    @Override
    public R getResult() {
        return mResult;
    }

    @Override
    public PackageInfo getPackageInfo() {
        return mPackageInfo;
    }

    @Override
    public NetworkInfo getNetworkInfo() {
        return mNetworkInfo;
    }

    @Override
    public AppCompatActivity getActivity() {
        return mActivity.get();
    }

    @Override
    public String getTag() {
        return mTag;
    }
}
