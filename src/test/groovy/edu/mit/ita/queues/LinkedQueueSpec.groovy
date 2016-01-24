package edu.mit.ita.queues

import edu.mit.ita.adt.Queue
import edu.mit.ita.adt.QueueSpec

class LinkedQueueSpec extends QueueSpec {
    @Override
    protected Queue<String> newQueue() {
        return new LinkedQueue<>();
    }
}
