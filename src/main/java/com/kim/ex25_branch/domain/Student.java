package com.kim.ex25_branch.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Student {
	@NotNull
	private Long id;
	
	@NotBlank(message = "안넣으면 안돼")
	private String name;
	
	@Email(message = "이메일형식 아니면 안돼")
	private String email;
	
	@Min(value=1, message="나이는 1부터시작 150이하")
	private Integer age;
	
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
