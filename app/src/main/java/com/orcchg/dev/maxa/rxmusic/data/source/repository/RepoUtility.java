package com.orcchg.dev.maxa.rxmusic.data.source.repository;

import timber.log.Timber;

public class RepoUtility {

    public static void checkLimitAndOffset(int limit, int offset) {
        if (limit < 0 && offset != 0) {
            Timber.e("Negative limit is specified to fetch all items, offset must be equal to zero! Actual offset: %s", offset);
            throw new IllegalArgumentException("Wrong offset value, must be 0, when limit is negative!");
        }
    }

    public static void checkListBounds(int boundary, int total) {
        if (boundary >= total) {
            Timber.e("Boundary (offset + limit) %s exceeds total items count %s", boundary, total);
            throw new ArrayIndexOutOfBoundsException(boundary);
        }
    }
}
