package cn.lry.domains;

public class Admin extends Person {

	/**
	 * @function    �޲ι�����
	 */
	public Admin() {
		super();
	}
	
	/**
	 * @function    ������
	 * @param id
	 *              ����ԱID
	 * @param name
	 *              ����Ա����
	 * @param pwd
	 *              ����Ա����
	 */
	public Admin(int id, String name, String pwd) {
		super(id, name, pwd);
	}
	
	/**
	 * @statement    ��дtoString()����
	 * @function     ������Ϣ���
	 * @return       ���ض���ʵ���Ļ�����Ϣ
	 */
	@Override
	public String toString() {
		return "Admin [getId()=" + getId() + ", getName()=" + getName()
				+ ", getPwd()=" + getPwd() + "]";
	}
	
};
