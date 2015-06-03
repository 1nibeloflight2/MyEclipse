package cn.lry.dao;

import java.util.List;
import java.util.Map;

import cn.lry.domains.Student;

public interface StuDao {

	/**
	 * �����ѧ��
	 * @param stu
	 * @return
	 */
	public abstract boolean add(Student stu);

	/**
	 * ����ѧ��ID����ѧ��
	 * @param id
	 * @return
	 */
	public abstract Student find(int id);

	/**
	 * ����ѧ����
	 * @param travType
	 * @return
	 */
	public abstract List<Student> traverse();

	
	/**
	 * @function    ��ҳ��ѯ����ʵ��
	 * @param pageNo
	 *              ��ѯҳ��   
	 * @param pageSize
	 *              ÿҳ��ѯ��¼����
	 * @param map
	 *              ��ѯ������װ
	 * @return      
	 */
	public List<Student> traverse(int pageNo, int pageSize,Map map);
	
	/**
	 * ���²���
	 * @param stu
	 * @return
	 */
	//@SuppressWarnings("finally")
	public abstract boolean update(Student stu);

	/**
	 * ɾ������
	 * @param stu
	 * @return
	 */
	//@SuppressWarnings("finally")
	public abstract boolean delete(Student stu);

}