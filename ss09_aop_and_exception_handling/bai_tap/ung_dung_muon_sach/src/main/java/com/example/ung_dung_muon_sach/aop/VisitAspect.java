package com.example.ung_dung_muon_sach.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VisitAspect {
    private int count = 0;

    @Before("execution(* com.example.ung_dung_muon_sach.controller.*.*(..))")
    public void countVisit() {
        count++;
        System.out.println("Số lượt truy cập: " + count);
    }
}
