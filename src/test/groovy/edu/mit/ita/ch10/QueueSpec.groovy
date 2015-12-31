package edu.mit.ita.ch10

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class QueueSpec extends Specification {
    def "A new queue starts empty"() {
        expect:
        queue.isEmpty()
        queue.size() == 0

        where:
        queue                           || dummy
        new FixedArrayQueue<String>(16) || ""
    }

    def "First item enqueued is the first item dequeued"() {
        when:
        queue.enqueue("Johny")
        queue.enqueue("Manny")

        then:
        queue.dequeue() == "Johny"
        queue.size() == 1

        where:
        queue                           || dummy
        new FixedArrayQueue<String>(16) || ""
    }

    def "Peeking does not remove item off the queue"() {
        when:
        queue.enqueue("Johny")
        queue.enqueue("Manny")

        then:
        queue.peek() == "Johny"
        queue.size() == 2

        where:
        queue                           || dummy
        new FixedArrayQueue<String>(16) || ""
    }

    def "Dequeue on empty queue throws exception"() {
        when:
        queue.dequeue()

        then:
        thrown IllegalStateException

        where:
        queue                           || dummy
        new FixedArrayQueue<String>(16) || ""
    }

    def "Peeking empty queue throws exception"() {
        when:
        queue.peek()

        then:
        thrown IllegalStateException

        where:
        queue                           || dummy
        new FixedArrayQueue<String>(16) || ""
    }
}
