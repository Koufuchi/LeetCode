import java.util.*;
//import java.util.Arrays;
public class Test {
    
    public static void main(String[] args){

        // 與 LC.java 位於同根目錄，不須也不能使用 import 或 package 導入

        // 於 LC.java 原檔內用 static 宣告，故可以直接調用方法
        int[] nums = {5,4,6,6,2,8};
        int target = 7;
        System.out.println("兩數相加為目標: " + Arrays.toString(LC.twoSum(nums, target)));

        // 不能用靜態方式呼叫非靜態方法
        // 要調用 public 宣告的方法時，必須先 new 其所在類別    
        LC object = new LC();
        String no2 = "ajcnddicmdjsxmzyeP";
        System.out.println("最長不重複字串: " + object.lengthOfLongestSubstring(no2));

        // 調用 public static 宣告的方法
        String no3 = "ajcnddicmdjsxmzyePbaaab";
        System.out.println("最長回文(鏡像字串): " + LC.longestPalindrome(no3));

        // 無法調用在其他類別中使用 private static 宣告的方法
    //  int[] no4 = {5,9,95,52,21,1,0,51,0,2,56,4};
    //  System.out.println("最大裝水量: " + LC.maxArea(no4));
    //  System.out.println("最大裝水量: " + object.maxArea(no4));

    // 由於 Queue和 LinkedList都是泛型，可用 <> 來實現與傳遞任何類型的資料。
   // Queue<TreeNode> queue = new LinkedList<TreeNode>(); // TreeNode 為類別名而非預設資料型態，全改成其他字串也可通 

    }

}