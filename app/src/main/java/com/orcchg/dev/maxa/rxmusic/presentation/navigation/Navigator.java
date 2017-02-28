package com.orcchg.dev.maxa.rxmusic.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.orcchg.dev.maxa.rxmusic.presentation.injection.PerActivity;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.music.detail.ArtistDetailsActivity;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list.ArtistListActivity;

import javax.inject.Inject;

@PerActivity
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void openListScreen(@NonNull Context context) {
        Intent intent = ArtistListActivity.getCallingIntent(context);
        context.startActivity(intent);
    }

    public void openDetailsScreen(@NonNull Context context, long artistId) {
        Intent intent = ArtistDetailsActivity.getCallingIntent(context, artistId);
        context.startActivity(intent);
    }
}
