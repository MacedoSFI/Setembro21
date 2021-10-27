package com.felipe.setembro21.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.setembro21.dto.CourseDTO;
import com.felipe.setembro21.model.Course;
import com.felipe.setembro21.repository.CourseRepository;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

	@Autowired
	CourseRepository courseRepository;

	@GetMapping(value = "/idcourse")
	@ResponseBody
	public ResponseEntity<CourseDTO> course(@RequestParam(name = "id") Long id) {
		Course course = courseRepository.findById(id).get();
		return new ResponseEntity<CourseDTO>(new CourseDTO(course), HttpStatus.OK);
	}

	@GetMapping(value = "/courses")
	@ResponseBody
	public ResponseEntity<Iterable<Course>> listCourses() {
		Iterable<Course> courses = courseRepository.findAll();
		return new ResponseEntity<Iterable<Course>>(courses, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar")
	@ResponseBody
	public ResponseEntity<Course> salvar(@RequestBody Course course) {
		Course courseSalvo = courseRepository.save(course);
		return new ResponseEntity<Course>(courseSalvo, HttpStatus.CREATED);
	}
}
