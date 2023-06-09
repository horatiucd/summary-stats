package com.hcd.summarystats.service;

import com.hcd.summarystats.domain.Parent;
import com.hcd.summarystats.domain.ParentStats;

import java.util.ArrayList;
import java.util.List;

public interface Service {

    ParentStats getStats(List<Parent> parents);

    default ParentStats getCombinedStats(List<Parent> mothers, List<Parent> fathers) {
        final List<Parent> parents = new ArrayList<>(mothers);
        parents.addAll(fathers);
        return getStats(parents);
    }
}
