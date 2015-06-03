package cn.lry.junit_test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;


import cn.lry.dao.CourseDao;
import cn.lry.dao.StuDao;
import cn.lry.dao.factory.CourseDaoFactory;
import cn.lry.dao.factory.StuDaoFactory;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

public class CourseDaoTest {

	@Test
	public void adds(){
		Course c1 = new Course(1101009,"�ߵ���ѧ���£�","��ѧ");
		Course c2 = new Course(1101013,"�ߵ���ѧ���ϣ�","��ѧ");
		Course c3 = new Course(1101014,"�ߵȴ������£�","��ѧ");
		Course c4 = new Course(1101010,"�ߵȴ������ϣ�","��ѧ");
		Course c5 = new Course(1101015,"��ѧӢ�����","Ӣ��");
		Course c6 = new Course(1101016,"��ѧӢ��ģ�","Ӣ��");
		Course c7 = new Course(1101011,"��ѧӢ�����","Ӣ��");
		Course c8 = new Course(1101012,"������һ��","����");
		Course c9 = new Course(1101017,"����������","����");
		Course c10 = new Course(1101018,"����������","����");
		Course c11 = new Course(1101019,"�������ģ�","����");
		Course c12 = new Course(1101020,"��ɢ��ѧ","��ѧ");
		Course c13 = new Course(1101021,"��ֵ����","��ѧ");
		Course c14 = new Course(1101022,"������������ͳ��","��ѧ");
		Course c15 = new Course(1101023,"web����","רҵ");
		Course c16 = new Course(1101024,"JSP��servlet","רҵ");
		Course c17 = new Course(1101025,"���������","רҵ");
		Course c18 = new Course(1101026,"c++�������","רҵ");
		Course c19 = new Course(1101027,"VB�������","רҵ");
		Course c20 = new Course(1101028,"java�������","רҵ");
		Course c21 = new Course(1101029,"C���Գ������","רҵ");
		
		CourseDao courdao = CourseDaoFactory.getInstance();
		courdao.add(c1);
		courdao.add(c2);
		courdao.add(c3);
		courdao.add(c4);
		courdao.add(c5);
		courdao.add(c6);
		courdao.add(c7);
		courdao.add(c8);
		courdao.add(c9);
		courdao.add(c10);
		courdao.add(c11);
		courdao.add(c12);
		courdao.add(c13);
		courdao.add(c14);
		courdao.add(c15);
		courdao.add(c16);
		courdao.add(c17);
		courdao.add(c18);
		courdao.add(c19);
		courdao.add(c20);
		courdao.add(c21);
	}
	
	@Test
	public void add(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		Course c1 = new Course(1101009,"�ߵ���ѧ���£�","��ѧ");
		courdao.add(c1);
	}
	
	@Test
	public void fund(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		System.out.println("��ѯ�����"+courdao.find(1101009));
		System.out.println("��ѯ�����"+courdao.find(1101010));
		System.out.println("��ѯ�����"+courdao.find(1101011));
		System.out.println("��ѯ�����"+courdao.find(1101001));
	}
	
	@Test
	public void update(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		Course cour = courdao.find(1101009);
		cour.setName("�γ�");
		cour.setType(null);
		courdao.update(cour);
	}
	
	@Test
	public void traverse_1(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		List<Course> list = courdao.traverse();
		
		Iterator<Course> iter = list.iterator();
		while(iter.hasNext()){
			System.out.println("�γ̣�"+iter.next());
		}
	}
	
	@Test
	public void traverse_2(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		List<Course> list;
		int i;
		for(i=1 ; i<10 ; i++){
			list = courdao.traverse(i,5,null);
			if(list != null){
				System.out.println("��"+i+"ҳ");
				for(Course cour: list){
					System.out.println(cour);
				}
			}else{
				System.out.println("δ��ѯ����ؼ�¼");
			}
		}
	}
	
	@Test
	public void delete(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		Course cour = courdao.find(1101009);
		courdao.delete(cour);
	}
};
