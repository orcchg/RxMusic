package com.orcchg.dev.maxa.rxmusic.domain.model.mapper;

public interface Populator<From, To> {
    void populate(From from, To to);
}
