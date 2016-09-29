package edu.mit.ita.queue

import edu.mit.ita.adt.Queue
import spock.lang.Specification

import static edu.mit.ita.queue.PriorityQueue.newMaxPriorityQueue
import static edu.mit.ita.queue.PriorityQueue.newMinPriorityQueue

class PriorityQueueSpec extends Specification {
    def "A new queue starts empty"() {
        given:
        Queue<Integer> queue = newMinPriorityQueue()

        expect:
        queue.isEmpty()
        queue.size() == 0
    }

    def "Regardless of insertion order, items are dequeued based on their priority"() {
        when:
        items.each { queue.enqueue(it) }

        then:
        queue.dequeue() == front

        where:
        items        | queue                 || front
        [7, 3, 9, 8] | newMinPriorityQueue() || 3
        [7, 3, 9, 8] | newMaxPriorityQueue() || 9
    }

    def "Peeking does not remove item off the queue"() {
        given:
        Queue<Integer> queue = newMaxPriorityQueue()

        when:
        queue.enqueue(3)
        queue.enqueue(1)
        queue.enqueue(4)

        then:
        queue.peek() == 4
        queue.size() == 3
    }

    def "Dequeue on empty queue throws exception"() {
        given:
        Queue<Integer> queue = newMaxPriorityQueue()

        when:
        queue.dequeue()

        then:
        thrown NoSuchElementException
    }

    def "Peeking empty queue throws exception"() {
        given:
        Queue<Integer> queue = newMinPriorityQueue()

        when:
        queue.peek()

        then:
        thrown NoSuchElementException
    }

    def "Can iterate queue elements (not priority order)"() {
        given:
        String[] elements = ["Johny", "Manny", "Tommy"]
        Queue<String> queue = newMinPriorityQueue();
        elements.each { queue.enqueue(it) }

        expect:
        int i = 0;
        for (String element : queue) {
            element == elements[i++];
        }
    }
}
