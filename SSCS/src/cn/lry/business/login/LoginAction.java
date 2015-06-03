package cn.lry.business.login;

import cn.lry.dao.proxy.DaoProxy;
import cn.lry.domains.Student;
import cn.lry.utils.EncryptUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//�û�id
	private int id;
	//����
	private String pwd;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	private boolean verifyId(int id){
		boolean flag = true;
		if(id == 0 ||  new Integer(id) == null){
			flag = false;
			//ƥ�䣺20** ****
		}else if(!Integer.toString(id).matches("^[2]{1}[0]{1}\\d{6}$")){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * ����У��
	 * @param pwd
	 * @return
	 */
	private boolean verifyPwd(String pwd){
		boolean flag = true;
		//����У��
		if(this.pwd == null || "".equals(this.pwd.trim())){
			flag = false;
			//ƥ��6-12λ��ĸ/����/�»������
		}else if(!this.pwd.trim().matches("^[a-zA-Z0-9_]{6,12}$")){
			flag = false;
		}
		return flag;
	}
	
	public String login() throws Exception {
		
		System.out.println("id = "+this.id);
		System.out.println("pwd = "+this.pwd);
		
		//action������ͼ���Ͷ���
		String view = "err";
		Student stu = null;
		
		//У��id,�����ʽ�Ƿ���ȷ
		if(this.verifyId(this.id) && this.verifyPwd(this.pwd)){
			stu = DaoProxy.finddStu(this.id);
			//У��id�Ƿ���ڶ�Ӧʵ�壬�����ڣ�У�������Ƿ���ȷ
			
			System.out.println("ʵ�����룺"+stu.getPwd());
			System.out.println("��½���룺"+EncryptUtils.getMD5Algo(this.pwd));
			
			if(stu !=null && 
					stu.getPwd().equals(EncryptUtils.getMD5Algo(this.pwd))){
				//У��ɹ���������ͼ
				view = "stu";
			}
		}
		
		if("stu".equals(view)){
			//���õ�½���󣬷ַ���jsp����
			ActionContext.getContext().put("stu",stu);
		}else{
			ActionContext.getContext().put("err","ID��"+this.id+"\tpassword��"+this.pwd);
		}
		
		return view;
	}
};













