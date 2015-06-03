package cn.lry.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.lry.dao.CourseDao;
import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

public class CourseDaoImpl implements CourseDao {

	public CourseDaoImpl(){}
	
	/**
	 * ��ӿγ�
	 * @param cour
	 * @return
	 */
	public boolean add(Course cour){
		Session session = null;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			//��������
			session.beginTransaction();
			//��������
			session.save(cour);
			//�ύ����
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			//session�ع�
			if(session!=null)
				session.getTransaction().rollback();
			return false;
		}finally{
			//�ر�session
			SessionUtils.closeSession(session);
		}
		return true;
	}
	
	/**
	 * ��ѯ�γ�
	 * @param id
	 * @return
	 */
	public Course find(int id){
		Session session = null;
		Course cour = null;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			cour = findModule(session,id);
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			//�ر�session
			SessionUtils.closeSession(session);
		}
		return cour;
	}
	
	/**
	 * ��ѯ���Ŀ���
	 * @param session
	 * @param id
	 * @return
	 */
	private Course findModule(Session session, int id){
		Course cour = null;
		
		//hql���Բ�ѯ
		String hql ="from Course as cour where cour.id=:flagId";
		//����query��ѯ����
		Query query = session.createQuery(hql);
		//���ò�ѯ����
		query.setInteger("flagId", id);
		
		//���ղ�ѯ���
		List<Course> list = query.list();
		Iterator<Course> iter = list.iterator();
		//�������
		if(iter.hasNext()){
			cour = iter.next();
		}
		
		return cour;
	}
	
	/**
	 * �γ̱���
	 */
	public List<Course> traverse(){
		Session session = null;
		List<Course> courList = null;
		//����hql��ѯ���
		String hql = null;
		
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			
			hql = "from Course as cour";
			Query query = session.createQuery(hql);
			//���ղ�ѯ���
			courList = query.list();
			
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			SessionUtils.closeSession(session);
		}
		
		return courList;
	}

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
	public List<Course> traverse(int pageNo, int pageSize,Map map){
		Session session = null;
		//��ѯ�������
		List<Course> courList = null;
		try
		{
			//��ȡsession
			session = SessionUtils.getSession();
			
			String hql = "from Course as cour";
			Query query = session.createQuery(hql);
			
			//���ò�ѯ����  
			//ע����У��map�Ƿ�Ϊ��
			if(map != null){
				Iterator it = map.keySet().iterator();
				while (it.hasNext()){
					Object key = it.next();
					query.setParameter(key.toString(), map.get(key));
				}
			}
			
			//���ò�ѯ��ʼҳ
			query.setFirstResult((pageNo - 1) * pageSize);
			//����ÿҳ��¼����
			query.setMaxResults(pageSize);

			courList = query.list();

		} catch (RuntimeException e){
			throw e;
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			SessionUtils.closeSession(session);
		}
		return courList;
	}
	
	/**
	 * �γ̸���
	 */
	public boolean update(Course cour){
		Session session = null;
		boolean flag = true;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			//�������񡢻�ȡ�������
			Transaction ts = session.beginTransaction();
			session.update(cour);
			//�����ύ
			ts.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			flag = false;
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			SessionUtils.closeSession(session);
		}
		return flag;
	}
	
	//�γ�ɾ��
	public boolean delete(Course cour){
		Session session = null;
		boolean flag = true;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			//�������񡢻�ȡ�������
			Transaction ts = session.beginTransaction();
			session.delete(cour);
			//�����ύ
			ts.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			flag = false;
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			SessionUtils.closeSession(session);
		}
		return flag;
	}
};
