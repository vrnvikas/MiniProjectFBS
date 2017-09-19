//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Feedback Management System
//  @ File Name : FMSDaoImpl.java
//  @ Date : 15-09-2017
//  @ Author : Vikas Chaudhary
//
//



package com.cg.fms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cg.fms.dto.Course;
import com.cg.fms.dto.Employee;
import com.cg.fms.dto.FacultySkill;
import com.cg.fms.dto.Feedback;
import com.cg.fms.dto.TrainingParticipant;
import com.cg.fms.dto.TrainingProgram;
import com.cg.fms.exception.FeedbackSysException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.fms.util.DBUtil;

/** */
public class FMSDaoImpl implements FMSDao {

	/** 
	 * @ Date : 16-09-2017
  	*  @author : Shubham Parasher
  	*  @ Description : Fetching the feedback details from the table
  	*  */
	@Override
	public List<Feedback> getAllfeedbackList() throws FeedbackSysException {
		ArrayList<Feedback> list =null;
		Feedback feedback =null;
		try(Connection con=DBUtil.getConnect())
		{
			PreparedStatement pstm=
					con.prepareStatement("select * from feedback_master");
			ResultSet res = pstm.executeQuery();
			list = new ArrayList<Feedback>();
			if(res.next()==false)
			{
				throw new FeedbackSysException("No Feedback has been registered");
			}
	
				do
				{
					feedback = new Feedback();
					feedback.setParticipantId(res.getInt("participant_id"));
					feedback.setTrainingCode(res.getInt("training_code"));
					feedback.setFbPrsComm(res.getInt("fb_prs_comm"));
					feedback.setFbClrfyDbts(res.getInt("fb_clrfy_dbts"));
					feedback.setFbTm(res.getInt("fb_tm"));
					feedback.setFbHandout(res.getInt("fb_hnd_out"));
					feedback.setFBHwSwNtwrk(res.getInt("fb_hw_sw_ntwrk"));
					feedback.setComment(res.getString("comments"));
					feedback.setSuggestions(res.getString("suggestions"));
					feedback.setFeedbackDate(res.getDate("FEEDBACK_DATE"));
					list.add(feedback);
				}while(res.next());
			
		}catch(Exception e){
			throw new FeedbackSysException(e.getMessage());
		}
	
	
		return list;
	}
	

	/** 
	 * @ Date : 16-09-2017
  	*  @author : Shubham Parasher
  	*  @ Description : Adding student feedback to the table
  	*  */
	
	@Override
	public boolean addNewFeedback(Feedback feedback) throws FeedbackSysException {
		 boolean flag = false;
		 try( Connection con = DBUtil.getConnect())
			{
				
			
			//get product details
				int id = feedback.getParticipantId();
				int code = feedback.getTrainingCode();
				int communication  = feedback.getFbPrsComm();
				int doubt = feedback.getFbClrfyDbts();
				int tm = feedback.getFbTm();
				int handout = feedback.getFbHandout();
				int network = feedback.getFBHwSwNtwrk();
				String comments = feedback.getComment();
				String suggestions = feedback.getSuggestions();
				Date date = feedback.getFeedbackDate();
				
				
			    //insert into database
				PreparedStatement pstm = con.prepareStatement("insert into feedback_master values(?,?,?,?,?,?,?,?,?,?,?,?)");
				
				pstm.setInt(1, code);
				pstm.setInt(2, id);
				pstm.setInt(3, communication);
				pstm.setInt(4, doubt);
				pstm.setInt(5, tm);
				pstm.setInt(6, handout);
				pstm.setInt(7, network);
				pstm.setString(8, comments);
				pstm.setString(9, suggestions);
				pstm.setDate(10, date);
				pstm.execute();
				flag = true;
			
			}
			catch(Exception e)
			{
				throw new FeedbackSysException(e.getMessage());
			}
		 
		 
		 
		 return flag;
		
		
	}
	
	
	/**
	 * @ Date      : 18-09-2017
	 * @author    : Vinay Kumar Verma 
	 * @Description: This method is used for adding new participants to the 
	 *               participant enrollment table. It also supports appropriate
	 *               exception handling. 
	 *  */
	@Override
	public boolean addNewParticipantTrainingEnroll(
			TrainingParticipant Parameter1) throws FeedbackSysException 
	{
		
		boolean status=false;
		try(Connection con = DBUtil.getConnect()){
				
			//Get EnrollMent Details
			int participantId     = Parameter1.getEmployeeId();
			int trainingCode      = Parameter1.getTrainingCode();
			
			
		    //insert into database enrollment details
			
			PreparedStatement pstm = 
					con.prepareStatement("insert into training_parti_enroll values(?,?)");
			pstm.setInt(1, participantId);
			pstm.setInt(2, trainingCode);
			pstm.execute();
			System.out.println("SUCCESSFUL");
			status= true;
			
		}
		catch(Exception e)
		{
			throw new FeedbackSysException(e.getMessage());
		}
		

		return status;
	}
	
	
	/**
	 * 	@Date : 16-09-2017
		 * @author : Roshni Patel
		 * @Description : Gets list of all training programs with start and due dates in Training_Program table
		 */
	@Override
	public ArrayList<TrainingProgram> getAllTrainingProgramList() throws FeedbackSysException {
		// TODO Auto-generated method stub
		TrainingProgram program=null;
		ArrayList<TrainingProgram> programList= null;
		try(Connection con =DBUtil.getConnect())
		{
		PreparedStatement pstm = con.prepareStatement("SELECT * from TRAINING_PROGRAM");
		ResultSet res = pstm.executeQuery();
		
		if(res.next()==false)
		{
			throw new FeedbackSysException("products not found");
		}
		else
		{
			programList = new ArrayList<TrainingProgram>();
			do
			{
				program = new TrainingProgram();
				program.setTrainingCode(res.getInt("TRAINING_CODE"));
				program.setCourseCode(res.getInt("COURSE_CODE"));
				program.setFacultyCode(res.getInt("FACULTY_CODE"));
				program.setStartDate(res.getDate("START_DATE"));
				program.setEndDate(res.getDate("END_DATE"));
				programList.add(program);
			}while(res.next());
		}
		}
		catch(Exception e)
		{
			throw new FeedbackSysException(e.getMessage());
		}
		if(programList.isEmpty()) throw new FeedbackSysException("No data found");
		return programList;
	}

	
	/**
	 * 	@Date : 16-09-2017
		 * @author : Roshni Patel
		 * @Description : Updates Program details in Training_Program table
		 */
	@Override
	public boolean updateTrainingProgramWithId(TrainingProgram program) throws FeedbackSysException{
		try(Connection con = DBUtil.getConnect())
		{
		int trainingCode=program.getTrainingCode();
		int courseCode=program.getCourseCode();
		int facultyCode=program.getFacultyCode();
		Date startDate = program.getStartDate();
		Date endDate = program.getEndDate();
		
			
			PreparedStatement pstm1 = con.prepareStatement("update TRAINING_PROGRAM set COURSE_CODE=?, FACULTY_CODE=?, START_DATE=?, END_DATE=? where TRAINING_CODE=?");
			
			pstm1.setInt(1, courseCode);
			pstm1.setInt(2, facultyCode);
			pstm1.setDate(3, startDate);
			pstm1.setDate(4, endDate);
			pstm1.setInt(5, trainingCode);
			pstm1.execute();
			
		}
		catch(Exception e)
		{
			throw new FeedbackSysException("main problem: "+e.getMessage());
		}
		return true;
	}

	@Override
	public List<String> getAllTrainingProgramNames() throws FeedbackSysException{
		return null;
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * 	@Date : 16-09-2017
		 * @author : Roshni Patel
		 * @Description : adds new Training Program in Training_Program table
		 */
	@Override
	public boolean addNewTrainingProgram(TrainingProgram trainneEnrollment) throws FeedbackSysException{
		int trainingCode=0;
		
		try(Connection con=DBUtil.getConnect())
		{
			
		    //get flat details
			int courseCode=trainneEnrollment.getCourseCode();
			int facultyCode=trainneEnrollment.getFacultyCode();
			Date startDate = trainneEnrollment.getStartDate();
			Date endDate = trainneEnrollment.getEndDate();
			
		    //getsequence number assign to flatRegisterationId
			
			Statement stm=con.createStatement();
			ResultSet res=stm.executeQuery("select TRAININGPROG_seq.nextVal from dual");
	
			if(res.next()==false)
			{
				throw new FeedbackSysException("Could not Register training Course. RegNo Generation failed.");
			}
			
			trainingCode=res.getInt(1);
			
			//insert into database flat_registartion_details
			
			PreparedStatement pstm=con.prepareStatement("insert into TRAINING_PROGRAM values(?,?,?,?,?)");
			pstm.setInt(1, trainingCode);
			pstm.setInt(2, courseCode);
			pstm.setInt(3, facultyCode);
			pstm.setDate(4, startDate);
			pstm.setDate(5, endDate);
			
			pstm.execute();
			
		}
		catch(Exception e)
		{
			throw new FeedbackSysException(e.getMessage());
		}
		return true;

	}
	
	
	
	/**
	 *@ Date : 15-09-2017
	 * @author : Biplav Kumar
	 * @ Description : Retrieve the EmployeeList 
	*/
	@Override
	public ArrayList<Employee> getAllEmployeeList() throws FeedbackSysException {
		System.out.println("start in get all");
		ArrayList<Employee> list = null;
		try(Connection con = DBUtil.getConnect())
		{
			
			Statement pstm = con.createStatement();
			ResultSet res = pstm.executeQuery("select * from Employee_master ");  //select-executeQuery  DML - execute 
			if(res.next()==false)
			{
				throw new FeedbackSysException("Table is Empty");
			}
			else
			{
				list = new ArrayList<Employee>();
				do
				{
					Employee list1 = new Employee();
					list1.setId(res.getInt("Employee_Id"));
					list1.setName(res.getString("Employee_Name"));
					list1.setPassword(res.getString("Password"));
					list1.setRole(res.getString("Role"));
					list.add(list1);
				}
				while(res.next());
					
			}
			
			
		} catch (Exception e) {
			
			 throw new FeedbackSysException(e.getMessage());
		}
		System.out.println("in getAll");
		return list;
		
	}


	/**
	 *@ Date : 15-09-2017
	 * @author : Biplav Kumar
	 *@ Description : Retrieve the EmployeeList 
	*/
	@Override
	public HashMap<Integer,String> getEmployeeNames() throws FeedbackSysException{
		HashMap<Integer,String> EmployeesName = null;
		
		try(Connection con = DBUtil.getConnect())
		{
			Statement pstm = con.createStatement();
			ResultSet res = pstm.executeQuery("select Employee_Id,Employee_Name from Employee_master ");
			if(res.next()==false)
			{
				throw new FeedbackSysException("Table is Empty");
			}
			else
			{
				EmployeesName = new HashMap<Integer,String>();
				do
				{
					//Employee list1 = new Employee();
					EmployeesName.put(res.getInt("Employee_Id"),res.getString("Employee_Name"));
					//list1.setName(res.getString("Employee_Name"));
					//Set set = list1.entrySet();
				}
				while(res.next());
			}
		} catch (Exception e) {
			
			throw new FeedbackSysException(e.getMessage());
			}
		
		return EmployeesName;
	}
	
	/** 
	 * 
	 * @ Date : 16-09-2017
  	 * @author : Sushritha Gone
	 * @ Description : Fetch course details from table Course_master.
	 * */
	@Override
	public List<Course> getAllCourseList() throws FeedbackSysException 
	{
		ArrayList<Course> courses=null;
		try (Connection con = DBUtil.getConnect()){
			
			java.sql.Statement stm =  con.createStatement();
			
			ResultSet res = stm.executeQuery("SELECT * FROM COURSE_MASTER");
			
			courses = new ArrayList<Course>();
			
			while(res.next())
			{
				Course course=new Course();
				course.setId(res.getInt(1));
				course.setName(res.getString(2));
				course.setDuration(res.getInt(3));
				courses.add(course);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new FeedbackSysException(e.getMessage());
		}
		if (courses.isEmpty()) {
			throw new FeedbackSysException("courses doesnt exit");
		}
		return courses;
	
	}
	
	/** 
	 * 
	 * @ Date : 16-09-2017
  	 *@author : Sushritha Gone
	 * @ Description :  Updating course details in Course_master table.
	 * */

	@Override
	public boolean updateCourseWithId(Course course) throws FeedbackSysException {
		boolean flag = false;
		try(Connection con = DBUtil.getConnect())
		{
			String name=course.getName();
			int duration = course.getDuration();
			int id = course.getId();
			
			PreparedStatement pstm = con.prepareStatement("update course_master set name= ?,duration =? where id= ?");
			pstm.setString(1,name);
			pstm.setInt(2,duration);
			pstm.setInt(3,id);
			
			pstm.execute();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new FeedbackSysException(e.getMessage());
		}
		return flag;
	}
	
	/** 
	 * 
	 * @ Date : 16-09-2017
  	 * @author : Sushritha Gone
	 * @ Description :  Fetching Course Names from Course_master table.
	 * */

	@Override
	public HashMap<Integer,String> getCourseNames() throws FeedbackSysException{
		
		HashMap<Integer,String> courseNames = null;
		try (Connection con = DBUtil.getConnect()){
			
			Statement pstm = con.createStatement();		
			ResultSet res =	pstm.executeQuery("Select id , name from course_master");
			
			while(res.next())
			{
				courseNames = new HashMap<Integer,String>();
				courseNames.put(res.getInt("id"), res.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new FeedbackSysException(e.getMessage());
		}
		return courseNames;
	}
	
	/** 
	 * 
	 * @ Date : 16-09-2017
  	 * @author : Sushritha Gone
	 * @ Description :  Deleting the course details from course_master.
	 * */
	@Override
	public Course removeCourse(int id) throws FeedbackSysException {
		
		Course course = null;
		try (Connection con = DBUtil.getConnect()){
			PreparedStatement pstm = con.prepareStatement("select * from COURSE_MASTER where id=?");
			pstm.setInt(1,id);
			ResultSet res = pstm.executeQuery();
			if(res.next() == false) 
				throw new FeedbackSysException("No Course with id"+id);
			else
			{
				course = new Course();
				course.setId(res.getInt("id"));
				course.setName(res.getString("name"));
				course.setDuration(res.getInt("duration"));
				PreparedStatement pstm1 = con.prepareStatement("delete from COURSE_MASTER where id=?");
				pstm1.setInt(1,id);
				pstm1.execute();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new FeedbackSysException(e.getMessage());
		}
		
		return course;
	}

	
	
	/** 
	 * 
	 * @date : 19-09-2017
  	 * @author : Vikas Chaudhary
	 * @description :  getting all faculty from table
	 * */
	@Override
	public List<FacultySkill> getAllFacultyList() throws FeedbackSysException {
		
		ArrayList<FacultySkill> facultyList=null;
		try (Connection con = DBUtil.getConnect()){
			java.sql.Statement stm =  con.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM faculty_skillset_master");
			
			facultyList = new ArrayList<FacultySkill>();
			
			while(res.next())
			{
				FacultySkill faculty=new FacultySkill();
				
				int id = res.getInt("faculty_id");
				faculty.setId(id);
				
				faculty.setName(getEmployeeName(con,id));
					
				String skill = res.getString("skillset");
				faculty.setSkillSet(buildSkillStringArray(skill));
				facultyList.add(faculty);
				
			}
			
		} catch (Exception e) {
			
			throw new FeedbackSysException(e.getMessage());
		}
		
		if (facultyList.isEmpty()) {
			throw new FeedbackSysException("faculty  doesnt exit");
		}
		return facultyList;
	}
	
	
	public String getEmployeeName(Connection con,int id) throws SQLException{
		
		String name = null;
		java.sql.Statement stm1 =  con.createStatement();
		ResultSet resEmp = stm1.executeQuery(
				"SELECT EMPLOYEE_ID,EMPLOYEE_NAME FROM EMPLOYEE_MASTER WHERE EMPLOYEE_ID = "+id);
		
		while(resEmp.next()){
			name = resEmp.getString(2);
		}
		return name;
	}
	
	
	public List<String> buildSkillStringArray(String skill){
		
		List<String> skillList = new ArrayList<String>();
		int count = 0;
		int index = 0;
		
		while(true){
			
			if(count < skill.length()-1){
				String sk = skill.substring(count,skill.indexOf(";",count+1));
				skillList.add(sk);
				count = skill.indexOf(";",count+1) + 1;
				index++;
			}else{
				break;
			}
		}
		
		return skillList;
	}
	
	
}
