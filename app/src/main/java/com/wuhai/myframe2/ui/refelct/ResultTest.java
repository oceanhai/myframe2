package com.wuhai.myframe2.ui.refelct;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

    /**
     * @author create by lxn
     * @date 2018/4/19 11:22
     **/
    public class ResultTest {

        public static void main(String[] args) throws Exception {

            Result result = new Result();
            result.setCode("10000");
            result.setMsg("成功");
            System.out.println("属性-----");
            System.out.println("方法-----");
            Method[] declaredMethods = Result.class.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                String name = declaredMethods[i].getName();
                System.out.println(name);
                String mod = Modifier.toString(declaredMethods[i].getModifiers()); // 取得访问权限
                String metName = declaredMethods[i].getName(); // 取得方法名称
            }
            System.out.println("属性值-----");
            Field field = Result.class.getDeclaredField("code");
            field.setAccessible(true);//TODO 类中的成员变量为private,故必须进行此操
            Object code = field.get(result);
            System.out.println("result的code="+code);

            System.out.println("类名-----");
            String filename = Result.class.getSimpleName().toLowerCase();
            System.out.println(filename);
            StringBuffer sb = new StringBuffer();
            sb.append("<date>");
            sb.append("<"+filename+">");
            Field[] fields = Result.class.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                sb.append("<"+name+">");
                Method m = Result.class.getMethod("get" +upperCase1th(name));
                String value = (String) m.invoke(result);
                sb.append(value);
                sb.append("</"+name+">");
                System.out.println(name);
            }
            sb.append("</"+filename+">");
            sb.append("</date>");
            System.out.println("拼装的xml："+sb.toString());
        }

        public static String upperCase1th(String str){
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
