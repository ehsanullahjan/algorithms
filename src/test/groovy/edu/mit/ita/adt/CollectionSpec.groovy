package edu.mit.ita.adt

import edu.mit.ita.bags.LinkedBag
import spock.lang.Specification

class CollectionSpec extends Specification {
    def "New collection starts empty"() {
        expect:
        collection.isEmpty()
        collection.size() == 0

        where:
        collection              || dummy
        new LinkedBag<String>() || ""
    }

    def "Can add elements to collection"() {
        when:
        collection.add("Johny")

        then:
        collection.size() == 1
        collection.contains("Johny")

        where:
        collection              || dummy
        new LinkedBag<String>() || ""
    }

    def "Can remove elements from collection"() {
        when:
        collection.add("Johny")
        collection.add("Tommy")
        collection.remove("Johny")

        then:
        collection.size() == 1
        !collection.contains("Johny")

        where:
        collection              || dummy
        new LinkedBag<String>() || ""
    }

    def "Can clear collection"() {
        when:
        collection.add("Johny")
        collection.add("Tommy")
        collection.clear()

        then:
        collection.isEmpty()

        where:
        collection              || dummy
        new LinkedBag<String>() || ""
    }

    def "Can iterate collection"() {
        given:
        String[] names = ["Johny", "Tommy", "Manny"]

        when:
        names.each {collection.add(it)}

        then:
        collection.each {names.contains(it)}

        where:
        collection              || dummy
        new LinkedBag<String>() || ""
    }
}
