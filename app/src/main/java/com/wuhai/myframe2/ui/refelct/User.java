package com.wuhai.myframe2.ui.refelct;

/**
 * @author: 张唯
 * @类 说 明:
 * @version 1.0
 * @创建时间：2014年11月26日 下午3:39:45
 * 
 */
public class User {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	
	private void showName(){
		System.out.println("通过反射机制执行私有的方法且没有参数");
	}
	
	public void showName(String name,int age){
		System.out.println("通过反射机制执行公开的方法且有参数" + ":------------->>>" + name + age);
	}

}
