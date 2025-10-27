package com.kim.ex25_branch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.ex25_branch.domain.Student;
import com.kim.ex25_branch.service.StudentService;

@RestController
@RequestMapping("/api/students")
//@RequiredArgsConstructor
public class StudentRestControaller {

	private final StudentService studnetService;
	
	// @RequiredArgsConstructor 보다 생성자 방식이 권장됨.
	public StudentRestControaller(StudentService studnetService) {
		this.studnetService = studnetService;
	}
	
	@GetMapping
	public List<Student> getAllStudents() {
		return studnetService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> detail(@PathVariable Long id) {
		Student student = studnetService.getFindStudentById(id);
		return ResponseEntity.ok(student);
	}

//	등록 :POST http://localhost:8080/api/students
//	{
//		"name":"테스트3",
//		"email":"kk@nate.com",
//		"age":25
//	}
	@PostMapping
	public ResponseEntity<Student> create(@RequestBody Student student) {
		studnetService.createStudent(student);
		return ResponseEntity.ok(student);
	}

	// 수정 put
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@RequestBody Student student) {
		studnetService.updateStudent(student);
		return ResponseEntity.ok(student);
	}

	// 삭제 delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		studnetService.deleteStudentById(id);
		return ResponseEntity.ok().build();
	}
	
	// 삭제 delete 아래 처럼 해도 오류가 나지 않으나 api 호출할때 //
	// body도 넣어줘야 하는 문제가 생김, 경로.../3 으로 보내고 body json 값에 id=4로 하게 되면 4가 지워짐..
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> delete(@RequestBody Student student) {
//		studnetService.deleteStudent(student);
//		return ResponseEntity.ok().build();
//	}
	 
}
