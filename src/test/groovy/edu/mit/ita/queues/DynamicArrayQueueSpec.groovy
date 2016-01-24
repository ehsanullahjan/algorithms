package edu.mit.ita.queues

import edu.mit.ita.adt.Queue
import edu.mit.ita.adt.QueueSpec

class DynamicArrayQueueSpec extends QueueSpec {
    private static final int initialCapacity = 4

    def "Dynamic array queue grows on enqueue when full"() {
        given: "a full queue"
        Queue<String> queue = new DynamicArrayQueue<>(initialCapacity);
        queue.enqueue("Johny")
        queue.enqueue("Manny")
        queue.enqueue("Abby")
        queue.enqueue("Haley")

        when: "an item is enqueued in the queue"
        queue.enqueue("Tommy")

        then: "the queue grows to double its size"
        queue.size() == 5
        queue.capacity() == initialCapacity * 2
    }

    def "Dynamic array queue shrinks when full below 1/4th its capacity"() {
        given: "a half full queue"
        Queue<String> queue = new DynamicArrayQueue<>(initialCapacity);
        queue.enqueue("Johny")
        queue.enqueue("Manny")

        when: "queue consumption drops to 1/4th its capacity"
        queue.dequeue()

        then: "the queue shrinks to half its size"
        queue.size() == 1
        queue.capacity() == initialCapacity / 2 as int
    }

    @Override
    protected Queue<String> newQueue() {
        return new DynamicArrayQueue<>()
    }
}
