package cn.lry.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cn.lry.dao.AdminDao;
import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Admin;

public class AdminDaoImpl implements AdminDao {

	public AdminDaoImpl(){}
	
	public boolean add(Admin admin){
		Session session = null;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			//��������
			session.beginTransaction();
			//��������
			session.save(admin);
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
	
	public Admin find(int id){
		Session session = null;
		Admin admin = null;
		try{
			//��ȡsession
			session = SessionUtils.getSession();
			//hql���Բ�ѯ
			String hql ="from Admin as admin where admin.id=:flagId";
			//����query��ѯ����
			Query query = session.createQuery(hql);
			//���ò�ѯ����
			query.setInteger("flagId", id);
			
			//���ղ�ѯ���
			List<Admin> list = query.list();
			
			Iterator<Admin> iter = list.iterator();
			//�������
			if(iter.hasNext()){
				admin = (Admin)iter.next();
			}
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//��ʹreturn������Ҳ��ִ��finally���
			//�ر�session
			SessionUtils.closeSession(session);
		}
		return admin;
	}
};
