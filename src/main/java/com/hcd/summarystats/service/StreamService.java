package com.hcd.summarystats.service;

import com.hcd.summarystats.domain.Parent;
import com.hcd.summarystats.domain.ParentStats;
import com.hcd.summarystats.service.Service;

import java.util.List;

public class StreamService implements Service {

    @Override
    public ParentStats getStats(List<Parent> parents) {
        int count = parents.size();

        int min = parents.stream()
                .mapToInt(Parent::age)
                .min()
                .orElseThrow(RuntimeException::new);

        int max = parents.stream()
                .mapToInt(Parent::age)
                .max()
                .orElseThrow(RuntimeException::new);

        int sum = parents.stream()
                .mapToInt(Parent::age)
                .sum();

        return new ParentStats(count, min, max, max - min, (double) sum/count, sum);
    }
}
