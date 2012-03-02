package org.junita.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Balaji Narain
 */
public class AnnotatedMethodFinder {

    private Class classUnderTest;

    public AnnotatedMethodFinder(Class classUnderTest) {
        this.classUnderTest = classUnderTest;
    }

    public List<Method> annotatedMethods(Class<? extends Annotation> annotationClass) {
        List<Method> result = new ArrayList<Method>();
        for (Method method : classUnderTest.getMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                result.add(method);
            }
        }
        return result;
    }
}
