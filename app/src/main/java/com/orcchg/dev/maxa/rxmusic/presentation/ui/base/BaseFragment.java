package com.orcchg.dev.maxa.rxmusic.presentation.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.orcchg.dev.maxa.rxmusic.presentation.MusicApplication;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.application.ApplicationComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.navigation.DaggerNavigationComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.navigation.NavigationComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.shared_prefs.DaggerSharedPrefsManagerComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.shared_prefs.SharedPrefsManagerComponent;
import com.orcchg.dev.maxa.rxmusic.presentation.injection.shared_prefs.SharedPrefsManagerModule;
import com.orcchg.dev.maxa.rxmusic.presentation.navigation.NavigatorHolder;

import hugo.weaving.DebugLog;
import timber.log.Timber;

public abstract class BaseFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends Fragment implements MvpView {

    protected P presenter;
    protected NavigationComponent navigationComponent;
    protected SharedPrefsManagerComponent sharedPrefsManagerComponent;

    private NavigatorHolder navigatorHolder = new NavigatorHolder();

    private boolean isStateRestored = false;

    @NonNull
    protected abstract P createPresenter();

    protected abstract void injectDependencies();

    @DebugLog @Override @SuppressWarnings("unchecked")
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(getClass().getSimpleName());
        Timber.i("onCreate(fragment=%s)", hashCode());
        isStateRestored = savedInstanceState != null;
        injectNavigator();
        injectSharedPrefsManager();
        injectDependencies();
        presenter = createPresenter();
        presenter.attachView((V) this);
        presenter.onCreate(savedInstanceState);
    }

    @DebugLog @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.tag(getClass().getSimpleName());
        Timber.i("onActivityResult(fragment=%s)", hashCode());
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.tag(getClass().getSimpleName());
        Timber.i("onStart(fragment=%s)", hashCode());
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(getClass().getSimpleName());
        Timber.i("onResume(fragment=%s)", hashCode());
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(getClass().getSimpleName());
        Timber.i("onPause(fragment=%s)", hashCode());
        presenter.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.tag(getClass().getSimpleName());
        Timber.i("onSaveInstanceState(fragment=%s)", hashCode());
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.tag(getClass().getSimpleName());
        Timber.i("onStop(fragment=%s)", hashCode());
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.tag(getClass().getSimpleName());
        Timber.i("onDestroy(fragment=%s)", hashCode());
        presenter.onDestroy();
        presenter.detachView();
    }

    /* Component */
    // --------------------------------------------------------------------------------------------
    protected ApplicationComponent getApplicationComponent() {
        return ((MusicApplication) getActivity().getApplication()).getApplicationComponent();
    }

    public SharedPrefsManagerComponent getSharedPrefsManagerComponent() {
        return sharedPrefsManagerComponent;
    }

    /* Internal */
    // --------------------------------------------------------------------------------------------
    @DebugLog
    protected boolean isStateRestored() {
        return isStateRestored;
    }

    private void injectNavigator() {
        navigationComponent = DaggerNavigationComponent.create();
        navigationComponent.inject(navigatorHolder);
    }

    private void injectSharedPrefsManager() {
        sharedPrefsManagerComponent = DaggerSharedPrefsManagerComponent.builder()
                .sharedPrefsManagerModule(new SharedPrefsManagerModule(getActivity()))
                .build();
    }
}
