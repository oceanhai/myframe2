package com.example.java_lib.shuxue;

public class ZuHe01 {

    public static void main(String[] args){
        System.out.println(getCombinationNum(20,6));
    }

    /**
     * C n m = n(n-1)(n-2)...(n-m+1)/m!
     * @param n
     * @param m
     * @return
     */
    public static int getCombinationNum(int n, int m){
        int num = -1;
        if(n<=0 || m<0 || n<m){
            return num;
        }

        if(m==0){
            return 1;
        }

        //m的阶乘
        int num2 = factorial(m);
        System.out.println("num2="+num2);
        //算被除数
        int num1 = n;
        for (int x=2;x<=m;x++){
            num1 = num1*(n-x+1);
            System.out.println("num1="+num1);
        }
        System.out.println("num1="+num1);

        return num1/num2;
    }

    /**
     * num的阶乘
     * @param num
     */
    public static int factorial(int num){
        if(num == 1){
            return 1;
        }else{
            return num*factorial(num-1);
        }
    }
}
