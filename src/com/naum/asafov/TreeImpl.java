package com.naum.asafov;

public class TreeImpl<T extends Comparable<T>> implements Tree<T>{

    Node<T> root = null;

    private static class Node<T extends Comparable<T>>{
        T value;
        Node<T> left,right;

        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    @Override
    public void insert(T value) {
        if (root == null){
            root = createRoot(value);
        }else {
            insertToTree(value,root);
        }
    }
    private void insertToTree(T value,Node<T> node){

        if (value.compareTo(node.value) > 0){
            if (node.right==null){
                node.right = createNewNode(value);
            }else {
                insertToTree(value,node.right);
            }
        }

        if (value.compareTo(node.value) < 0){
            if (node.left==null){
                node.left = createNewNode(value);
            }else {
                insertToTree(value,node.left);
            }
        }
    }

    private Node<T> createRoot(T value){
        return new Node<>(value,null,null);

    }
    private Node<T> createNewNode(T value){
        return new Node<>(value,null,null);
    }

    @Override
    public boolean search(T value) {
        return searchInTree(value,root);
    }

    private boolean searchInTree(T value,Node<T> node){
        if (node==null) return false;
        if (value.compareTo(node.value)==0){
            return true;
        }else if (value.compareTo(node.value)>0){
            return searchInTree(value,node.right);
        }else if (value.compareTo(node.value)<0){
            return searchInTree(value,node.left);
        }else {
            return false;
        }
    }
    @Override
    public void remove(T value) {
        removeFromTree(value,root,root);
    }

    private void removeFromTree(T value,Node<T> currentNode,Node<T> prevNode){
        if (currentNode!=null){
            if (value.compareTo(currentNode.value)==0){
                if (currentNode.right == null && currentNode.left == null){
                    removeLeaf(prevNode,currentNode);
                }else if (currentNode.right!=null && currentNode.left != null){
                    removeIfTwoChild(currentNode);
                }else{
                    removeIfOneChild(prevNode,currentNode);
                }

            }else if (value.compareTo(currentNode.value)>0){
                removeFromTree(value,currentNode.right,currentNode);
            }else if (value.compareTo(currentNode.value)<0){
                removeFromTree(value,currentNode.left,currentNode);
            }
        }
    }

    private void removeIfTwoChild(Node<T> currentNode){
        Node<T> successor = currentNode.right;
        while (successor.left!=null){
            successor = successor.left;
        }
        T value = successor.value;
        removeFromTree(value,root,root);
        currentNode.value = value;
    }


    private void removeIfOneChild(Node<T> prevNode,Node<T> currentNode){

        if (prevNode.right!=null) {
            if (prevNode.right.value.equals(currentNode.value)) {
                if (currentNode.right != null) {
                    prevNode.right = currentNode.right;
                    currentNode.right = null;
                    currentNode.left = null;
                } else {
                    prevNode.right = currentNode.left;
                    currentNode.right = null;
                    currentNode.left = null;
                }
            }
        }


        if (prevNode.left!=null) {
            if (prevNode.left.value.equals(currentNode.value)) {
                if (currentNode.right != null) {
                    prevNode.left = currentNode.right;
                    currentNode.right = null;
                    currentNode.left = null;
                } else {
                    prevNode.left = currentNode.left;
                    currentNode.right = null;
                    currentNode.left = null;
                }
            }
        }
    }

    private void removeLeaf(Node<T> prevNode,Node<T> currentNode){
        if (prevNode.right.value.equals(currentNode.value)){
            prevNode.right = null;
        }if (prevNode.left.value.equals(currentNode.value)){
            prevNode.left = null;
        }
    }

}
