package com.example.java_lib.sort;

/**
 * Created by Administrator on 2016/10/16.
 */
public class SortUtils {


    public static void main(String[] args){
//        int[] arr = new int[]{1,5,3,7,6,11,5,10,19,4,5};
        int[] arr = new int[]{12,30,5,16,5,1,20};
//        maopaoSort(arr);
//        System.out.println();
//        xuanzheSort(arr);
//        System.out.println();
//        charuSort(arr);
//        System.out.println();

        qsort(arr,0,arr.length-1);
    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void maopaoSort(int[] arr){
        for(int x=0;x<arr.length-1;x++){
            for(int y=0;y<arr.length-1-x;y++ ){
                if(arr[y] > arr[y+1]){
                    int temp = arr[y];
                    arr[y] = arr[y+1];
                    arr[y+1] = temp;
                }
            }
        }

        print(arr);
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void xuanzheSort(int[] arr){
        for (int x=0;x<arr.length-1;x++){
            for(int y=x+1;y<arr.length;y++){
                if(arr[x] > arr[y]){
                    int temp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = temp;
                }
            }
        }

        print(arr);
    }


    /**
     * 插入排序
     * @param arr
     */
    public static void charuSort(int[] arr){
        for(int x=1;x<arr.length;x++){
            for(int y=x;y>0;y--){
                if(arr[y]<arr[y-1]){
                    int temp = arr[y];
                    arr[y] = arr[y-1];
                    arr[y-1] = temp;
                }
            }
        }

        print(arr);
    }


    private static int num=0;

    /**
     * 快排序2  ※还是这个感觉思路更舒服些
     * 首先需要一个数组存放所有的数据
     * 定一个开始位置和一个结束为止
     * 选择一个数作为准基数
     */
    public static int[] qsort(int arr[],int min,int max) {
        int key=arr[min];//准基数
        int start=min; //开始位置
        int end =max;//结束位置

        while(end>start) {  //循环条件是否数值交叉
            //从后开始往前查找
            while(end>start && arr[end]>=key) {
                //如果找到的值大于基数值，那么继续往下找，end--
                end--;
            }
            //如果找到的值小于基数值，那么进行值交换
            if(arr[end] < key) {
                int temp=arr[end];
                arr[end]=arr[start];
                arr[start]=temp;
            }

            //从前往后找
            while(end>start && arr[start]<=key) {
                //如果找到的值小于基数值，那么继续往下找，start++
                start++;
            }
            //如果找到的值大于基数值，那么进行值交换
            if(arr[start] > key) {
                int temp=arr[start];
                arr[start]=arr[end];
                arr[end]=temp;
            }
        }

        System.out.println();
        System.out.println("第"+(++num)+"轮 start="+start+",end="+end);
        print(arr);

        //这部分的数据都是小于准基数，通过递归在进行一趟快排
        if(start>min) {
            qsort(arr, min, start-1);  //开始位置为第一位，结束位置为关键索引-1
        }

        if(end<max) {
            qsort(arr, end+1, max);  //开始位置为关键索引+1，结束位置最后一位
        }

        return arr;
    }

    private static void print(int[] arr){
        for(int x=0;x<arr.length;x++){
            if(x != arr.length-1){
                System.out.print(arr[x]+",");
            }else{
                System.out.print(arr[x]);
            }
        }
    }


}
