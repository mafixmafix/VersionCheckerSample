package tk.pallas.versioncheckersample;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import tk.pallas.versionchecker.VersionChecker;

public class MainActivity extends DaggerAppCompatActivity {

    public static final String TAG = "MainActivity";

    @Inject
    VersionChecker versionChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        versionChecker.start(this, TAG);
    }

    public void openDialogFragment() {
        DFragment dialogFragment = new DFragment();
        dialogFragment.setCancelable(false);
        dialogFragment.show(getSupportFragmentManager(), "DFragment");
    }
}
