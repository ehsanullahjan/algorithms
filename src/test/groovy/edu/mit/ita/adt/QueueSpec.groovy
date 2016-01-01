package edu.mit.ita.adt

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
abstract class QueueSpec extends Specification {
    def "A new queue starts empty"() {
        given:
        Queue<String> queue = newQueue()

        expect:
        queue.isEmpty()
        queue.size() == 0
    }

    def "First item enqueued is the first item dequeued"() {
        given:
        Queue<String> queue = newQueue()

        when:
        queue.enqueue("Johny")
        queue.enqueue("Manny")

        then:
        queue.dequeue() == "Johny"
        queue.size() == 1
    }

    def "Peeking does not remove item off the queue"() {
        given:
        Queue<String> queue = newQueue()

        when:
        queue.enqueue("Johny")
        queue.enqueue("Manny")

        then:
        queue.peek() == "Johny"
        queue.size() == 2
    }

    def "Dequeue on empty queue throws exception"() {
        given:
        Queue<String> queue = newQueue()

        when:
        queue.dequeue()

        then:
        thrown IllegalStateException
    }

    def "Peeking empty queue throws exception"() {
        given:
        Queue<String> queue = newQueue()

        when:
        queue.peek()

        then:
        thrown IllegalStateException
    }

    protected abstract Queue<String> newQueue();
}
