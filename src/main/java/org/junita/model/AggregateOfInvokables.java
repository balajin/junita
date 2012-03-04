package org.junita.model;

import java.util.ArrayList;
import java.util.List;

/**
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
