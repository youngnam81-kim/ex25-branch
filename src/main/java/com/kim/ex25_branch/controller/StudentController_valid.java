package com.kim.ex25_branch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kim.ex25_branch.domain.Student;
import com.kim.ex25_branch.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students/valid")
public class StudentController_valid {
	private final StudentService studentService;

	public StudentController_valid(StudentService studentService) {
		this.studentService = studentService;
	}

//	list화면: 전체 학생 목록
	@GetMapping
	public String list(Model model) {
		model.addAttribute("students", studentService.getAllStudents());

		return "student/list_validtest";
	}
	
//	등록폼:form화면: 새로운 학생 정보를 입력하기위한 빈화면.
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("student", new Student());
		return "student/form_validtest";
	}

//	***** 등록폼ValidTest:form화면: 새로운 학생 정보를 입력하기위한 빈화면.
	@GetMapping("/new/valid")
	public String createFormValid(Model model) {
		model.addAttribute("student", new Student());
		return "student/form_validtest";
	}

//	등록처리:학생정보 기록후 저장버튼 클릭을 하면, 학생정보를 insert
	@PostMapping
	public String create(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
//			결과에 에러가 있으면, 입력안하고, 입력창으로 돌아가기
			return "student/form_validtest";
		}
//		이미 학생 저장이 완료되요
		studentService.createStudent(student);
		return "redirect:/students/valid";
	}

//	http://localhost:8080/students/valid/11/edit
//	수정폼
	@GetMapping("/{id}/edit")
	public String updateView(@PathVariable Long id, Model model) {
		Student student = studentService.getFindStudentById(id);
		model.addAttribute("student", student);
		return "student/form_validtest";
	}

//	수정처리:수정처리가 되면 안되는뎅...ㅜㅜㅜ
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, @Valid @ModelAttribute Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
//			결과에 에러가 있으면, 입력안하고, 입력창으로 돌아가기
			return "student/form_validtest";
		}
		student.setId(id);
		studentService.updateStudent(student);
		return "redirect:/students/valid";

	}

//	http://localhost:8080/students/11/delete
	// 삭제 처리
	@PostMapping("/{id}/delete")
	private String deleteStudent(@ModelAttribute Student student) {
		studentService.deleteStudent(student);
		return "redirect:/students/valid";
	}
}
