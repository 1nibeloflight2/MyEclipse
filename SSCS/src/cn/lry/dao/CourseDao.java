package cn.lry.dao;

import java.util.List;
import java.util.Map;

import cn.lry.domains.Course;

public interface CourseDao {

	/**
	 * ��ӿγ�
	 * @param cour
	 * @return
	 */
	public abstract boolean add(Course cour);

	/**
	 * ��ѯ�γ�
	 * @param id
	 * @return
	 */
	public abstract Course find(int id);

	/**
	 * @function    ��ѯ���пγ���Ϣ
	 * @return		��������ɹ������ؿγ̼��ϣ����򷵻ؿ�
	 */
	public abstract List<Course> traverse();
	
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
	public List<Course> traverse(int pageNo, int pageSize,Map map);

	public abstract boolean update(Course cour);

	public abstract boolean delete(Course cour);

}