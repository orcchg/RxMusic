package com.orcchg.dev.maxa.rxmusic.domain.model.mapper;

import java.util.List;

public interface DuplexMapper<From, To> extends Mapper<From, To> {
    From mapBack(To object);
    List<From> mapBack(List<To> list);
}
