package com.felipe.setembro21.dto;

import java.io.Serializable;
import com.felipe.setembro21.model.Course;

public class CourseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nameCourseDTO;
	
	private String categoryCourseDTO;

	public CourseDTO(Course course) {
		this.nameCourseDTO = course.getName();
		this.categoryCourseDTO = course.getCategory();
	}

	public String getNameCourseDTO() {
		return nameCourseDTO;
	}

	public void setNameCourseDTO(String nameCourseDTO) {
		this.nameCourseDTO = nameCourseDTO;
	}

	public String getCategoryCourseDTO() {
		return categoryCourseDTO;
	}

	public void setCategoryCourseDTO(String categoryCourseDTO) {
		this.categoryCourseDTO = categoryCourseDTO;
	}
	
	

}
