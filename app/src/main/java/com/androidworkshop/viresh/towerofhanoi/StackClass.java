package com.androidworkshop.viresh.towerofhanoi;

/**
 * Created by viresh on 3/6/17.
 */

public class StackClass {
    int a[],size;
    StackClass(){
        a = new int[50];
        size=0;
    }

    void insert(int y){
        a[size]=y;
        size++;
    }

    void pop(){
        if(size>0) {
            size--;
        }
    }

    int top(){
        if(size>0) {
            return a[size - 1];
        }
        else{
            return 0;
        }
    }

    boolean isEmpty(){
        return size==0;
    }

}
