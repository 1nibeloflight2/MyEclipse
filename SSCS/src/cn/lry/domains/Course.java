package cn.lry.domains;

import java.util.HashSet;
import java.util.Set;

public class Course {
	
	//�γ̱��
	private int id;
	//�γ�����
	private String name;
	//�γ�����
	private String type;
	//ѡ��ѧ������
	private Set<Student> stus;
	
	public Course() {
		super();
		//this.stus = new HashSet<Student>();
	}
	public Course(int id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		//this.stus = new HashSet<Student>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<Student> getStus() {
		return stus;
	}
	public void setStus(Set<Student> stus) {
		this.stus = stus;
	}
	public void setStus(Student stu){
		this.stus.add(stu);
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
};
