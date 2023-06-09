package com.hcd.summarystats.service;

import com.hcd.summarystats.domain.Parent;
import com.hcd.summarystats.domain.ParentStats;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collector;
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
        Collector<Parent, ?, IntSummaryStatistics> collector = Collectors.summarizingInt(Parent::age);

        IntSummaryStatistics stats = mothers.stream().collect(collector);
        stats.combine(fathers.stream().collect(collector));

        return new ParentStats(stats.getCount(),
                stats.getMin(),
                stats.getMax(),
                stats.getMax() - stats.getMin(),
                stats.getAverage(),
                stats.getSum());
    }
}
