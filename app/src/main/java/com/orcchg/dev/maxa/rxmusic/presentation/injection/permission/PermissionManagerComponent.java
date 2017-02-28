package com.orcchg.dev.maxa.rxmusic.presentation.injection.permission;

import com.orcchg.dev.maxa.rxmusic.presentation.PermissionManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PermissionManagerModule.class})
public interface PermissionManagerComponent {

    PermissionManager permissionManager();
}
