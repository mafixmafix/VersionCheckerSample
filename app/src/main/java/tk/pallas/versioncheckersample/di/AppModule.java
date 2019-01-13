package tk.pallas.versioncheckersample.di;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import tk.pallas.versionchecker.Condition;
import tk.pallas.versionchecker.IVersion;
import tk.pallas.versionchecker.VersionChecker;
import tk.pallas.versionchecker.VersionCheckerClient;
import tk.pallas.versioncheckersample.App;
import tk.pallas.versioncheckersample.MainActivity;
import tk.pallas.versioncheckersample.ResultModel;

/**
 * Created by Mafix <ahangarani.amir@gmail.com>
 */

@Module
public abstract class AppModule {

    @Provides
    static VersionChecker provideVersionChecker(VersionCheckerClient versionCheckerClient) {
        return VersionChecker.Builder.getInstance()
                .versionCheckerClient(versionCheckerClient)
                .build();
    }

    @Provides
    static VersionCheckerClient provideVersionCheckerClient(Context context, Condition<ResultModel> condition) {
        return VersionCheckerClient
                .Builder
                .getInstance()
                .appContext(context)
                .condition(condition)
                .result(ResultModel.class)
                .url("https://reqres.in/api/users/2") // Fake REST_API :)
                .build();
    }

    @Provides
    static Condition<ResultModel> provideCondition() {
        return new Condition<ResultModel>() {

            @Override
            public void whatHappen(IVersion<ResultModel> version) {
                if (version.getNetworkInfo().isConnected() && version.getTag().equals(MainActivity.TAG)) {
                    if (version.getResult() != null) {
                        Log.d("VersionChecker", version.getResult().toString());
                        ((MainActivity) version.getActivity()).openDialogFragment();
                    }
                }
            }
        };
    }

    @Singleton
    @Binds
    abstract Context bindContext(App app);
}
