package cn.lry.domains;

import java.util.HashSet;
import java.util.Set;

public class Student extends Person {

	//ѧԺ
	private String academy;
	//�༶
	private int classNum;
	//��ѡ�μ���
	private Set<Course> courses;
	
	public Student() {
		super();
		//this.courses = new HashSet<Course>();
	}
	public Student(int id, String name, String pwd, String academy, int classNum) {
		super(id, name, pwd);
		this.academy = academy;
		this.classNum = classNum;
		//this.courses = new HashSet<Course>();
	}
	
	public Student(int id, String name, String academy, int classNum) {
		super(id, name);
		this.academy = academy;
		this.classNum = classNum;
		//this.courses = new HashSet<Course>();
	}
	
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> cours) {
		this.courses = cours;
	}
	
	@Override
	public String toString() {
		return "Student [academy=" + academy + ", classNum=" + classNum
				+ ", courses=" + courses + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getPwd()=" + getPwd() + "]";
	}
	
};
