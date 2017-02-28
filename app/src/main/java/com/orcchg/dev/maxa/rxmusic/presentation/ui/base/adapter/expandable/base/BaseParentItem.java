package com.orcchg.dev.maxa.rxmusic.presentation.ui.base.adapter.expandable.base;

import com.bignerdranch.expandablerecyclerview.model.Parent;

public abstract class BaseParentItem<C extends BaseChildItem> implements Parent<C> {

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
