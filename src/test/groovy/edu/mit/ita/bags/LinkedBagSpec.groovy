package edu.mit.ita.bags

import edu.mit.ita.adt.Collection
import edu.mit.ita.adt.CollectionSpec

class LinkedBagSpec extends CollectionSpec {
    @Override
    Collection<String> newCollection() {
        return new LinkedBag<String>()
    }
}
