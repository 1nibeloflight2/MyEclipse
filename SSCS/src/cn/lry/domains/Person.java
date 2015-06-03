package cn.lry.domains;

import cn.lry.utils.EncryptUtils;

public class Person {

	//�û�id
	private int id;
	//�û�����
	private String name;
	//�û�����
	private String pwd;
	
	public Person(){}
	public Person(int id, String name, String pwd){
		this.id = id;
		this.name = name;
		//MD5�㷨����
		this.pwd = EncryptUtils.getMD5Algo(pwd);
	}
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		//MD5�㷨����
		this.pwd = EncryptUtils.getMD5Algo(new Integer(id).toString());
	}
	public int getId() {
		return id;
	}
	//ע�⣺�����Ը���Id���������������޸ģ�
	public void setId(int id){
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
};
