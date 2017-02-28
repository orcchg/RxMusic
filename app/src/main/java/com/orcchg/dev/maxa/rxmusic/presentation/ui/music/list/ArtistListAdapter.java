package com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orcchg.dev.maxa.rxmusic.R;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.adapter.BaseAdapter;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.music.list.viewholder.ArtistViewHolder;
import com.orcchg.dev.maxa.rxmusic.presentation.ui.viewobject.ArtistListItemVO;

class ArtistListAdapter extends BaseAdapter<ArtistViewHolder, ArtistListItemVO> {

    private final ItemClickListener listener;

    ArtistListAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArtistViewHolder createModelViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_musician, parent, false);
        return new ArtistViewHolder(view, listener);
    }
}
