package com.example;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //                      F
        //             B              G
        //      A          D                 I
        //                C    E         H


        TreeNode nodeF = new TreeNode("F");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeG = new TreeNode("G");
        TreeNode nodeA = new TreeNode("A");
        TreeNode nodeD = new TreeNode("D");
        TreeNode nodeI = new TreeNode("I");
        TreeNode nodeC = new TreeNode("C");
        TreeNode nodeE = new TreeNode("E");
        TreeNode nodeH = new TreeNode("H");

        nodeF.setLeftNode(nodeB);
        nodeF.setRightNode(nodeG);

        nodeB.setLeftNode(nodeA);
        nodeB.setRightNode(nodeD);

        nodeG.setRightNode(nodeI);

        nodeD.setLeftNode(nodeC);
        nodeD.setRightNode(nodeE);

        nodeI.setLeftNode(nodeH);

//        printInOrderTree(nodeF);
//        printPreOrderTree(nodeF);
//        printPostOrderTree(nodeF);
        printLayerTravelTree(nodeF);
        TreeNode root = reverseBinaryTree(nodeF);
//        TreeNode root = flatten(nodeF);
        printTree(nodeF);
    }

    /**
     * 中序遍历
     *
     * @param root 根节点
     */
    private static void printInOrderTree(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrderTree(root.getLeftNode());
        System.out.println(root.getValue());
        printInOrderTree(root.getRightNode());
    }

    /**
     * 前序遍历
     *
     * @param root 根节点
     */
    private static void printPreOrderTree(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getValue());
        printPreOrderTree(root.getLeftNode());
        printPreOrderTree(root.getRightNode());
    }

    /**
     * 后续遍历
     *
     * @param root 根节点
     */
    private static void printPostOrderTree(TreeNode root) {
        if (root == null) {
            return;
        }
        printPostOrderTree(root.getLeftNode());
        printPostOrderTree(root.getRightNode());
        System.out.println(root.getValue());
    }

    /**
     * 广度优先遍历
     *
     * @param root 根节点
     */
    private static void printLayerTravelTree(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.getValue());
            if (current.getLeftNode() != null) {
                queue.add(current.getLeftNode());
            }
            if (current.getRightNode() != null) {
                queue.add(current.getRightNode());
            }
        }
    }

    /**
     * 翻转二叉树
     * @param root 根节点
     * @return tree
     */
    private static TreeNode reverseBinaryTree(TreeNode root){
        //先处理base case，当root ==null 时，什么都不需要做,返回空指针
        if(root == null){
            return null;
        }
        else{
            //把左子树翻转
            TreeNode left = reverseBinaryTree(root.getLeftNode());
            //把右子树翻转
            TreeNode right = reverseBinaryTree(root.getRightNode());
            //把左右子树分别赋值给root节点，但是是翻转过来的顺序
            root.setLeftNode(right);
            root.setRightNode(left);
            //返回根节点
            return root;
        }
    }

    /**
     * 平铺二叉树
     *
     * @param root 根节点
     * @return 翻转结果
     */
    private static TreeNode flatten(TreeNode root) {
        //base case
        if (root == null) {
            return null;
        } else {
            //用递归的思想，把左右先铺平
            TreeNode left = flatten(root.getLeftNode());
            TreeNode right = flatten(root.getRightNode());
            //把左指针和右指针先指向空。
            root.setLeftNode(null);
            root.setRightNode(null);
            //假如左子树生成的链表为空，那么忽略它，把右子树生成的链表指向根节点的右指针
            if (left == null) {
                root.setRightNode(right);
                return root;
            }
            //如果左子树生成链表不为空，那么用while循环获取最后一个节点，并且它的右指针要指向右子树生成的链表的头节点
            root.setRightNode(left);
            TreeNode lastLeft = left;
            while (lastLeft != null && lastLeft.getRightNode() != null) {
                lastLeft = lastLeft.getRightNode();
            }
            lastLeft.setRightNode(right);
            return root;
        }
    }


    /**
     * 打印二叉树
     *
     * @param root 根节点
     */
    private static void printTree(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.getValue());
            if (node.getLeftNode() != null) {
                queue.add(node.getLeftNode());
            }
            if (node.getRightNode() != null) {
                queue.add(node.getRightNode());
            }
        }
    }

}
