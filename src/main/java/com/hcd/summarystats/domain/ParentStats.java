package com.hcd.summarystats.domain;

public record ParentStats(long count,
                          int youngest,
                          int oldest,
                          int ageRange,
                          double averageAge,
                          long totalYearsOfAge) {
}
