package com.wj.datastruct;

/**
 * 二叉树节点
 */
public class Node {

    public long data;
    public Node leftChild;
    public Node rightChild;

    public Node(long data, Node leftChild, Node rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(long data) {
        this.data = data;
    }
}
