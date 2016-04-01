package com.hhsh.test;

import java.util.Date;


class EAM_Project  {
	private Long   p_Id; //项目编号	 
	private String a_Xmxh;  //序号
	private Date p_Jgrq; //日期
	private boolean is_ok;//状态
	public Long getP_Id() {
		return p_Id;
	}
	public void setP_Id(Long p_Id) {
		this.p_Id = p_Id;
	}
	 
	public String getA_Xmxh() {
		return a_Xmxh;
	}
	public void setA_Xmxh(String a_Xmxh) {
		this.a_Xmxh = a_Xmxh;
	}
	public Date getP_Jgrq() {
		return p_Jgrq;
	}
	public void setP_Jgrq(Date p_Jgrq) {
		this.p_Jgrq = p_Jgrq;
	}
	public boolean isIs_ok() {
		return is_ok;
	}
	public void setIs_ok(boolean is_ok) {
		this.is_ok = is_ok;
	}
}
