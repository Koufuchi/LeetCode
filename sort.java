import java.util.*;

public class sort {

    public static int[] insertionSort(int[] arr){
        // Time Complex: O(n^2)
        // Space Complex: O(1)

        //雙指標，temp存右指標所在值，當(左>右) => 左右互換 => 右指標暫不動，左指標在while迴圈不斷交換
        for(int i=1; i<arr.length; i++){
            int temp = arr[i];
            int j = i-1;
            // 因為布林邏輯是從左到右判斷的，若使用 array[j] > temp && j >= 0 則會先拋出 Index -1 out of bounds 的錯誤，但順序調換則不會
            while( j >= 0 && arr[j] > temp ){
                arr[j+1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr){
        // Time Complex: O(n^2)
        // Space Complex: O(1)
        
        for(int i=0; i<arr.length; i++){
            int min = i;
            // 找出最小的索引值用於交換
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[min]) min = j; 
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp; 
        }
        return arr;
    }

    public static int[] mergeSort(int[] arr){

        return arr;
    }

    public static int[] bubbleSort(int[] arr){
        // Time Complex: O(n^2)
        // Space Complex: O(1)

        for(int i=0; i<arr.length-1; i++){
            Boolean flag = false;
            for(int j=1; j<arr.length-1-i; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if(!flag) continue;
        }
        return arr;
    }

    public static void main(String[] args){

        int[] A1 = {31,41,59,26,41,5831,41,59,26,41,58,31,41,59,26,41,58,31,41,59,26,41,5831,41,59,26,41,58,31,41,59,26,41,58};

        /*int[] randArr = new int[10] ;
        Random random = new Random();
        for(int i=0; i<10; i++){
            randArr[i] = random.nextInt(10);
        }*/

        long rs = LC.now(); 
        System.out.println("插入排序結果 : " + Arrays.toString(insertionSort(A1)));
        LC.runtime(rs);

        rs = LC.now(); 
        System.out.println("選擇排序結果 : " + Arrays.toString(selectionSort(A1)));
        LC.runtime(rs); 
        
        rs = LC.now();
        System.out.println("泡沫排序結果 : " + Arrays.toString(bubbleSort(A1)));
        LC.runtime(rs);
    }

}
