package edu.mit.ita.queue

import edu.mit.ita.adt.Queue
import edu.mit.ita.adt.QueueSpec

class FixedArrayQueueSpec extends QueueSpec {
    def "Fixed array queue throws on enqueue when full"() {
        given: "a full queue"
        Queue<String> queue = new FixedArrayQueue<>(1);
        queue.enqueue("Johny")

        when: "an item is enqueued in the queue"
        queue.enqueue("Manny")

        then: "an exception is thrown"
        thrown IllegalStateException
    }

    @Override
    protected Queue<String> newQueue() {
        return new FixedArrayQueue<>(16)
    }
}
