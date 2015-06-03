package cn.lry.junit_test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Student;

public class SessionFactoryTest {

	@Test
	public void fund(){
		
		try{
			Session session = SessionUtils.getSessionFactory().getCurrentSession();
			//��������
			session.beginTransaction();
			String hql ="from Student as stu where stu.id=:flagId";
			//����query��ѯ����
			Query query = session.createQuery(hql);
			//���ò�ѯ�ؼ���
			query.setInteger("flagId",20134654);
			//���ղ�ѯ���
			List<Student> list = query.list();
			//�ύ����
			session.getTransaction().commit();
			
			Iterator iter = list.iterator();
			//�������
			if(iter.hasNext()){
				System.out.println(iter.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
};
