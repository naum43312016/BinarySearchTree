package com.naum.asafov;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new TreeImpl<>();
        tree.insert(40);
        tree.insert(28);
        tree.insert(55);
        tree.insert(32);
        tree.insert(23);
        tree.insert(20);


        tree.remove(23);
        System.out.println(tree);
    }
}
