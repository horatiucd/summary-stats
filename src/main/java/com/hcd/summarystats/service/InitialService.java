package com.hcd.summarystats.service;

import com.hcd.summarystats.domain.Parent;
import com.hcd.summarystats.domain.ParentStats;

import java.util.List;

public class InitialService implements Service {

    @Override
    public ParentStats getStats(List<Parent> parents) {
        int count = parents.size();
        int min = Integer.MAX_VALUE;
        int max = 0;
        int sum = 0;
        for (Parent human : parents) {
            int age = human.age();
            if (age < min) {
                min = age;
            }
            if (age > max) {
                max = age;
            }
            sum += age;
        }

        return new ParentStats(count, min, max, max - min, (double) sum/count, sum);
    }
}
