package com.example.validate_form_dang_ky.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Họ không được để trống!")
    @Size(min = 5, max = 45, message = "Họ phải có độ dài từ 5 đến 45 ký tự!")
    private String firstName;
    @NotBlank(message = "Tên không được để trống!")
    @Size(min = 5, max = 45, message = "Tên phải có độ dài từ 5 đến 45 ký tự!")
    private String lastName;
    @Pattern(regexp = "(84|0[35789])+([0-9]{8})\\b", message = "Sai số điện thoại")
    private String phoneNumber;
    @Min(value = 18, message = "Tuổi phải lớn hơn 18")
    private int age;
    @Email
    @NotBlank(message = "Email không được để trống")
    private String email;
}
