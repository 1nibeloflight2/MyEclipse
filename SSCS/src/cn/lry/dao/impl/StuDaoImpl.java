package cn.lry.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.lry.dao.StuDao;
import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Student;

public class StuDaoImpl implements StuDao {
	
	//����
	public  StuDaoImpl(){}
	
	/**
	 * �����ѧ��
	 * @param stu
	 * @return
	 */
	public boolean add(Student stu){
		Session session = null;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			//��������
			session.beginTransaction();
			//��������
			session.save(stu);
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
	 * ����ѧ��ID����ѧ��
	 * @param id
	 * @return
	 */
	public Student find(int id){
		Session session = null;
		Student stu = null;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			stu = findModule(session,id);
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			//�ر�session
			SessionUtils.closeSession(session);
		}
		return stu;
	}
	
	/**
	 * Id��ѯģ��
	 * ���ڸ��£�ɾ���Ȳ���
	 * @param session
	 * @param id
	 * @return
	 */
	private Student findModule(Session session, int id){
		Student stu = null;
		
		//hql���Բ�ѯ
		String hql ="from Student as stu where stu.id=:flagId";
		//����query��ѯ����
		Query query = session.createQuery(hql);
		//���ò�ѯ����
		query.setInteger("flagId", id);
		
		//���ղ�ѯ���
		List<Student> list = query.list();
		
		Iterator<Student> iter = list.iterator();
		//�������
		if(iter.hasNext()){
			stu = (Student)iter.next();
		}
		
		//����ˣ�����ӡ��Ȼ����ʧ��
		System.out.println(stu);
		return stu;
	}
	
	/**
	 * ����ѧ����
	 * @param travType
	 * @return
	 */
	public List<Student> traverse(){
		Session session = null;
		List<Student> stuList = null;
		//����hql��ѯ���
		String hql = null;
		
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			
			hql = "from Student as stu";
			Query query = session.createQuery(hql);
			//���ղ�ѯ���
			stuList = query.list();
			
			Iterator iter = stuList.iterator();
			while(iter.hasNext()){
				System.out.println((Student)iter.next());
			}
			
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			SessionUtils.closeSession(session);
		}
		
		return stuList;
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
	public List<Student> traverse(int pageNo, int pageSize,Map map){
		Session session = null;
		//��ѯ�������
		List<Student> stuList = null;
		try
		{
			//��ȡsession
			session = SessionUtils.getSession();
			
			String hql = "from Student as stu";
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

			stuList = query.list();

		} catch (RuntimeException e){
			throw e;
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			SessionUtils.closeSession(session);
		}
		return stuList;
	}

	/**
	 * ���²���
	 * @param stu
	 * @return
	 */
	//@SuppressWarnings("finally")
	public boolean update(Student stu){
		Session session = null;
		boolean flag = true;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			//�������񡢻�ȡ�������
			Transaction ts = session.beginTransaction();
			session.update(stu);
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
	
	/**
	 * ɾ������
	 * @param stu
	 * @return
	 */
	public boolean delete(Student stu){
		Session session = null;
		boolean flag = true;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			//�������񡢻�ȡ�������
			Transaction ts = session.beginTransaction();
			session.delete(stu);
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
