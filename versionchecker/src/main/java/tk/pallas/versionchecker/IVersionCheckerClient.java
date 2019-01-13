package tk.pallas.versionchecker;

import android.content.Context;

public interface IVersionCheckerClient {
    Context getAppContext();

    <R> ICondition<R> getAppCondition();

    Class getResultClass();

    String getUrl();
}
