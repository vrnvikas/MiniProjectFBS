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

import java.util.HashMap;
import java.util.List;

import com.cg.fms.dto.Course;
import com.cg.fms.dto.Employee;
import com.cg.fms.dto.FacultySkill;
import com.cg.fms.dto.Feedback;
import com.cg.fms.dto.TrainingProgram;
import com.cg.fms.exception.FeedbackSysException;


/** */
public interface FMSDao {
	
	/**
	 * @throws FeedbackSysException  */
	public List<TrainingProgram> getAllTrainingProgramList() throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public boolean updateTrainingProgramWithId(TrainingProgram program) throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public List<String> getAllTrainingProgramNames() throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public boolean addNewTrainingProgram(com.cg.fms.dto.TrainingProgram trainneEnrollment) throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public List<FacultySkill> getAllFacultyList() throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public List<Course> getAllCourseList() throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public boolean updateCourseWithId(Course course) throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public HashMap<Integer,String> getCourseNames() throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public List<Employee> getAllEmployeeList() throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public HashMap<Integer,String> getEmployeeNames() throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public List<Feedback> getAllfeedbackList() throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public boolean addNewFeedback(com.cg.fms.dto.Feedback feedback) throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public boolean addNewParticipantTrainingEnroll(com.cg.fms.dto.TrainingParticipant Parameter1) throws FeedbackSysException;
	
	/**
	 * @throws FeedbackSysException  */
	public Course removeCourse(int id) throws FeedbackSysException;
}
