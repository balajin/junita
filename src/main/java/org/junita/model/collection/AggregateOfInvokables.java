package org.junita.model.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of either of the following three
 *
 *  1. Test methods
 *  2. Enclosed Tess
 *  3. Data Modifiers
 *
 * @author : Balaji Narain
 */
public class AggregateOfInvokables<T> {

    protected List<T> invokables;

    protected AggregateOfInvokables() {
        invokables = new ArrayList<T>();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return invokables.isEmpty();
    }

    public int size() {
        return invokables.size();
    }

    public AggregateOfInvokables<T> add(T target) {
        invokables.add(target);
        return this;
    }
}
