package com.app.algorithm;

import com.app.adt.IComparable;

public class InsertionSort <E extends IComparable<E>> implements ISort<E>{
    public void sort(Object[] data){
        for(int i = 1;i < data.length;i++){
            Object tmp = data[i];
            int j;
            for(j = i;j > 0 && data[j - 1].toString().compareTo(tmp.toString()) > 0;j--){
                data[j] = data[j - 1];
            }
            data[j] = tmp;
        }
    }
}
