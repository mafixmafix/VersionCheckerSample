package tk.pallas.versionchecker;

import android.content.pm.PackageInfo;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mafix <ahangarani.amir@gmail.com>
 */

public interface IVersion<R> {
    R getResult();

    PackageInfo getPackageInfo();

    NetworkInfo getNetworkInfo();

    AppCompatActivity getActivity();

    String getTag();
}
