package com.zy.learning;

import java.util.Random;

/**
 * Created by zhougb on 2016/7/19.
 */
public class SimpleSort {
    private static int [] arr = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    private static Random random = new Random();

    private static void initArr(){
        for(int i=0;i<arr.length;i++){
            arr[i] = -1;
        }
        int count = arr.length;
        while (count > 0){
            int r = random.nextInt(arr.length*4);
            //System.out.println("r:"+r);
            int hash = r % arr.length;
            if (arr[hash] >= 0)
                continue;
            else {
                arr[hash] = r;
                count--;
            }
        }
        printArr();
    }

    private static void printArr(){
        for (int i : arr){
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println();
    }

    public static void bubbleSort(){
        for(int out=arr.length-1; out >= 0; out--){
            for(int in=0; in < arr.length-1; in++){
                if (arr[in+1] > arr[in]){
                    swap(in+1, in);
                }
            }
        }
    }

    public static void selectSort(){
        for(int out=0; out < arr.length; out++){
            int minIdx = out;
            for(int in = out + 1; in < arr.length; in++){
                if (arr[in] < arr[minIdx]){
                    minIdx = in;
                }
            }
            swap(minIdx, out);
        }
    }

    public static void insertSort(){
        for(int out=0; out < arr.length-1; out++){
            int tmp = arr[out];
            int in = out;
            /*while (in>=0 && arr[in+1] < arr[in]){
                swap(in, in+1);
                in--;
            }*/
            while (in > 0 && arr[in-1] < tmp) {
                arr[in] = arr[in - 1];
                in--;
            }
            arr[in] = tmp;
        }
    }

    public static void doAnagam(int size){
        if (size == 1)
            return;
        for(int i=0; i< size; i++){
            doAnagam(size-1);
            if (size == 2)
                printArr();
            rotate(size);
        }
    }

    private static void rotate(int size){
        for(int i=1; i<size-1; i++){
            swap(i+1, i);
        }
        swap(0, size-1);
    }

    private static void swap(int fi, int si){
        int tmp = arr[fi];
        arr[fi] = arr[si];
        arr[si] = tmp;
    }

    public static void merge(int workspace[], int lower, int mid, int upper){
        int alen = mid - lower;
        int blen = upper - mid;

        int j = 0;
        int aidx = lower;
        int bidx = mid;
        while (lower <= mid && mid <= upper){
            if (arr[lower] < arr[mid])
                workspace[j++] = arr[lower++];
            else
                workspace[j++] = arr[mid++];
        }

        while (lower <= mid)
            workspace[j++] = arr[lower++];

        while (mid <= upper)
            workspace[j++] = arr[mid++];
    }

    public static void recMerge(int workspace[], int lowbound, int upperbound){
        if (lowbound == upperbound)
            return ;

        int mid = (lowbound + upperbound) + 1;
        recMerge(workspace, lowbound, mid);
        recMerge(workspace, mid+1, upperbound);
        merge(workspace, lowbound, mid, upperbound);
    }



    public static void shellSort(){
        int in, out;
        int i;

        int h = 0;
        while (h < arr.length/3)
            h = h*3 + 1;

        while (h > 0){
            for(out = h; out < arr.length-1; out++){
                int tmp = arr[out];
                in = out;
                while (in > h-1 && arr[in-1] < tmp){
                    arr[in-1] = tmp;
                    in =- h;
                }
            }
            System.out.println(h);
            h = (h-1) / 3;
        }
    }

    public static int paritionIt(int left, int right, int provit){
        int lefti = left - 1;
        int righti = right;
        while (true){
            while (arr[++lefti] < provit);
            while (righti > 0 && arr[--righti] > provit);

            if (lefti >= righti )
                break;
            else {
                swap(lefti, righti);
            }
        }
        swap(lefti, right);
        return lefti;
    }

    public static void recQuickSort(int left, int right){
        if (right - left <= 0){
            return;
        }else {
            //System.out.println("par:"+right);
            int provit = arr[right];
            int partiion = paritionIt(left, right, provit);
            System.out.println("par:"+partiion);
            recQuickSort(left, partiion-1);
            recQuickSort(partiion+1, right);
        }
    }

    //---------  apply medianof3
    private static int medianof3(int left, int right){
        int center = (left + right) /2;

        if (arr[left] > arr[center])
            swap(left, center);

        if (arr[left] > arr[right])
            swap(left, right);

        if (arr[center] > arr[right])
            swap(center, right);

        swap(center, right-1);
        return arr[right-1];
    }

    public static int paritionIt2(int left, int right, int provit){
        int lefti = left;
        int righti = right-1;
        while (true){
            while (arr[++lefti] < provit);
            while (arr[--righti] > provit);

            if (lefti >= righti )
                break;
            else {
                swap(lefti, righti);
            }
        }
        swap(lefti, right-1);
        return lefti;
    }

    public static void manualSort(int left, int right){
        int size = right - left + 1;
        if (size <= 1){
            return;
        }else if (size == 2){
            if (arr[left] > arr[right])
                swap(left, right);
        }else {
            if (arr[left] > arr[right-1])
                swap(left, right-1);
            if (arr[left] > arr[right])
                swap(left, right);
            if (arr[right-1] > arr[right])
                swap(right-1, right);
        }
    }

    public static void recQuickSort2(int left, int right){
        int size = right - left + 1;
        if (size <= 3){
            manualSort(left, right);
        }else {
            int provit = medianof3(left, right);
            int partiion = paritionIt2(left, right, provit);
            recQuickSort(left, partiion-1);
            recQuickSort(partiion+1, right);
        }
    }


    public static void main(String args[]){
        initArr();
        bubbleSort();
        printArr();

        initArr();
        selectSort();
        printArr();

        initArr();
        insertSort();
        printArr();

        /*arr = new int[]{3,4,5,6,7};
        doAnagam(arr.length);*/


        //shellSort();
        System.out.println("start partition:");
        initArr();
        //paritionIt(0, arr.length-1, 10);
        recQuickSort2(0, arr.length-1);
        printArr();

        StringBuffer  stringBuffer;
        StringBuilder stringBuilder;
    }
}