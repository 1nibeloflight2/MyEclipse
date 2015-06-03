package cn.lry.dao.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionUtils {

	 private static SessionFactory sessionFactory = null;
	
	 
	 static {
		 try{
			 //����hibernate.cfg.xml����(�Զ�ƥ��srcĿ¼�µ�xml����)
			Configuration cfg = new Configuration().configure();
			ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(
									cfg.getProperties()).buildServiceRegistry();
	        //����sessionFactory
			sessionFactory = cfg.buildSessionFactory(sr);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	 
	 private SessionUtils(){}
	 
	 public static SessionFactory getSessionFactory() {
		 return sessionFactory;
	 }
	 
	 public static Session getSession() {
		return sessionFactory.openSession();
	 }
	 
	 public static void closeSession(Session session){
		 try{
			 if(session != null){
				 session.close();
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
};


