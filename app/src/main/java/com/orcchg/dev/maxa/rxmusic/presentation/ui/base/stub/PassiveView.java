package com.orcchg.dev.maxa.rxmusic.presentation.ui.base.stub;

import com.orcchg.dev.maxa.rxmusic.presentation.ui.base.MvpPresenter;

/**
 * Passive View is not attached to any {@link MvpPresenter}. In other words, there is no
 * {@link MvpPresenter} instance that receives any user-events from a {@link PassiveView} or
 * manipulates it's view data. This view is completely passive and must receive such events
 * from any outer view, containing it.
 */
public interface PassiveView {
}
