package com.orcchg.dev.maxa.rxmusic.presentation;

import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.orcchg.dev.maxa.rxmusic.BuildConfig;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.application.ApplicationComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.application.ApplicationModule;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.application.DaggerApplicationComponent;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import timber.log.Timber;

public class MusicApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("Application onCreate");
        initializeCrashlytics();
        initializeInjector();
        initializeLeakDetection();
        initializeLogger();
        initializeRealmEngine();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    /* Initialization */
    // --------------------------------------------------------------------------------------------
    private void initializeCrashlytics() {
        CrashlyticsCore core = new CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build();
        Fabric.with(this, new Crashlytics.Builder().core(core).build());
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return getPackageName() + ":" + super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        } else {
            Timber.plant(new CrashlyticsTree());
        }
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initializeRealmEngine() {
        Realm.init(this);
    }

    /* Crashlytics */
    // --------------------------------------------------------------------------------------------
    /**
     * {@see https://blog.xmartlabs.com/2015/07/09/Android-logging-with-Crashlytics-and-Timber/}
     *
     * Comment: {@link Timber.Tree} only supplies the tag when it was explicitly set.
     * In most cases, tag will be null. If you want the tag to be extracted from the log,
     * you need to extend {@link Timber.DebugTree} instead.
     */
    public class CrashlyticsTree extends Timber.DebugTree {
        private static final String CRASHLYTICS_KEY_PRIORITY = "priority";
        private static final String CRASHLYTICS_KEY_TAG = "tag";
        private static final String CRASHLYTICS_KEY_MESSAGE = "message";

        @Override
        protected void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable t) {
            if (priority == Log.VERBOSE) {
                return;
            }

            Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority);
            Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag);
            Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message);

            if (t == null) {
                Crashlytics.log(priority, tag, message);
            } else {
                Crashlytics.logException(t);
            }
        }
    }
}
