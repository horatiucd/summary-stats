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
                .mapToObj(i -> new Parent("Mother" + i, i + 30))
                .collect(Collectors.toList());

        fathers = IntStream.rangeClosed(4, 6)
                .mapToObj(i -> new Parent("Father" + i, i + 30))
                .collect(Collectors.toList());

        parents = new ArrayList<>(mothers);
        parents.addAll(fathers);
    }

    private void assertParentStats(ParentStats stats) {
        Assertions.assertNotNull(stats);
        Assertions.assertEquals(6, stats.count());
        Assertions.assertEquals(31, stats.youngest());
        Assertions.assertEquals(36, stats.oldest());
        Assertions.assertEquals(5, stats.ageRange());

        final int sum = 31 + 32 + 33 + 34 + 35 + 36;

        Assertions.assertEquals((double) sum/6, stats.averageAge());
        Assertions.assertEquals(sum, stats.totalYearsOfAge());
    }

    @Test
    void getStats() {
        final ParentStats stats = service.getStats(parents);
        assertParentStats(stats);
    }

    @Test
    void getCombinedStats() {
        final ParentStats stats = service.getCombinedStats(mothers, fathers);
        assertParentStats(stats);
    }
}
