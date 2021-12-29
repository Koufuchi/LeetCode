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

    // no11 [39]
    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        /**
         * candidates = [2,3,6,7], targrt = 7
         * expect = [[2,2,3],[7]]
         * 
         * Backtracking 解，先排序組合元素，然後開始遞迴。
         * 每次用目標值減去組合元素的最大值，若剛好為 0 則為解，小於 0 則為無解，大於 0 則繼續下一層遞迴。
         * 
         * 還有更好的剪枝方案:
         *    (多傳一個 sum 進去，在每次進入下一圈前先判斷 nextsum [=sum+nums[i]] 是否大於 target，若是則直接跳過這圈)
         */
        //Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        combinationSumSub(candidates, target, 0, new ArrayList<>(), ans, 0);
        return ans;
    }
    static void combinationSumSub(int[] candidates, int target, int index, List<Integer> temp, List<List<Integer>> ans, int round) {
        System.out.println("");
        System.out.println("第 "+round+" 層");
        System.out.println("index : "+index);

        if(index == candidates.length){
            //System.out.println("index: "+index+"== candidates.length: "+candidates.length);
            if(target == 0){
                ans.add(new ArrayList<>(temp));
                System.out.println("加入新組合: "+ temp);
            }
            // 因為已經用過所有組合元素了，所以 return
            System.out.println("找完所有組合元素 return ");
            return;
        }

        if(candidates[index] <= target){

            //System.out.println("candidates[index]: "+candidates[index]+" <= target: "+target);
            temp.add(candidates[index]);
            //System.out.println("index : "+index);
            System.out.println("加入數字: "+candidates[index]+" ，temp 變為:"+ temp );
            combinationSumSub(candidates, target - candidates[index], index, temp, ans, round+1);
            temp.remove(temp.size()-1);
            System.out.println("拿掉數字後: "+temp);
        }

        System.out.println("尚有可能，把 index 加 1, 找下一個");
        combinationSumSub(candidates, target, index + 1, temp, ans, round+1);
        
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

        // no11
        int[] no11 = {2,7,6,3,5,1};
        rs = LC.now();
        System.out.println("no11 目標的所有可能組合為: " + combinationSum(no11, 9));
        LC.runtime(rs);
    }

}
