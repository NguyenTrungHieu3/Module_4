package com.example.ung_dung_muon_sach.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    @AfterReturning("execution(* com.example.ung_dung_muon_sach.service.BorrowService.*(..))")
    public void logAfterAction(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Đã thực hiện thành công: " + methodName);
    }

    @AfterThrowing(pointcut = "execution(* com.example.ung_dung_muon_sach.service.BorrowService.*(..))", throwing = "ex")
    public void logError(Exception ex) {
        System.out.println("Lỗi xảy ra: " + ex.getMessage());
    }
}
