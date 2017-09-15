//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Feedback Management System
//  @ File Name : FMSDao.java
//  @ Date : 15-09-2017
//  @ Author : Vikas Chaudhary
//
//



package com.cg.fms.dao;

import java.util.List;

import com.cg.fms.dto.Course;
import com.cg.fms.dto.Employee;
import com.cg.fms.dto.FacultySkill;
import com.cg.fms.dto.Feedback;
import com.cg.fms.dto.TrainingProgram;


/** */
public interface FMSDao {
	/** */
	public List<TrainingProgram> getAllTrainingProgramList();
	
	/** */
	public boolean updateTrainingProgramWithId(int id);
	
	/** */
	public List<String> getAllTrainingProgramNames();
	
	/** */
	public void addNewTrainingProgram(com.cg.fms.dto.TrainingProgram trainneEnrollment);
	
	/** */
	public List<FacultySkill> getAllFacultyList();
	
	/** */
	public List<Course> getAllCourseList();
	
	/** */
	public boolean updateCourseWithId(int id);
	
	/** */
	public List<String> getCourseNames();
	
	/** */
	public List<Employee> getAllEmployeeList();
	
	/** */
	public List<String> getEmployeeNames();
	
	/** */
	public List<Feedback> getAllfeedbackList();
	
	/** */
	public boolean addNewFeedback(com.cg.fms.dto.Feedback feedback);
	
	/** */
	public boolean addNewParticipantTrainingEnroll(com.cg.fms.dto.TrainingParticipant Parameter1);
}