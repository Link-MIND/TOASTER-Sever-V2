package com.app.toaster.trace.aop;

import com.app.toaster.auth.UserId;
import com.app.toaster.trace.model.UpdateHistoryEvent;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UpdateHistoryAspect {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning("@annotation(logUpdateHistory)")
    public void logUpdate(JoinPoint joinPoint, LogUpdateHistory logUpdateHistory) {
        Long userId = findIdFromArgs(joinPoint, UserId.class);
        Long domainId = findIdFromArgs(joinPoint, DomainId.class);

        if (userId == null) {
            eventPublisher.publishEvent(
                UpdateHistoryEvent.of(logUpdateHistory.eventString(), logUpdateHistory.domain(), domainId, 1L)
            );
            return;
        }

        eventPublisher.publishEvent(
            UpdateHistoryEvent.of(logUpdateHistory.eventString(), logUpdateHistory.domain(), domainId, userId)
        );
    }

    private Long findIdFromArgs(JoinPoint joinPoint, Class<? extends Annotation> annotationClass) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(annotationClass)) {
                Object arg = args[i];
                if (arg instanceof Long) {
                    return (Long) arg;
                }
            }
        }
        return null;
    }
}