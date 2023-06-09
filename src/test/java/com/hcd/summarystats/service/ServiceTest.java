package com.hcd.summarystats.service;

import com.hcd.summarystats.domain.Parent;
import com.hcd.summarystats.domain.ParentStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class ServiceTest {

    private Service service;

    private List<Parent> mothers;
    private List<Parent> fathers;
    private List<Parent> parents;

    protected abstract Service setupService();

    @BeforeEach
    void setup() {
        service = setupService();

        mothers = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> new Parent("Mother" + i, i + 10))
                .collect(Collectors.toList());

        fathers = IntStream.rangeClosed(4, 5)
                .mapToObj(i -> new Parent("Father" + i, i + 10))
                .collect(Collectors.toList());

        parents = new ArrayList<>(mothers);
        parents.addAll(fathers);
    }

    private void assertHumanStats(ParentStats stats) {
        Assertions.assertNotNull(stats);
        Assertions.assertEquals(5, stats.count());
        Assertions.assertEquals(11, stats.youngest());
        Assertions.assertEquals(15, stats.oldest());
        Assertions.assertEquals(4, stats.ageRange());
        Assertions.assertEquals((double) 65/5, stats.averageAge());
        Assertions.assertEquals(11 + 12 + 13 + 14 + 15, stats.totalYearsOfAge());
    }

    @Test
    void getStats() {
        final ParentStats stats = service.getStats(parents);
        assertHumanStats(stats);
    }

    @Test
    void getCombinedStats() {
        final ParentStats stats = service.getCombinedStats(mothers, fathers);
        assertHumanStats(stats);
    }
}
