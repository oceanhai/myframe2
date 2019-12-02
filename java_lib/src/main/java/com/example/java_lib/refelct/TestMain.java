package com.example.java_lib.refelct;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: 张唯
 * @类   说   明:	
 * @version 1.0
 * @创建时间：2014年11月26日 下午3:39:54
 * 
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
//		cls= Class.forName("com.qianfeng.refelct.User");
		cls = User.class;
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

		//通过反射执行 无参方法
		Method method1 = cls.getDeclaredMethod("showName", new Class[]{});
		method1.setAccessible(true);
		Object object = method1.invoke(user, new Object[]{});

		//TODO 调用有参数的方法  没成功
		Method method2 = cls.getDeclaredMethod("showName", new Class[]{});
		method2.setAccessible(true);
//		//得到方法中的所有参数信息
//		Class<?>[] parameterClazz = method2.getParameterTypes();
//		List<Object> listValue = new ArrayList<Object>();
//		//方法的  入参
//		List<Object> list = new ArrayList<Object>();
//		list.add("wuhai");
//		list.add(11);
//		//循环参数类型
//		for(int i=0; i<parameterClazz.length; i++){
//			fillList(listValue, parameterClazz[i],list.get(i));
//		}
//		Object object2 = method2.invoke(user, listValue.toArray());

		//便利所有方法
		Method [] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
	
		
		
	}

	private static void fillList(List<Object> list, Class<?> parameter,Object value) {
		System.out.println(parameter.getTypeName());
		if("java.lang.String".equals(parameter.getTypeName())){
			list.add(value);
		}else if("java.lang.Character".equals(parameter.getTypeName())){
			char[] ch = ((String)value).toCharArray();
			list.add(ch[0]);
		}else if("char".equals(parameter.getTypeName())){
			char[] ch = ((String)value).toCharArray();
			list.add(ch[0]);
		}else if("java.lang.Double".equals(parameter.getTypeName())){
			list.add(Double.parseDouble((String) value));
		}else if("double".equals(parameter.getTypeName())){
			list.add(Double.parseDouble((String) value));
		}else if("java.lang.Integer".equals(parameter.getTypeName())){
			list.add(Integer.parseInt((String) value));
		}else if("int".equals(parameter.getTypeName())){
			list.add(Integer.parseInt((String) value));
		}else if("java.lang.Long".equals(parameter.getTypeName())){
			list.add(Long.parseLong((String) value));
		}else if("long".equals(parameter.getTypeName())){
			list.add(Long.parseLong((String) value));
		}else if("java.lang.Float".equals(parameter.getTypeName())){
			list.add(Float.parseFloat((String) value));
		}else if("float".equals(parameter.getTypeName())){
			list.add(Float.parseFloat((String) value));
		}else if("java.lang.Short".equals(parameter.getTypeName())){
			list.add(Short.parseShort((String) value));
		}else if("shrot".equals(parameter.getTypeName())){
			list.add(Short.parseShort((String) value));
		}else if("java.lang.Byte".equals(parameter.getTypeName())){
			list.add(Byte.parseByte((String) value));
		}else if("byte".equals(parameter.getTypeName())){
			list.add(Byte.parseByte((String) value));
		}else if("java.lang.Boolean".equals(parameter.getTypeName())){
			if("false".equals(value) || "0".equals(value)){
				list.add(false);
			}else if("true".equals(value) || "1".equals(value)){
				list.add(true);
			}
		}else if("boolean".equals(parameter.getTypeName())){
			if("false".equals(value) || "0".equals(value)){
				list.add(false);
			}else if("true".equals(value) || "1".equals(value)){
				list.add(true);
			}
		}
	}
}
