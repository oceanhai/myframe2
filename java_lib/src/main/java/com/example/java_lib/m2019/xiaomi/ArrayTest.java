package com.example.java_lib.m2019.xiaomi;

import java.util.Arrays;

public class ArrayTest {

    public static void main(String[] args){

        int[] arr1 = new int[]{1,2,5,9};
//        int[] arr1 = new int[]{1,2,5,11,12,13};
        int[] arr2 = new int[]{1,2,3,7,10,11,12,13};
//        int[] arr2 = new int[]{1,2,3,7,10};
        int[] arr3 = arrMerge(arr1,arr2);

        System.out.println(Arrays.toString(arrMerge(arr1,arr2)));
        System.out.println(Arrays.toString(arrMerge2(arr1,arr2)));
    }

    /**
     * 有序数组合并  自己的算法
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] arrMerge(int[] arr1, int[] arr2){

        int len1 = arr1.length;
        int len2 = arr2.length;
        System.out.println("len1="+len1+",len2="+len2);

        int[] arr3 = new int[len1+len2];

        int x=0,y=0,z=0;



        while (x<len1 || y<len2){
            System.out.println("x="+x+",y="+y+",z="+z);

            if(x>=len1 && y<len2){
                arr3[z]=arr2[y];
                y++;
                z++;
                continue;
            }

            if(y>=len2 && x<len1){
                System.out.println("-----x="+x+",y="+y);
                arr3[z]=arr1[x];
                x++;
                z++;
                continue;
            }
            System.out.println("前arr1[x]="+arr1[x]+",arr2[y]="+arr2[y]);

            if(arr1[x] > arr2[y]){
                arr3[z] = arr2[y];
                System.out.println("arr3[z]="+arr3[z]);
                y++;
                z++;
            }else if(arr1[x] < arr2[y]){
                arr3[z] = arr1[x];
                System.out.println("arr3[z]="+arr3[z]);
                x++;
                z++;
            }else{
                arr3[z] = arr1[x];
                System.out.println("arr3[z]="+arr3[z]);
                z++;
                x++;
                arr3[z] = arr1[y];
                System.out.println("arr3[z]="+arr3[z]);
                z++;
                y++;
            }
        }

        return arr3;
    }

    /**
     * 网上策略
     * @param num1
     * @param num2
     * @return
     */
    public static int[] arrMerge2(int[] num1, int[] num2){

        //变量用于存储两个集合应该被比较的索引（存入新集合就加一）
        int a = 0;
        int b = 0;
        int[] num3 = new int[num1.length + num2.length];

        for (int i = 0; i < num3.length; i++) {
            if (a < num1.length && b < num2.length) {   //两数组都未遍历完，相互比较后加入
                if (num1[a] > num2[b]) {
                    num3[i] = num2[b];
                    b++;
                } else {
                    num3[i] = num1[a];
                    a++;
                }
            } else if (a < num1.length) {   //num2已经遍历完，无需比较，直接将剩余num1加入
                num3[i] = num1[a];
                a++;
            } else if (b < num2.length) {    //num1已经遍历完，无需比较，直接将剩余num2加入
                num3[i] = num2[b];
                b++;
            }
        }

        return num3;
    }

}
