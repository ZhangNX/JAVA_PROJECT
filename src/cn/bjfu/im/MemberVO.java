package cn.bjfu.im;

public class MemberVO { //�û���VO�࣬�����û���������
	private String name;
	private String pwd;
	public MemberVO(String t_name,String t_pwd	) {
		// ���췽��
		this.name=t_name;
		this.pwd=t_pwd;
	}
	public String getName() {//get,set����
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {//toString����
		return "MemberVO [name=" + name + ", pwd=" + pwd + "]";
	}
	
	
		
}
