package org.junita.model;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
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
