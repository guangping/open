package com.varela;


import org.testng.annotations.Test;

/**
 * Created by 51offer on 2016/3/8.
 */
public class ArrayTest {

    @Test
    public void max(){
        int arrays[]={5,7,9,11,14,16,23,25,162};
        int index=takeApart(arrays,23);
        System.out.println(index);
    }
    /**
     * 折半算法
     * */
    public int takeApart(int arrays[],int arg){
        int index=-1;

        int low=0,high=arrays.length-1;
        int mid=0;
        while (low<=high){
            mid=(low+high)/2;
            System.out.println("index:"+mid);
            if(arg==arrays[mid]){
                index=mid;
                break;
            }
            if(arg>arrays[mid]){
                low=mid+1;
            }else {
                high=mid-1;
            }
        }
        return index;
    }


    /**
     *
     * */
}
