package com.example.validate_thong_tin_bai_hat.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SongDto {
    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không được dài quá 800 ký tự!")
    @Pattern(regexp = "^[^@;,.=\\-+!#$%^&*()_|~`{}\\[\\]:\"/\\\\<>?]*$", message = "Tên bài hát không được chứa ký tự đặc biệt!")
    private String name;
    @NotBlank(message = "Tên nghệ sĩ không được để trống!")
    @Size(max = 300, message = "Tên nghệ sĩ không được dài quá 300 ký tự!")@Pattern(regexp = "^[^@;,.=\\-+!#$%^&*()_|~`{}\\[\\]:\"/\\\\<>?]*$", message = "Tên nghệ sĩ không được chứa ký tự đặc biệt!")
    private String artist;
    @NotBlank(message = "Thể loại nhạc không được để trống")
    @Size(max = 1000, message = "Thể loại nhạc không được vượt quá 1000 ký tự")
    @Pattern(
            regexp = "^[^@;.=\\-+!#$%^&*()_|~`{}\\[\\]:\"/\\\\<>?]*$",
            message = "Thể loại nhạc không được chứa ký tự đặc biệt (ngoại trừ dấu phẩy)")
    private String genre;
}
