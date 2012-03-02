package org.junita.core;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : Balaji Narain
 */
public class TargetProxy {

    public void invokeMethod(Method method, Object instance) throws InvocationTargetException, IllegalAccessException {
        method.invoke(instance);
    }
}
