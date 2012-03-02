package org.junita;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junita.support.AnnotatedMethodFinder;
import org.junita.support.Instantiator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Balaji Narain
 */
public class TestMethodExecutor {

    private Instantiator instantiator;
    private List<Class<? extends Annotation>> priorAnnotations;
    private List<Class<? extends Annotation>> posteriorAnnotations;

    public TestMethodExecutor() {
        this(new Instantiator());
    }

    public TestMethodExecutor(Instantiator instantiator) {
        this.instantiator = instantiator;

        priorAnnotations = new ArrayList<Class<? extends Annotation>>();
        priorAnnotations.add(Before.class);

        posteriorAnnotations = new ArrayList<Class<? extends Annotation>>();
        posteriorAnnotations.add(After.class);
    }

    public boolean test(Class testClass, Method testMethod) throws Exception {
        Object instance = instantiator.instantiate(testClass);
        if (testMethod.isAnnotationPresent(Ignore.class))
            return false;
        invokeAllAnnotatedMethods(instance, priorAnnotations);
        testMethod.invoke(instance);
        invokeAllAnnotatedMethods(instance, posteriorAnnotations);
        return true;
    }

    private void invokeAllAnnotatedMethods(Object instance,
                                           List<Class<? extends Annotation>> annotations)
            throws InvocationTargetException, IllegalAccessException {
        for (Class annotation : annotations) {
            invokeAnnotatedMethod(instance, annotation);
        }
    }

    private void invokeAnnotatedMethod(Object instance, Class annotation) throws InvocationTargetException, IllegalAccessException {
        AnnotatedMethodFinder finder = new AnnotatedMethodFinder(instance.getClass());
        for (Method method : finder.annotatedMethods(annotation)) {
            method.invoke(instance);
        }
    }
}
