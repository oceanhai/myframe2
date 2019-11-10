package com.example.java_lib.m2017.meican;


import com.example.java_lib.m2017.bitedalu.TreeNodeTest;

/**
 * Created by wuhai on 2018/4/25.
 */

public class AlgorithmTreeNode {

    /**
     *              5
     *         2        7
     *      1   3，8         11
     *
     */
    public static void main(String[] args){
        //二叉树1
        TreeNodeTest.TreeNode node5 = new TreeNodeTest.TreeNode(5);
        TreeNodeTest.TreeNode node2 = new TreeNodeTest.TreeNode(2);
        TreeNodeTest.TreeNode node7 = new TreeNodeTest.TreeNode(7);
        TreeNodeTest.TreeNode node1 = new TreeNodeTest.TreeNode(1);
        TreeNodeTest.TreeNode node3 = new TreeNodeTest.TreeNode(3);// 3:true;8:false
        TreeNodeTest.TreeNode node11 = new TreeNodeTest.TreeNode(11);
        node5.setLeft(node2);
        node5.setRight(node7);
        node2.setLeft(node1);
        node2.setRight(node3);
        node7.setRight(node11);

        //二叉树2
//        TreeNodeTest.TreeNode node4 = new TreeNodeTest.TreeNode(4);
//        node1.setLeft(node4);

        //二叉树3
//        TreeNodeTest.TreeNode node2a = new TreeNodeTest.TreeNode(2);
//        node3.setLeft(node2a);   //false 如果把抛异常=限制去掉就true
//        TreeNodeTest.TreeNode node1a = new TreeNodeTest.TreeNode(1);
//        node3.setLeft(node1a);    //false
//        TreeNodeTest.TreeNode node3a = new TreeNodeTest.TreeNode(3);
//        node3.setLeft(node3a);    //false 如果把抛异常=限制去掉就true
//        TreeNodeTest.TreeNode node4 = new TreeNodeTest.TreeNode(4);
//        node3.setRight(node4);    //true
//        TreeNodeTest.TreeNode node5a = new TreeNodeTest.TreeNode(5);
//        node3.setRight(node5a);    //false 如果把抛异常=限制去掉就true

        //二叉树4
//        TreeNodeTest.TreeNode node6 = new TreeNodeTest.TreeNode(6);
//        node7.setLeft(node6);   //true
//        TreeNodeTest.TreeNode node9 = new TreeNodeTest.TreeNode(9);
//        node7.setLeft(node9);   //false

        //遍历下节点我们
        TreeNodeTest.preorderTraversalRec(node5);
        System.out.println();

        try {
            judgeIsTree(node5,Integer.MIN_VALUE,Integer.MAX_VALUE);
            System.out.println("true");
        }catch (Exception e){
            System.out.println("false");
        }

    }

    static class StopMsgException extends RuntimeException {
    }

    public static void judgeIsTree(TreeNodeTest.TreeNode node, int min, int max){

        if(node.getVal() <= min || node.getVal() >= max){
            // 跳出
            throw new StopMsgException();
        }
        if(node.getLeft() != null){
            judgeIsTree(node.getLeft(),min,node.getVal());//左节点 node.getLeft()>=node.getVal() 将异常
        }

        if(node.getRight() != null){
            judgeIsTree(node.getRight(),node.getVal(),max);//右节点 node.getRight() <= node.getVal()将异常
        }

    }

}
