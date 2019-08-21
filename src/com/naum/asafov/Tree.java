package com.naum.asafov;

public interface Tree<T extends Comparable<T>> {


    void insert(T value);

    boolean search(T value);

    void remove(T value);

}
