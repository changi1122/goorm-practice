package aopaspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* aopaspect.*Service.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void logTimeBefore(JoinPoint joinPoint) {
        LocalTime now = LocalTime.now();
        System.out.println("[Before " + joinPoint.getSignature().getName() + "] 현재 시 분 초: " + now.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    @After("pointCut()")
    public void logTimeAfter(JoinPoint joinPoint) {
        LocalTime now = LocalTime.now();
        System.out.println("[After " + joinPoint.getSignature().getName() + "] 현재 시 분 초: " + now.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AfterReturning에서 출력] sleep 시간: " + result.toString() + "초 (반환값)");
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("[AfterThrowing에서 출력] 예외 메시지: " + ex.getMessage());
    }

    @Around("pointCut()")
    public Object logTimeGapAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime before = LocalTime.now();
        System.out.println("[Around slowMethod] 소요시간 측정 시작!");

        /* 조인포인트 실행 */
        Object result = joinPoint.proceed();

        LocalTime after = LocalTime.now();

        // 차이 계산
        Duration duration = Duration.between(before, after);
        System.out.println("[Around slowMethod] 소요시간: " + duration.getSeconds() + "." + duration.getNano() + "초");

        return result;
    }
}
