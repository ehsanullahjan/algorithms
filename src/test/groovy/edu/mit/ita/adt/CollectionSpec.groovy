package edu.mit.ita.adt

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
abstract class CollectionSpec extends Specification {
    def "New collection starts empty"() {
        given:
        Collection<String> collection = newCollection();

        expect:
        collection.isEmpty()
        collection.size() == 0
    }

    def "Can add elements to collection"() {
        given:
        Collection<String> collection = newCollection();

        when:
        collection.add("Johny")

        then:
        collection.size() == 1
        collection.contains("Johny")
    }

    def "Can remove elements from collection"() {
        given:
        Collection<String> collection = newCollection();

        when:
        collection.add("Johny")
        collection.add("Tommy")
        collection.remove("Johny")

        then:
        collection.size() == 1
        !collection.contains("Johny")
    }

    def "Can clear collection"() {
        given:
        Collection<String> collection = newCollection();

        when:
        collection.add("Johny")
        collection.add("Tommy")
        collection.clear()

        then:
        collection.isEmpty()
    }

    def "Can iterate collection"() {
        given:
        String[] names = ["Johny", "Tommy", "Manny"]
        Collection<String> collection = newCollection();

        when:
        names.each {collection.add(it)}

        then:
        collection.each {names.contains(it)}
    }

    abstract Collection<String> newCollection();
}
