package com.example.may_tinh_ca_nhan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/home")
    public String showForm() {
        return "calculator";
    }

    @PostMapping("calculate")
    public String calculate(
            @RequestParam(name = "num1") double num1,
            @RequestParam(name = "num2") double num2,
            @RequestParam(name = "operator") String operator,
            Model model
    ) {
        double result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                model.addAttribute("operator", "Addition");
                break;

            case "-":
                result = num1 - num2;
                model.addAttribute("operator", "Subtraction");
                break;

            case "*":
                result = num1 * num2;
                model.addAttribute("operator", "Multiplication");
                break;

            case "/":
                if (num2 == 0) {
                    model.addAttribute("result", "Không thể chia cho 0");
                    return "index";
                }
                result = num1 / num2;
                model.addAttribute("operator", "Division");
                break;
        }
        model.addAttribute("result", result);
        return "result";
    }
}
