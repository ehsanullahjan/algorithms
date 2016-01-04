package edu.mit.ita.sorting

import spock.lang.Specification
import spock.lang.Unroll

class SortingSpec extends Specification {
    private static final Integer[] unorderedSeq = [5, 2, 3, 6, 14, 4, 7, 11, 10, 13, 0, 8, 15, 1, 9, 12];
    private static final Integer[] orderedSeq = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];

    @Unroll
    @SuppressWarnings("GroovyAssignabilityCheck")
    def "Items can be sorted using #method"() {
        when: "#method is applied to unsorted items"
        sort(items)

        then: "the items are sorted in place"
        items == result

        where:
        method           | sort                 | items                || result
        "insertion sort" | {Insertion.sort(it)} | unorderedSeq.clone() || orderedSeq
        "merge sort"     | {Merge.sort(it)}     | unorderedSeq.clone() || orderedSeq
    }
}
