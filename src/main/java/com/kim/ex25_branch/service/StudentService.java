package com.kim.ex25_branch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kim.ex25_branch.domain.Student;
import com.kim.ex25_branch.mapper.StudentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentService {
	
	private final StudentMapper studentMapper;
	
	// @RequiredArgsConstructor 보다 생성자 방식이 권장됨.
	public StudentService(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}
	
	public List<Student> getAllStudents() {
		return studentMapper.selectAllStudents();
	}

	public Student getFindStudentById(Long id) {
		return studentMapper.selectFindStudentById(id);
	}
	
	@Transactional
	public void createStudent(Student student) {
		studentMapper.insertStudent(student);
	}

	@Transactional
	public void updateStudent(Student student) {
		studentMapper.updateStudent(student);
	}

	@Transactional
	public void deleteStudent(Student student) {
		studentMapper.deleteStudent(student);
	}
	@Transactional
	public void deleteStudentById(Long id) {
		studentMapper.deleteStudentById(id);		
	}

}
