package com.orcchg.dev.maxa.rxmusic.data.model.entity.music.mapper;

import com.orcchg.dev.maxa.rxmusic.data.model.entity.music.TotalValueEntity;
import com.orcchg.dev.maxa.rxmusic.domain.model.mapper.Mapper;
import com.orcchg.dev.maxa.rxmusic.domain.model.music.TotalValue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TotalValueMapper implements Mapper<TotalValueEntity, TotalValue> {

    @Inject
    public TotalValueMapper() {
    }

    @Override
    public TotalValue map(TotalValueEntity object) {
        return TotalValue.builder()
                .setValue(object.value())
                .build();
    }

    @Override
    public List<TotalValue> map(List<TotalValueEntity> list) {
        List<TotalValue> mapped = new ArrayList<>();
        for (TotalValueEntity entity : list) {
            mapped.add(map(entity));
        }
        return mapped;
    }
}
