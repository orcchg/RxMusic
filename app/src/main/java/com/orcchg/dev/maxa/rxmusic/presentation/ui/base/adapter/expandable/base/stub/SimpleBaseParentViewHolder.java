package com.orcchg.dev.maxa.rxmusic.presentation.ui.base.adapter.expandable.base.stub;

import android.view.View;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.adapter.expandable.base.BaseParentViewHolder;

public class SimpleBaseParentViewHolder extends BaseParentViewHolder<ParentItemStub, ChildItemStub> {

    public SimpleBaseParentViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(ParentItemStub model) {
        // override in subclasses
    }
}
