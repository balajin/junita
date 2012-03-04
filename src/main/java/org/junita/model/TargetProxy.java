package org.junita.model;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A utility to simplify mocking.
 *
 *   Method class is final, and it cannot be mocked.
 *
 * @author : Balaji Narain
 */
public class TargetProxy {

    public boolean invokeMethod(Method method, Object instance) throws InvocationTargetException, IllegalAccessException {
        if (method != null && instance != null) {
            method.invoke(instance);
            return true;
        }
        return false;
    }
}
