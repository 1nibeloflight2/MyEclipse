package cn.lry.dao.proxy;

import java.util.Set;

import cn.lry.dao.AdminDao;
import cn.lry.dao.CourseDao;
import cn.lry.dao.StuDao;
import cn.lry.dao.factory.AdminDaoFactory;
import cn.lry.dao.factory.CourseDaoFactory;
import cn.lry.dao.factory.StuDaoFactory;
import cn.lry.domains.Admin;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

/**
 * @function dao��������ͳһstudao��courdao��admindao����
 * @author Glory
 *
 */
public class DaoProxy {

	/**
	 * @statement  ��д ����
	 * @function   ���ѧ��
	 * @param stu
	 *             ����ӵ�ѧ������ʵ��
	 * @return     ���������ɹ�������true�����򷵻�false
	 */
	public static boolean add(Student stu){
		return StuDaoFactory.getInstance().add(stu);
	}
	
	/**
	 * @statement  ��д����
	 * @function   ��ӿγ�
	 * @param cour
	 *             ����ӵĿγ̶���ʵ��
	 * @return     �������ʧ�ܣ�����false �����򷵻�true
	 */
	public static boolean add(Course cour){
		return CourseDaoFactory.getInstance().add(cour);
	}
	
	/**
	 * @function    ���ѧ��/�γ̼���
	 * @param objSet
	 *              Ҫ��Ӷ��󼯺�
	 * @param objType
	 *              Ҫ��ӵĶ�������
	 * @return      �������ʧ�ܣ�����false�����򷵻�true
	 */
	public static <T> boolean add(Set<T> objSet, Class<T> objType){
		//ClassType name
		String objName = objType.getSimpleName();
	
		if("Student".equals(objName)){
			for(T  stu: objSet){
				if(!StuDaoFactory.getInstance().add((Student)stu)){
					return false;
				}
			}
		}else if("Course".equals(objName)){
			for(T  cour: objSet){
				if(!CourseDaoFactory.getInstance().add((Course)cour)){
					return false;
				}
			}
		}
		return true;	
	}
	
	/**
	 * @function ��ѯѧ����Ϣ		
	 * @param id
	 * 			   ѧ��ѧ��
	 * @return   �����ѯ����ؼ�¼������ѧ��ʵ�������򷵻�null
	 */
	public static Student finddStu(int id){
		return StuDaoFactory.getInstance().find(id);
	}
	
	/**
	 * @function ��ѯ�γ���Ϣ
	 * @param id
	 * 			 �γ̱��
	 * @return  �����ѯ����ؼ�¼�����ؿγ�ʵ�������򷵻�null
	 */
	public static Course findCour(int id){
		return CourseDaoFactory.getInstance().find(id);
	}
	
	/**
	 * @function ��ѯ����Ա��Ϣ
	 * @param id
	 * 			 ����Ա���
	 * @return  �����ѯ����ؼ�¼�����ع���Աʵ�������򷵻�null
	 */
	public static Admin findAdm(int id){
		return AdminDaoFactory.getInstance().find(id);
	}
	
	/**
	 * @function ���ݴ��ݵ�id�ź�ʵ�����Ͳ�ѯѧ��/����Ա/�γ���Ϣ
	 * @param id
	 * 			   ���
	 * @param findClass
	 *           ʵ������
	 * @return   ���ݴ��ݵ�entityClass���Ͳ�һ����
	 *           �����ѯ����ؼ�¼�����ض�Ӧʵ�������򷵻�null
	 */
	public static <T> T find(int id, Class<T> entityClass){
	
		T result = null;
		//ClassType name
		String classType = entityClass.getSimpleName();
		
		if(classType.equals("Student")){
			result = (T) StuDaoFactory.getInstance().find(id);
		}else if(classType.equals("Student")){
			result = (T) CourseDaoFactory.getInstance().find(id);
		}else if(classType.equals("Admin")){
			result = (T) AdminDaoFactory.getInstance().find(id);
		}
		return result;
	}
	
	/**
	 * @statement ��д������
	 * @function ���ݴ��ݲ���  ���и��¶��������ݿ���Ϣ����
	 * @param stu/cour
	 *           ���¶��� 
	 * @return   ������³ɹ�������true���������ʧ�ܣ�����false
	 */
	public static boolean update(Student stu){
		return StuDaoFactory.getInstance().update(stu);
	}
	public static boolean update(Course cour){
		return CourseDaoFactory.getInstance().update(cour);
	}
	
	/**
	 * @function ѡ�β��� 
	 * @param stu
	 *           ѡ�ε�ѧ������ʵ��
	 * @param coursSet
	 *           ��ѧ����ѡ�Ŀγ̼���
	 * @return   ����ʧ�ܣ�����false�����򷵻�true
	 */
	public static boolean selectCours(Student stu, Set<Course> coursSet){
		//courdao��course Dao����
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		//�������ݵĿγ̼��ϣ�Ϊÿһ���γ����ѧ��ʵ�壬Ȼ����¸ÿγ�
		for(Course c: coursSet){
			c.setStus(stu);
			if(courdao.update(c)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @statement  ��д����
	 * @function   ɾ��ѧ��
	 * @param stu
	 *             ��ɾ��ѧ������ʵ��
	 * @return     �������ʧ�ܣ�����false�����򷵻�true
	 */
	public static boolean delete(Student stu){
		//courdao��course Dao����
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		//������ѧ����ѡ�μ��ϣ�����ÿ���γ����Ƴ���ѧ����¼,Ȼ����¿γ�
		Set<Course> cours = stu.getCourses();
		for(Course c: cours){
			if(!c.getStus().remove(stu) || !courdao.update(c)){
				return false;
			}
		}
		//ɾ��ѧ��
		if(!StuDaoFactory.getInstance().delete(stu)){
			return false;
		}
		return true;
	}
	
	/**
	 * @statement  ��д����
	 * function    ɾ���γ�
	 * @param cour
	 *             ��ɾ���γ̶���ʵ��
	 * @return     �������ʧ�ܣ�����false�����򷵻�true
	 */
	public static boolean delete(Course cour){
		return CourseDaoFactory.getInstance().delete(cour);
	}
	
	/**
	 * @function    �Ƴ�ָ��ѧ����ĳһ��ѡ�μ�¼
	 * @param stu
	 *              �Ƴ�ѡ�ε�ѧ������ʵ��
	 * @param cour
	 *              ���Ƴ��Ŀγ̶���ʵ��
	 * @return      �������ʧ�ܣ�����false�����򷵻�true
	 */
	public static boolean removeCour(Student stu, Course cour){
		//�Ƴ��ÿγ̵ĸ�ѧ����¼��Ȼ����¿γ�
		if(!cour.getStus().remove(stu) || 
				!CourseDaoFactory.getInstance().update(cour)){
			return false;
		}
		return true;
	}
	
	/**
	 * @function �Ƴ�ѧ��ѡ�μ�¼
	 * @param stu
	 *           ��Ҫ�Ƴ�ѡ�ε�ѧ������ʵ��
	 * @param coursSet
	 *           ���Ƴ���ѡ�μ���
	 * @return   �������ʧ�ܣ�����false�����򷵻�true
	 */
	public static boolean removeCours(Student stu, Set<Course> coursSet){
		//courdao��course Dao����
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		//������ѧ����ѡ�μ��ϣ�����ÿ���γ����Ƴ���ѧ����¼,Ȼ����¿γ�
		Set<Course> cours = stu.getCourses();
		for(Course c: cours){
			if(!c.getStus().remove(stu) || !courdao.update(c)){
				return false;
			}
		}
		return true;
	}
};
