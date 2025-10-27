package com.kim.ex25_branch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kim.ex25_branch.domain.Student;
import com.kim.ex25_branch.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentControaller {

	private final StudentService studentService;
	// private final ApiUserService apiUserService;

	// public StudentControaller(StudentService studentService, ApiUserService apiUserService) {
	public StudentControaller(StudentService studentService) {
		this.studentService = studentService;
	}

	// 전체 유저 목록 화면
	@GetMapping
	public String getAllStudents(Model model) {
		List<Student> studentList = studentService.getAllStudents();
		model.addAttribute("students", studentList);

		return "student/list";
	}

	// 단건 조회
	@GetMapping("/{id}")
	public ResponseEntity<Student> getFindStudentById(@PathVariable Long id) {
		Student student = studentService.getFindStudentById(id);
		return ResponseEntity.ok(student);
	}

	// 등록 화면
	@GetMapping("/new")
	public String createForm(Model model, Student student) {
		model.addAttribute("student", student);
		return "student/form";
	}

	// 등록 서비스
	@PostMapping
	private String createStudent(@ModelAttribute Student student) {
		studentService.createStudent(student);
		return "redirect:/students";
	}

	// 단건 수정 화면
	@GetMapping("/edit/{id}")
	public String updateView(@PathVariable Long id, Model model) {
		Student student = studentService.getFindStudentById(id);
		model.addAttribute("student", student);

		return "student/form";
	}

	// 수정 서비스
	@PostMapping("/{id}")
	private String updateStudent(@ModelAttribute Student student) {
		studentService.updateStudent(student);
		return "redirect:/students";
	}

	// 삭제 서비스
	@PostMapping("/delete/{id}")
	private String deleteStudent(@ModelAttribute Student student) {
		studentService.deleteStudent(student);
		return "redirect:/students";
	}

}
