package com.hcd.summarystats.service;

import com.hcd.summarystats.domain.Parent;
import com.hcd.summarystats.domain.ParentStats;
import com.hcd.summarystats.service.Service;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StatsService implements Service {

    @Override
    public ParentStats getStats(List<Parent> parents) {
        IntSummaryStatistics stats = parents.stream()
                .mapToInt(Parent::age)
                .summaryStatistics();

        return new ParentStats(stats.getCount(),
                stats.getMin(),
                stats.getMax(),
                stats.getMax() - stats.getMin(),
                stats.getAverage(),
                stats.getSum());
    }

    @Override
    public ParentStats getCombinedStats(List<Parent> mothers, List<Parent> fathers) {
        IntSummaryStatistics stats = mothers.stream()
                .collect(Collectors.summarizingInt(Parent::age));

        stats.combine(fathers.stream()
                .collect(Collectors.summarizingInt(Parent::age)));

        return new ParentStats(stats.getCount(),
                stats.getMin(),
                stats.getMax(),
                stats.getMax() - stats.getMin(),
                stats.getAverage(),
                stats.getSum());
    }
}
