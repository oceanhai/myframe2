package com.wuhai.myframe2.ui.refelct;

import java.lang.reflect.Method;

/**
 * 作者 wh
 *
 * 创建日期 2020/8/24 15:27
 *
 * 描述：反射测试类
 */
public class TestMain {
	
	/**
	 * 如果我们要使用反射 就必须获得这个类的Class对象
	 * 
	 * 三种方式获得这个Class对象
	 * 1.Class类的静态方法    Class.forName();
	 * 2.类或者接口的静态语法    String.class;
	 * 3.类或者接口的实例化对像   user.getClass();
 	 * 
	 * 
	 * 
	 *  
	 * 
	 * @Title: main 
	 * @说       明:
	 * @参       数: @param args   
	 * @return void    返回类型 
	 * @throws
	 */
	public static void main(String[] args)  throws Exception{
		//参数  包名 + 类名
		Class<?> cls  = null;
		/**
		 * 1.Class类的静态方法    Class.forName();
		 */
//		cls= Class.forName("com.qianfeng.refelct.User");
		/**
		 * 2.类或者接口的静态语法    String.class;
		 */
		cls = User.class;
		/**
		 * 3.类或者接口的实例化对像   user.getClass();
		 */
//		User user = new User();
//		cls = user.getClass();
//		System.out.println(cls.toString());
		User user = (User) cls.newInstance();
		//通过Class对象获取类的成员变量
//		Field field = cls.getDeclaredField("id");
//		Field nameField = cls.getDeclaredField("name");
//		field.setAccessible(true);
//		nameField.setAccessible(true);
//		nameField.set(user, "哇哈哈");
//		
//		
//		field.set(user, "1");
//		System.out.println(user.toString());
//		Field []  fields = cls.getDeclaredFields();

		/**
		 * 通过反射机制执行私有的方法且没有参数
		 */
		Method method1 = cls.getDeclaredMethod("showName", new Class[]{});
		method1.setAccessible(true);
		Object object = method1.invoke(user, new Object[]{});

		/**
		 * 反射获取 方法列表
		 */
		Method [] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
	
		
		
	}
}
