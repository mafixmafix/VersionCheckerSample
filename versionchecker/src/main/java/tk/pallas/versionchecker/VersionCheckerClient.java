package tk.pallas.versionchecker;

import android.content.Context;
import android.support.annotation.NonNull;

import static tk.pallas.versionchecker.Utils.checkNotNull;

public class VersionCheckerClient implements IVersionCheckerClient {

    private final Context mAppContext;
    private final ICondition mCondition;
    private final Class mResultClass;
    private final String mUrl;

    private VersionCheckerClient(Context appContext, ICondition condition, Class resultClass, String url) {
        mAppContext = appContext.getApplicationContext();
        mCondition = condition;
        mResultClass = resultClass;
        mUrl = url;
    }

    @Override
    public Context getAppContext() {
        return mAppContext.getApplicationContext();
    }

    @Override
    public ICondition getAppCondition() {
        return mCondition;
    }

    @Override
    public Class getResultClass() {
        return mResultClass;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }

    //    private ILibraryPreferences getLibraryPreferences() {
//        return mLibraryPreferences;
//    }

    public static final class Builder {
//        private ILibraryPreferences mLibraryPreferences;
        private Context mAppContext;
        private ICondition mCondition;
        private Class mResultClass;
        private String mUrl;

        public Builder() {
        }

        public Builder(VersionCheckerClient versionCheckerClient) {
            mAppContext = versionCheckerClient.getAppContext().getApplicationContext();

        }

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder appContext(@NonNull Context context) {
            mAppContext = checkNotNull(context, "appContext == null").getApplicationContext();
            return this;
        }

        public Builder condition(@NonNull ICondition condition) {
            mCondition = checkNotNull(condition, "condition == null");
            return this;
        }

        public <T> Builder result(@NonNull Class<T> resultClass) {
            mResultClass = checkNotNull(resultClass, "resultClass == null");
            return this;
        }

        public Builder url(@NonNull String url) {
            mUrl = checkNotNull(url, "appContext == null");
            return this;
        }

//        public VersionCheckerClient.Builder libraryPreferences(@NonNull ILibraryPreferences libraryPreferences) {
//            mLibraryPreferences = checkNotNull(libraryPreferences, "client == null");
//            return this;
//        }

        public VersionCheckerClient build() {
            if (mAppContext == null) {
                throw new IllegalStateException("appContext required.");
            }
            if (mCondition == null) {
                throw new IllegalStateException("condition required.");
            }
            if (mResultClass == null) {
                throw new IllegalStateException("resultClass required.");
            }
            if (mUrl == null) {
                throw new IllegalStateException("url required.");
            }

            return new VersionCheckerClient(mAppContext, mCondition, mResultClass, mUrl);
        }
    }
}
