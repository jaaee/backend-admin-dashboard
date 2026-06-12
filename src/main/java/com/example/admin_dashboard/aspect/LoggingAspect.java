package com.example.admin_dashboard.aspect;

import com.example.admin_dashboard.util.LogSanitizer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.example.admin_dashboard.service..*(..))")
    public Object logServiceMethods(
            ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Signature signature = joinPoint.getSignature();

        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();

        Object[] arguments = joinPoint.getArgs();

        log.info(
                "STARTED -> {}.{} | Arguments={}",
                className,
                methodName,
                Arrays.toString(arguments)
        );

        try {

            Object result = joinPoint.proceed();

            long executionTime =
                    System.currentTimeMillis() - startTime;

            log.info(
                    "COMPLETED -> {}.{} | ExecutionTime={} ms",
                    className,
                    methodName,
                    executionTime
            );

            return result;

        } catch (Exception ex) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            log.error(
                    "FAILED -> {}.{} | ExecutionTime={} ms | Error={}",
                    className,
                    methodName,
                    executionTime,
                    ex.getMessage(),
                    ex
            );

            throw ex;
        }
    }
    @Around("execution(* com.example.admin_dashboard.controller..*(..))")
    public Object logController(
            ProceedingJoinPoint joinPoint) throws Throwable {


        long startTime = System.currentTimeMillis();

        String className =
                joinPoint.getSignature().getDeclaringTypeName();

        String methodName =
                joinPoint.getSignature().getName();

        Object[] request = joinPoint.getArgs();

        log.info(
                "CONTROLLER START -> {}.{} | Request={}",
                className,
                methodName,
                Arrays.toString(request)
        );

        try {

            Object response = joinPoint.proceed();
            Object responseToLog = response;

            if (response instanceof ResponseEntity<?> responseEntity) {
                responseToLog = responseEntity.getBody();
            }

            Object sanitizedResponse =
                    LogSanitizer.sanitize(responseToLog);



            log.info(
                    "CONTROLLER END -> {}.{} | Response={} | Time={} ms",
                    className,
                    methodName,
                    sanitizedResponse,
                    System.currentTimeMillis() - startTime
            );

            return response;

        } catch (Exception ex) {

            log.error(
                    "CONTROLLER ERROR -> {}.{} | Time={} ms",
                    className,
                    methodName,
                    System.currentTimeMillis() - startTime,
                    ex
            );

            throw ex;
        }
    }
    }


