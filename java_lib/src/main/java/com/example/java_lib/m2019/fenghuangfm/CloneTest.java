package com.example.java_lib.m2019.fenghuangfm;

import org.junit.Test;

public class CloneTest {

    @Test
    public void testShallowCopy() throws Exception{

        Address address=new Address();
        address.setType("Home");
        address.setValue("北京");

        Person p1=new Person();
        p1.setAge(31);
        p1.setName("Peter");
        p1.setAddress(address);

        Person p2=(Person) p1.clone();

        System.out.println(p1==p2);//false

        p2.setName("Jacky");
        p2.getAddress().setType("Office");
        System.out.println("p1="+p1);//p1=Person [name=Peter, age=31]
        System.out.println("p2="+p2);//p2=Person [name=Jacky, age=31]

    }

}

class Person implements Cloneable {
    private String name;
    private Integer age;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object = super.clone();
        Address address = ((Person)object).getAddress();
        ((Person)object).setAddress((Address) address.clone());
        return object;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}

class Address implements Cloneable {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Address{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}