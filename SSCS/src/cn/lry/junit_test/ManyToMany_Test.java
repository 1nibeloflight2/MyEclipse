package cn.lry.junit_test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Admin;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

public class ManyToMany_Test {

private  Session session = null;
	
	@Before
    public void inilizat(){
    	session = SessionUtils.getSession();
    }
	@Test
	public void add() {
		
		Course c1 = new Course(1101001,"�ߵ���ѧ","��ѧ");
		Course c2 = new Course(1101002,"�ߵȴ���","��ѧ");
		Course c3 = new Course(1101003,"��ѧӢ��(һ)","Ӣ��");
		Course c4 = new Course(1101004,"����(��)","����");
		
		Student s1 = new Student(20134646,"������","123456","���ѧԺ",1302);
		Student s2 = new Student(20134651,"���","123456","���ѧԺ",1302);
		Student s3 = new Student(20134654,"����ҫ","123456","���ѧԺ",1302);
		Student s4 = new Student(20134667,"����","123456","���ѧԺ",1302);
		
		//���ö������
		/*ע�⣺ֻ����һ�ζ���������γ� --> ѧ�� / ѧ�� --> �γ̣�
		 * �ظ������ᵼ��������ͻ�쳣(����������Course_id & student_id)*/
		Set<Student> stus = new HashSet<Student>();
		stus.add(s1);
		stus.add(s2);
		stus.add(s3);
		stus.add(s4);
		
		c1.setStus(stus);
		c2.setStus(stus);
		c3.setStus(stus);
		c4.setStus(stus);
		
		try{
//			//��ȡsession
//			session = SessionUtils.getSession();
			//��������
			session.beginTransaction();
			//��������
			
			session.save(s1);
			session.save(s2);
			session.save(s3);
			session.save(s4);
			
			session.save(c1);
			session.save(c2);
			session.save(c3);
			session.save(c4);

			//�ύ����
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			//session�ع�
			if(session!=null)
				session.getTransaction().rollback();
		}
	}
	
	@Test
	public void addCours(){
		Course c1 = new Course(1101009,"�ߵ���ѧ���£�","��ѧ");
		Course c2 = new Course(1101013,"�ߵ���ѧ���ϣ�","��ѧ");
		Course c3 = new Course(1101014,"�ߵȴ������£�","��ѧ");
		Course c4 = new Course(1101010,"�ߵȴ������ϣ�","��ѧ");
		Course c5 = new Course(1101015,"��ѧӢ��(��)","Ӣ��");
		Course c6 = new Course(1101016,"��ѧӢ��(��)","Ӣ��");
		Course c7 = new Course(1101011,"��ѧӢ��(��)","Ӣ��");
		Course c8 = new Course(1101012,"����(һ)","����");
		Course c9 = new Course(1101017,"����(��)","����");
		Course c10 = new Course(1101018,"����(��)","����");
		Course c11 = new Course(1101019,"����(��)","����");
		
		try{
			session.beginTransaction();
			//��������
			
			session.save(c1);
			session.save(c2);
			session.save(c3);
			session.save(c4);
			session.save(c5);
			session.save(c6);
			session.save(c7);
			session.save(c8);
			session.save(c9);
			session.save(c10);
			session.save(c11);
			

			//�ύ����
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			//session�ع�
			if(session!=null)
				session.getTransaction().rollback();
		}
	}
	
	@Test
	public void addAdmin(){
		Admin admin1 = new Admin(1001,"admin_1","123456");
		Admin admin2 = new Admin(1002,"admin_2","123456");
		Admin admin3 = new Admin(1003,"admin_3","123456");
		Admin admin4 = new Admin(1004,"admin_4","123456");
		try{
			//��������
			session.beginTransaction();
			//��������
			session.save(admin1);
			session.save(admin2);
			session.save(admin3);
			session.save(admin4);
			//�ύ����
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			//session�ع�
			if(session!=null)
				session.getTransaction().rollback();
		}
	}
	
	@Test
	public void fund(){
		try{
//			
			String hql ="from Student as stu where stu.id=:flagId";
			//String hql ="from Course as cour where cour.id=:flagId";
			//����query��ѯ����
			Query query = session.createQuery(hql);
			//���ò�ѯ�ؼ���
			query.setInteger("flagId", 20134654);
			
			//���ղ�ѯ���
			List<Student> list = query.list();
			Iterator iter = list.iterator();
			//�������
			if(iter.hasNext()){
				System.out.println(iter.next());
			}
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}
	
	
	
	public Student query(int id) {
		Student stu = null;
		
		try{
//			
			String hql ="from Student as stu where stu.id=:flagId";
			//����query��ѯ����
			Query query = session.createQuery(hql);
			//���ò�ѯ�ؼ���
			query.setInteger("flagId", id);
			
			//���ղ�ѯ���
			List<Student> list = query.list();
			Iterator iter = list.iterator();
			//�������
			if(iter.hasNext()){
				stu = (Student)iter.next();
			}
			
			System.out.println(stu);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		return stu;
	}
	
	@Test
	public void traverseStu(){
		String hql ="from Student as stu";
		Query query = session.createQuery(hql);
		//���ղ�ѯ���
		List<Student> list = query.list();
		Iterator iter = list.iterator();
		//�������
		while(iter.hasNext()){
			System.out.println((Student)iter.next());
		}
	}
	
	@Test
	public void traverseCours(){
		String hql ="from Course as cours";
		Query query = session.createQuery(hql);
		//���ղ�ѯ���
		List<Course> list = query.list();
		Iterator iter = list.iterator();
		//�������
		while(iter.hasNext()){
			System.out.println((Course)iter.next());
		}
	}
	
	@Test
	public void update(){
		
		//Student stu = query(20134654);
		Student stu = new Student(20134646,"������","654321","���ѧԺ",2013);
		stu.setAcademy("�ķ�ѧԺ");
		
		Course c1 = new Course(1101005,"��ֵ����","��ѧ");
		Course c2 = new Course(1101006,"���Դ���","��ѧ");
		Course c3 = new Course(1101007,"��ѧ�������","Ӣ��");
		Course c4 = new Course(1101008,"������������ͳ��","��ѧ");
		Set<Course> cours = new HashSet<Course>();
		cours.add(c1);
		cours.add(c2);
		cours.add(c3);
		cours.add(c4);
		
		stu.setCourses(cours);
		try{
//			//��ȡsession
//			session = SessionUtils.getSession();
			//�������񡢻�ȡ�������
			Transaction ts = session.beginTransaction();
			
			//�ȱ���γ���Ϣ
//			session.save(c1);
//			session.save(c2);
//			session.save(c3);
//			session.save(c4);
//			
			//����ѧ����Ϣ
			session.update(stu);
			//�����ύ
			ts.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete(){
		Course cour = new Course(1101003,"��ѧӢ��(һ)","Ӣ��");
		Student stu = new Student(20134646,"������","123456","���ѧԺ",1302);
		try{
//			//��ȡsession
//			session = SessionUtils.getSession();
			//�������񡢻�ȡ�������
			Transaction ts = session.beginTransaction();
			session.delete(cour);
			//�����ύ
			ts.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void close(){
		SessionUtils.closeSession(session);
	}
};
