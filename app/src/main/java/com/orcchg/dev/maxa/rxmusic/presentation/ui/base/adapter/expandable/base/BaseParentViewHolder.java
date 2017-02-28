package com.orcchg.dev.maxa.rxmusic.presentation.ui.base.adapter.expandable.base;

import android.view.View;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

public abstract class BaseParentViewHolder<P extends BaseParentItem<C>, C extends BaseChildItem>
        extends ParentViewHolder<P, C> {

    public BaseParentViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(P model);
}
