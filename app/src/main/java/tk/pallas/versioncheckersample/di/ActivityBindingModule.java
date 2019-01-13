package tk.pallas.versioncheckersample.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import tk.pallas.versioncheckersample.MainActivity;

@Module
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity mainActivity();
}
