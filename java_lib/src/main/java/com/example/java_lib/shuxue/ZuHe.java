package com.example.java_lib.shuxue;

import java.util.Arrays;

/**
 * 组合
 */
public class ZuHe {
    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("***********循环*************");
//        ergodicArray(a, 6);
        System.out.println("***********递归*************");
        recursionArray(a,new int[6],0,0);
    }
    //递归
    public static void recursionArray(int[] a,int[] b,int start,int index){

        if(b.length>a.length)
            throw new RuntimeException("长度错误");
        else{
            if(index<b.length){
                int[] c=b.clone();//克隆 互不影响
                for(int i=start;i<a.length;i++){
                    //System.out.println("i:"+i+" index:"+index);
                    c[index]=a[i];
                    recursionArray(a,c,i+1,index+1);
                }
            }else
                System.out.println(Arrays.toString(b));
        }

    }
    //循环
    public static void ergodicArray(int[] a,int length){
        if(length>a.length)
            throw new RuntimeException("长度错误");
        else{
            int[] b=new int[length];
            for(int i=0;i<length;i++)
                b[i]=i;
            ergodicArray(a,b);
        }
    }

    public static void ergodicArray(int[] a, int[] b) {

        while (b[0] <= (a.length - b.length)) {
            if (b[b.length - 1] < a.length) {
                for (int i = 0; i < b.length; i++) {
                    System.out.print(a[b[i]]);
                    if(i<b.length-1)
                        System.out.print(',');
                }
                System.out.println();
                ++b[b.length - 1];
            } else {
                int j = b.length - 1;
                while (j >= 0) {
                    if (b[j] != a.length - (b.length - j) + 1) {
                        // j--;
                        break;
                    } else
                        j--;
                }
                if ((b[0] == (a.length - b.length - 1) || b[j] <= (a.length - b.length + j))) {
                    int t = 0;
                    for (int i = j; i < b.length; i++) {
                        if (i == j) {
                            b[i]++;
                        } else {
                            b[i] = b[i - 1] + 1;
                        }
                    }
                }
            }
        }

    }
}
