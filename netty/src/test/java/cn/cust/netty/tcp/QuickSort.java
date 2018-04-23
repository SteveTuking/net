package cn.cust.netty.tcp;

/**
 * 递归快速排序
 * Created by zhangbing on 2018/3/24.
 */
public class QuickSort {

   public static void  quickSort(int[] target,int start,int end){
       if(start < end){
           int i = start + 1;
           int j = end;
           int goal = target[start];
           while(i <= j){
              while(i<=j&&target[i]<=goal) i++;
              while(i<=j&&target[j]>=goal) j--;
              if(i<j){
                  swap(target,i,j);
                  i++;
                  j--;
              }
           }
           swap(target,start,j);
           quickSort(target,start,j-1);
           quickSort(target,j+1,end);
       }
   }

   public static void swap(int[] target,int pos1,int pos2){
       int temp ;
       temp = target[pos1] ;
       target[pos1] = target[pos2];
       target[pos2] = temp;
   }
   public static void main(String args[]){
       int [] a = {5,8,7,2,9,3};
       quickSort(a,0,5);
       System.out.println(a);
   }

}

