package com.wuhai.myframe2.ui.annotation;

import java.lang.reflect.Field;

/**
 * 作者 wh
 *
 * 创建日期 2020/8/24 15:44
 *
 * 描述：注解测试
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
