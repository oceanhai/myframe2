package com.example.java_lib.annotitation;

import java.lang.reflect.Field;

/**
 * @author: 张唯
 * @类 说 明:
 * @version 1.0
 * @创建时间：2014年11月26日 下午4:21:26
 * 
 */
public class TestMain {
	public static void main(String[] args) throws Exception {
		Class<?> cls = User.class;
		User user = (User) cls.newInstance();
		Field field = cls.getDeclaredField("id");
		if (field != null) {
			MeAnnotation meAnnotation = field.getAnnotation(MeAnnotation.class);
			if (meAnnotation!= null) {
				System.out.println(meAnnotation.id());
				System.out.println(meAnnotation.helloWord());
			}

		}

	}

}
