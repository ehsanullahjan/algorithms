package edu.mit.ita.bags

import edu.mit.ita.adt.Collection
import edu.mit.ita.adt.CollectionSpec

class DynamicArrayBagSpec extends CollectionSpec {
    private static final int initialCapacity = 4

    def "Dynamic array bag grows on add when full"() {
        given: "a full bag"
        Collection<String> bag = new DynamicArrayBag<>(initialCapacity)
        bag.add("Johny")
        bag.add("Manny")
        bag.add("Abby")
        bag.add("Haley")

        when: "an item is added to the bag"
        bag.add("Tommy")

        then: "the bag grows to double its size"
        bag.size() == 5
        bag.capacity() == initialCapacity * 2
    }

    def "Dynamic array bag shrinks when full below 1/4th its capacity"() {
        given: "a half full bag"
        Collection<String> bag = new DynamicArrayBag<>(initialCapacity);
        bag.add("Johny")
        bag.add("Manny")

        when: "bag consumption drops to 1/4th its capacity"
        bag.remove("Manny")

        then: "the bag shrinks to half its size"
        bag.size() == 1
        bag.capacity() == initialCapacity / 2 as int
    }

    def "Can delete last element"() {
        Collection<String> bag = new DynamicArrayBag<>(initialCapacity)
        bag.add("Johny")
        bag.add("Manny")
        bag.add("Abby")
        bag.add("Haley")
        given:

        when:
        bag.remove("Haley")

        then:
        bag.size() == 3
        !bag.contains("Haley")
    }

    @Override
    protected Collection<String> newCollection() {
        return new DynamicArrayBag<String>()
    }
}
