package com.wj.datastruct;

public class Tree {
    public Node root;

    public void insert(long data) {
        Node newNode = new Node(data);
        Node current = root;
        Node parent;
        if (root == null) {
            root = newNode;
            return;
        }
        while (true) {
            parent = current;
            if (data > current.data) {
                current = current.rightChild;
                if (current == null) {
                    parent.rightChild = newNode;
                    break;
                }
            } else {
                current = current.leftChild;
                if (current == null) {
                    parent.leftChild = newNode;
                    break;
                }
            }

        }

    }

    public Node find(long data) {
        Node current = root;
        while (data != current.data) {
            if (data > current.data) {
                current = current.rightChild;
            } else {
                current = current.leftChild;
            }
        }
        return current;
    }

    public void delete(long data) {

    }
}
