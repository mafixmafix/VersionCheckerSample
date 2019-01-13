package tk.pallas.versioncheckersample.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Mafix <ahangarani.amir@gmail.com>
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
