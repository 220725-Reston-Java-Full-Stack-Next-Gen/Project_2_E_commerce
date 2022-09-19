package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static Logger LOGGER = Logger.getLogger(LoggingAspect.class);

    @Before(value = "execution(* com.revature.*.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info(String.format("Before advice for: [ %s : %s ]", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName()));
    }

    @After(value = "execution(* com.revature.*.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        LOGGER.info(String.format("After advice for: [ %s : %s ]", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName()));
    }

    @Around(value = "execution(* com.revature.services.*.*(..))")
    public Object logDuringService(ProceedingJoinPoint joinPoint) {
        // Get arguments returned from the dao layer call that came into the service layer
        Object[] args = joinPoint.getArgs();

        // provide a log message for this advice
        LOGGER.info(String.format("Around advice for: [ %s : %s ]", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName()));

        // proceed the program flow in this joinPoint's owner method using proceed()
        Object result = null;

        try {
            result = joinPoint.proceed(args);
            LOGGER.info(String.format("Results of this method invoking/proceeding is: %s", result));
        } catch (Throwable e) {
            LOGGER.warn(String.format("Unable to execute around advice: %s", e.getMessage()));
        }

        // end the advice by returning the found results from proceeding
        LOGGER.info("End of Around Advice");
        return result;
    }

    @Around(value = "execution(* com.revature.repos.*.*(..))")
    public Object logDuringRepo(ProceedingJoinPoint joinPoint) {
        // get the arguments returned from the dao layer call that came into the service layer
        Object[] args = joinPoint.getArgs();

        // provide a log message for this advice
        LOGGER.info(String.format("Around advice for: [ %s : %s ]", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName()));

        // proceed the program flow in this Joinpoint's owner method using the proceed()
        Object result = null;

        try {
            result = joinPoint.proceed(args);
            LOGGER.info(String.format("Results of this method invoking/proceeding is: %s", result));
        } catch (Throwable e) {
            LOGGER.warn(String.format("Unable to execute around advice: %s", e.getMessage()));
        }

        // end advice by returning the found results from proceeding
        LOGGER.info("End of Around Advice");
        return result;
    }
}
