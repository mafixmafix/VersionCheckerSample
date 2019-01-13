package tk.pallas.versioncheckersample;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import tk.pallas.versioncheckersample.di.DaggerAppComponent;

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
