package com.makarov.ui.tracker.library.injector;

import com.makarov.ui.tracker.library.annotations.ViewEvent;
import com.makarov.ui.tracker.library.repository.IEventRepository;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by makarov on 12/07/15.
 */

@Aspect
public class ViewEventsInjector {

    private static ViewEventsTracker eventTracker;

    private static void checkInitedTracker(){
        if(eventTracker == null){
            throw new NullPointerException();
        }
    }

    public static void init(){
        eventTracker = new ViewEventsTracker();
    }

    public static void init(IEventRepository repository){
        eventTracker = new ViewEventsTracker(repository);
    }

    public static void clear(){
        checkInitedTracker();
        eventTracker.clearHistory();
    }

    public static void inject(Object obj) {
        checkInitedTracker();
        eventTracker.inject(obj);
    }

    private static final String POINTCUT_METHOD = "execution(@com.makarov.ui.tracker.library.annotations.ViewEvent * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithViewEvent() {
    }

    @Around("methodAnnotatedWithViewEvent()")
    public Object joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();

        Method method = ms.getMethod();
        Object object = joinPoint.getThis();
        Object[] arg = joinPoint.getArgs();

        int code = method.getAnnotation(ViewEvent.class).value();
        eventTracker.addEvent(code, method, object, arg);

        Object result = joinPoint.proceed();
        return result;
    }



}

