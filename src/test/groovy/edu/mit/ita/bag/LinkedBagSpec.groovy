package edu.mit.ita.bag

import edu.mit.ita.adt.Collection
import edu.mit.ita.adt.CollectionSpec

class LinkedBagSpec extends CollectionSpec {
    @Override
    protected Collection<String> newCollection() {
        return new LinkedBag<String>()
    }
}
