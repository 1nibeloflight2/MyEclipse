package cn.lry.dao.factory;

import cn.lry.dao.CourseDao;
import cn.lry.dao.impl.CourseDaoImpl;

public class CourseDaoFactory {

	private static CourseDao courdao = null;
	
	//����˽�л���ʵ��CourseDaoImpl����
	private CourseDaoFactory(){}
	
	public static CourseDao getInstance(){
		if(courdao == null){
			courdao = new CourseDaoImpl();
		}
		return courdao;
	}
	
};
