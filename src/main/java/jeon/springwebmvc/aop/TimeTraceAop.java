package jeon.springwebmvc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* jeon.springwebmvc..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        // 시간 측정 시작
        long start = System.currentTimeMillis();
        // 로그 출력
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint + " " + timeMs + "ms");
        }
    }
}
