//import java.lang.reflect.Array;
import java.util.*;
/*import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List; // 重要
import java.util.Stack;*/

public class LC {
    //Test TreeNode = new Test();
    // no1
    static int[] twoSum(int[] nums, int target) {    

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();    
        for(int i=0; i < nums.length; i++){      
            if(map.containsKey(target - nums[i])){
                int[] ans = {i, map.get(target-nums[i])}; 
                return ans;
            }else{
                map.put(nums[i], i);
            }
        }
        return null;
    } 
    // no2
    public int lengthOfLongestSubstring(String s) {      

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int ans = 0;
        int i = 0;
        int del = 0;     
        while(i < s.length()){         
            if(!map.containsKey(s.charAt(i))){            
                map.put(s.charAt(i), i);
                ans = Math.max(ans, map.size());
                i++;    
            }else{
                map.remove(s.charAt(del)); 
                del++;
            }
        }
        return ans; 
    }
    // no3
    public static String longestPalindrome(String s) { 

        int start = 0, end = 0;     
        for(int i=0; i < s.length(); i++){
            int left = i-1, right = i+1;
            int c = s.charAt(i);
            while(left >= 0 && s.charAt(left) == c){    // to find the center
                left --;                
            }
            while(right < s.length() && s.charAt(right) == c){
                right ++;
            }
            
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){ // to find the palindrome
                left --;
                right ++;     
            }           
            left += 1;
            if(end - start < right - left){
                end = right;
                start = left;
            }        
        }
        return s.substring(start, end);
    }
    // no4
    private static int maxArea(int[] height) {

        int max = 0;
        for (int i = 0, j= height.length - 1; i < j; ) {
            int minHeight = height[i] < height[j] ? height[i++] : height[j--];
            max = Math.max(max, (j - i + 1) * minHeight);
        }

        return max;
    }
    // no5
    static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> ans = new LinkedList<List<Integer>>(); 
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int j = i + 1, k = nums.length - 1, target = 0 - nums[i];
                while (j < k) {
                    if (nums[j] + nums[k] == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while (j < k && nums[j] == nums[j + 1]) j++;
                        while (j < k && nums[k] == nums[k - 1]) k--;
                        j++; 
                        k--;
                    } 
                    else if (nums[j] + nums[k] < target) j++;
                    else k--;
               }
            }
        }
        return ans;
    }
    // no7
    static boolean isValid(String s) {

        Stack<Character> stack = new Stack<Character>();  
        for(int i=0; i < s.length(); i++){
            char c = s.charAt(i);
            if( c == '(' || c == '{' || c == '['){
                stack.push(c);
            }
            else if(stack.empty()){
                return false;
            }
            else if( c == ')' && '(' == stack.peek()){
                stack.pop();
            }
            else if( c == '}' && '{' == stack.peek()){
                stack.pop();
            }
            else if( c == ']' && '[' == stack.peek()){
                stack.pop();
            }
            else {return false; }
        }
        return stack.empty();
    }
    // no10 [33]////////////////////////////////////////////////////////////////////////////////
    static int search(int[] nums, int target) {
        /** nums = [4,5,6,7,0,1,2], target = 0
         *  expect = 4
         *  TC: O(log n), SC: O(1)
         *  給定數組為旋轉過一次的數組，一樣可以用二分搜尋法
         *  先找中點，若中點大於起點，則左半是有序的
         *           若中點小於終點，則右半是有序的
         */
        int left = 0;
        int right = nums.length-1;
        int mid = (right-left)/2; // JAVA 中兩個 int 型態互除會自動去除餘數
        /*while(left<right){
            if(nums[mid] == target){
                return nums[mid];
            }else if(nums[mid] < nums[right]){ // 右半邊為有序
                if(nums[mid] < target && target < nums[right]){

                }
            }else{
                right = mid;
            }
            mid = (right-left)/2;
        }*/
        return -1;
    }
    // no11 [39]
    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        /**
         * candidates = [2,3,6,7], targrt = 7
         * expect = [[2,2,3],[7]]
         * 
         * Backtracking 解，先排序組合元素，然後開始遞迴。
         * 每次用目標值減去組合元素的最大值，若剛好為 0 則為解，小於 0 則為無解，大於 0 則繼續下一層遞迴。
         */
        //Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        combinationSumSub(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }
    static void combinationSumSub(int[] candidates, int target, int index, List<Integer> temp, List<List<Integer>> ans) {
        if(target<0){
            return;
        }
        else if(target==0){
            ans.add(new ArrayList<>(temp));
        }else{
            // 迴圈跑這一層的所有可能
            for(int i=index; i<candidates.length; i++){
                temp.add(candidates[i]);
                // 進入下一圈
                combinationSumSub(candidates, target-candidates[i], i, temp, ans);
                temp.remove(temp.size()-1);
            }
        }
    }
    // no14 [53]
    static int maxSubArray(int[] nums) {
        /**
         * nums = [-2,1,-3,4,-1,2,1,-5,4] 
         * expect = 6
         * TC: O(n), SC: O(1)
         * DP 解: 對 [1...n] 求最大子串列的問題拆分為
         *          [1...n-1],[n] 的子問題
         *       => 如果知道 [1...n-1] 的解，就能推出 [1...n]的解
         *       1. 如果 [1...n-1] 的解包含 [n-1]，代表問題為此解加上 [n] 是否會變大。
         *       2. 如果不包含，代表問題為 [n] 是否大於 [1...n-2] 的解。
         */ 
        int curr = nums[0];
        int ans = nums[0];
        for(int i=1; i<nums.length; i++){
            if(curr+nums[i] > nums[i]){
                curr += nums[i];
                //ans = Math.max(ans, curr);
            }else{
                //ans = Math.max(ans, nums[i]);
                curr = nums[i];
            }
            if(curr > ans) ans = curr;
        }
        return ans;
    }
    // no18
    static int[][] insert(int[][] intervals, int[] newInterval) {    
   
        List<int[]> result = new LinkedList<>();
        int i=0;
        int length = intervals.length;
        while(i < length && intervals[i][1] < newInterval[0]){
            result.add(intervals[i++]);             
        }
        
        while(i < length && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
                i++;
        }
        
        result.add(newInterval);
        while(i < length){
            result.add(intervals[i++]);
        }

        return result.toArray(new int[result.size()][]);
    }
    // no27
    public List<List<Integer>> levelOrder(TreeNode root) {

        // 由於 Queue和 LinkedList都是泛型，可用 <> 來實現與傳遞任何類型的資料。
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // TreeNode 為類別名而非預設資料型態，全改成其他字串也可通
        List<List<Integer>> ans = new LinkedList<>();
        
        if(root == null) return ans;
        queue.offer(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new LinkedList<>();
            
            for(int i=0; i < size; i++){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            ans.add(temp);
        }
        return ans;
    }
    // no32
    public boolean isPalindrome(String s) {

        s = s.toLowerCase();
        int left = 0, right = s.length()-1;
        
        while(left < right){
            char cl = s.charAt(left);
            char cr = s.charAt(right);
            while(!Character.isLetterOrDigit(cl) && left < right) cl = s.charAt(++left);
            while(!Character.isLetterOrDigit(cr) && left < right) cr = s.charAt(--right);
            if(cl != cr) return false;
            left++; 
            right--;
        }  
        return true;
    }
    // no33
    public int longestConsecutive(int[] nums) {

        HashSet<Integer> set = new HashSet<Integer>();
        int max = 0;

        for(int i : nums){
            set.add(i);
        }     
        
        for(int i : set){
            int temp = 0;
            int target = i; 
            if(!set.contains(target-1)){ // 多這個 if 篩選直接從 557ms O(n^3) 降到 3ms O(n)
                while(set.contains(target++)) temp++;
                max = Math.max(max, temp);
            } 
        }     
        return max;
    }
    // no35
    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] sub = new boolean[s.length()+1];      
        sub[0] = true;     
        HashSet<String> set = new HashSet<String>(); 
        
        for(String i : wordDict){
            set.add(i);
        }
        
        for(int right=1; right <= s.length(); right++){          
            for(int left=0; left < right; left++){       
                if(sub[left] && set.contains(s.substring(left, right))){             
                    sub[right] = true;
                    break;             
                }
            }
        }
        return sub[s.length()];
        /*
           定一個布林陣列 sub 來存狀態，當 s 的索引值對應到 sub 裡為 True，代表從頭到該索引為止的字串都合格。
   
           雙指標 : 右指標遍歷 s ，每當右指移動，左指標便從頭開始縮減。
   
           當左指標索引值對應到 sub 裡為 True，且當前左右指截取的字串在字典裡，代表可延伸到右指索引的字串都合格，
   
           故將右指索引對應到 sub 裡設為 True，且由於再怎麼找都不會再影響右指的結果，故直接跳出節省效能。
            
           由於最一開始 sub 沒有任何 True 會導致永遠找不到答案，也不能一開始就確定第一個字合格，
            
           故 sub 最前面多加一個起始用的 True。
        */
    }
    // no36
    public boolean hasCycle(ListNode head) {

        ListNode slow = head, fast = head;        
        while(fast != null && fast.next != null){         
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;     
        }  
        return false;
    }
    // no37 (較差) 2 ms, faster than 36% 
    public ArrayList<Integer> reorderList_BAD(ListNode head) {

        ArrayList<ListNode> list = new ArrayList<ListNode>();    
        ListNode node = head;
            
        while(node != null){
            list.add(node);
            node = node.next;
        }
        
        int left = 0, right = list.size()-1;
        
        while(left < right){
            
            list.get(left).next = list.get(right);
            left++;
            
            if(left == right) break;
            
            list.get(right).next = list.get(left);
            right--;     
        }       
        list.get(left).next = null;
        
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ListNode start = list.get(0);
        while(start != null){
            ans.add(start.val);
            start = start.next;
        } 
        /*  雖然這裡用 Array 來存鏈結串列，但 Array 的順序並不等於鏈結串列的順序，
            Array 順序是 [1,2,3,4] ，但這裡存放的值是自定義的 ListNode 而非 int，是照記憶體來找 next 的，
            依照鏈結串列的順序為 [1,4,2,3] ，所以需搞清楚兩者差異。
        */   
        return ans;
    }
    // no37 (較好)  1 ms, faster than 99.87% 
    public ArrayList<Integer> reorderList(ListNode head) {

        // 1. 找出中點     
        ListNode slow = head, fast = head.next;       
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 2. 依中點切成兩段，後段反轉        
        ListNode second = slow.next;
        slow.next = null;
        
        ListNode prev = null; // prev 代表要幫新排序接的下一個值
        while (second != null) {
            ListNode temp = second.next; // 先用 temp 存第二個值，因為會改順序
            second.next = prev;          // 把當前節點改為接上新排序的下一個值
            prev = second;               // 先存待會要接的值，不然會遺失
            second = temp;               // 當前節點往後移，因為順序改了所以不能用 next 
        }
        second = prev;
        
        // 3. 兩段合併
        ListNode left = head;
        ListNode right = second;
        while(left != null && right != null){
            prev = left.next;
            left.next = right;
            right = right.next;
            left.next.next = prev;
            left = prev;
        }

        ArrayList<ListNode> list = new ArrayList<ListNode>();    
        ListNode node = head;
            
        while(node != null){
            list.add(node);
            node = node.next;
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();
        ListNode start = list.get(0);
        while(start != null){
            ans.add(start.val);
            start = start.next;
        } 
        return ans;
    }
    // no38 (待改進)
    public int maxProduct(int[] nums) {
        /*
           最大值代表乘了最多次且都負負得正(正數)
           最小值代表乘了最多次且沒負負得正(負數)
           ans 存目前為止的最大值(0 會產生斷點要重算)
           
           每次乘上負數時，最大與最小值便會互換，
           所以在乘上負數前將兩者交換就能解決問題。
           
           [1,2,0,9, -1,1,1,1,1,1,1,-2]
          max 2 2 9 | 0 1          | 18
          min 1 0 0 |-9 -9         | -2
          ans 2 2 9   9 9            18

        */
        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];
        
        for(int i=1; i < nums.length; i++){
            int val = nums[i];
            if(val < 0){ // 最大最小互換
                int temp = max ;
                max = min;
                min = temp;
            }
            
            max = Math.max(val, max * val);
            min = Math.min(val, min * val);
            ans = Math.max(ans, max);
        } 
        return ans;
    }
    // no39
    public int findMin(int[] nums) {

        if(nums.length == 1) return nums[0];
        
        int left = 0;
        int right=nums.length - 1;

        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[right]){
                // java 整數型態除法會無條件捨去所有小數，不 +1 的話在最右邊會變無限循環
                left = mid + 1; 
            }else{
                right = mid;
            }
        } 
        return nums[left];
    }
    // no42
    public int rob(int[] nums) {
        /*
           只有兩條路線(X) -> [3,1,1,3,1]
           
           DP: T:O(n), S:O(1) 
           
           1, 2, 3, ..., n-2, n-1, n
           
           假設 f(x) 為求得 x 內偷最多的公式
           
           對於 n 只有選與不選的可能性
           
           1.   選: f(n-2) + n (因為 n-1 以外的都可以選)
           
           2. 不選: f(n-1) (因為 n 以外的都可以選)
           
           綜合 1, 2 可得 f(n) = max( f(n-1), f(n-2)+ n )
           
           從左遍歷到右可求得 f(n)
           
           這題中每次計算 f(n) 時只需要前兩個值便可，故不需要整個 dp array 來做 memo，
           可節省空間。     
        */
        
        int curr = 0; // f(n-1)
        int prev = 0; // f(n-2)
        
        for(int i : nums){ // n       
            int temp = Math.max(curr, prev + i); // 求出 f(n)
            prev = curr; // f(n-2) -> f(n-1)
            curr = temp; // f(n-1) -> f(n)    
        }        
        return curr;        
    }
    // no43
    public int numIslands(char[][] grid) {

        /* 
           BFS : 將在樹上的操作轉為在表格上

           遍歷表格，每當遇到 1 時就跑一次 BFS 把相鄰的 1 都轉成 2，計算跑了幾次 BFS 就是答案
        
           1. 邊界條件: 遍歷二元樹時，當 root==null 代表到底要 return，
           
                        轉為表格時則為當前座標(x,y)超過表的範圍時 return
                    
           2. 尋找條件: 當前座標代表的值非陸地(1)則 return
           
           3. 避免重複: 若已通過 1、2，則將當前陸地(1)轉為「已找過(2)」                 
        */
        System.out.println("元素總量 "+grid.length * grid[0].length);
        int ans = 0;   
        int nums = 0;  
        for(int i=0; i < grid.length; i++){
            for(int j=0; j < grid[0].length; j++){
                if(grid[i][j]=='1'){ // char 型態一定要 '1'，不能用 1
                    nums += subNumIslands(grid, i, j);
                    ans++;
                }
                else{ nums++; }
            }
        }
        System.out.println("執行次數 "+nums);
        return ans;
    }
    public int subNumIslands(char[][] grid, int i, int j){
  
        if(grid.length-1 < i || i < 0 || grid[0].length-1 < j || j < 0) return 1;
        if(grid[i][j] != '1') return 1;
        
        grid[i][j] = '2';
        
        return  1 + subNumIslands(grid, i+1, j)
                  + subNumIslands(grid, i-1, j)
                  + subNumIslands(grid, i, j+1)
                  + subNumIslands(grid, i, j-1);
    }
    // no50
    public boolean containsDuplicate(int[] nums) {
        
        /* HashSet算法 5 ms 45 MB
        HashSet set = new HashSet<Integer>();
        
        for(int i=0; i<nums.length; i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;*/

        // 先排序再跑一遍 3ms 42 MB
        Arrays.sort(nums);
        for(int i=1; i<nums.length; i++) {
            if(nums[i] == nums[i-1]) return true;
        }
        return false;
    }
    // no55
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        /* 對題目特化的解法，定一個 26 空間的陣列，並用「目標字母-a」當索引
           a-z 相當於 1-26， ex: 'c'-'a' => 2, 'c'-'z' => -23
           所以輸入字串-'a'得到的值就會剛好介於 0~25 */

		int[] all = new int[26];
        
        // 3ms
		/*for (int i=0; i<s.length(); i++) {
			all[s.charAt(i)-'a']++;
			all[t.charAt(i)-'a']--;
		}*/
        
        // 2ms
        for(char c: s.toCharArray()){
             all[c-'a'] +=1;
        }   
        for(char c: t.toCharArray()){
             all[c-'a'] -=1;
        }   
        
		for (int i : all) {
			if (i != 0) return false;
		}
		return true;
    }
    // no59
    public int missingNumber(int[] nums) {
        // 因為都是不重複數且為連續序列，所以用相加應得的數減去實際加總數就能找到缺少的那個數字
        int max = nums.length;
        int cnt = 0;
        for(int i : nums){
            cnt += i;
        }
        return (max*(max+1)/2)-cnt;
    }


    // 斷行
    public static void whiteLine(){
        System.out.println("");
    }
    // 計算執行時間
    public static void runtime(long rs){
        long re = System.currentTimeMillis();
        System.out.println("執行時間 "+(re-rs)+ "ms");
        System.out.println("");
    } 
    // 當前時間(毫秒)
    public static long now(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args){//////////////////////////////////////////////////////////

        /*  直接 print 會變 [I@4926097b，
            在陣列中除了char型的可以直接輸出外，其它型別的陣列輸出都是記憶體地址
            [ 表示陣列，I 表示 int 型別，4926097b 是物件名。
            那一長串就是int[] 該物件的toString內容。  */

        // no1
        int[] no1 = {2,4,6464,415,987,45,141,0,879,55,19,65};
        int target = 186;
        long rs = now();  // runtime start
        System.out.println("no1 兩數相加為目標: " + Arrays.toString(twoSum(no1, target))); 
        runtime(rs);
   
        // no2
        // 要調用 public 宣告的方法時，必須先 new 其所在類別    
        LC object = new LC(); // 
        String no2 = "ajcnddicmdejxmskoonzbqrklrterdsmpqmdoismrfdsolskfjsxmzyel";
        // 用 new 完的新類別 object 來呼叫方法，而不能直接使用原類別 LC 
        rs = now(); 
        System.out.println("no2 最長不重複字串: " + object.lengthOfLongestSubstring(no2));
        runtime(rs);

        // no3
        String no3 = "ajcnddicmdejxmskoonzbqptyuuuytprklrterdsmpqmdoismrfdsolskfjsxmzyel";
        rs = now();
        System.out.println("no3 最長回文(鏡像字串): " + longestPalindrome(no3));
        runtime(rs);

        // no4
        int[] no4 = {5,9,95,52,21,1,0,51,0,2,56,4,1,3,4,5,8,9};
        rs = now();
        System.out.println("no4 最大裝水量: " + maxArea(no4));
        runtime(rs);

        // no5
        int[] no5 = {-9,-5,-3,-1,0,0,0,1,3,4,5,8,9,28,29,465,1200};
        rs = now();
        System.out.println("no5 三數相加為0: " + threeSum(no5));
        runtime(rs);

        // no7
        String no7 = "({[{({[(()()[]{})]})}]})({[{({})}]})({[{({})}]})({[{({})}]})({[{({})}]})";
        System.out.println("no7 檢查符號開合: " + isValid(no7));
        rs = now();
        runtime(rs);

        // no10
        int[] no10 = {4,5,6,7,0,1,2};
        rs = now();
        System.out.println("no10 搜尋目標結果(若無則返回-1): " + search(no10, 0));
        runtime(rs);

        // no11
        int[] no11 = {2,3,6,7};
        rs = now();
        System.out.println("no11 目標的所有可能組合為: " + combinationSum(no11, 7));
        runtime(rs);

        // no14
        int[] no14 = {-2,1,-3,4,-1,2,1,-5,4};
        rs = now();
        System.out.println("no14 最大子陣列總和為: " + maxSubArray(no14));
        runtime(rs);

        // no18
        int[][] no18 = {{1,5},{6,8},{9,15},{17,51},{53,76}};
        int[] newInterval = {4,10};
        System.out.print("no18 鏈結串列加入數組後合併: ");
        rs = now();
        int[][] array = insert(no18, newInterval); 
        for(int i=0; i < array.length; i++){
            System.out.print(Arrays.toString(array[i]));
        }
        whiteLine();
        runtime(rs);

        // no27
        String no27 = "[3,9,20,null,null,15,7]";
        TreeNode root = TreeNode.mkTree(no27);
        rs = now();
        System.out.println("no27 將樹以層切分: " + object.levelOrder(root));
        runtime(rs);

        // no32
        String no32 ="race a e car!!";
        rs = now();
        System.out.println("no32 是否為回文: " + object.isPalindrome(no32));
        runtime(rs);

        // no33
        int[] no33 = {11,12,13,14,1,2,3,4,5,6,7,8,9,10,1,2,0,9,-1,1,1,1,1,1,1,-2};
        rs = now();
        System.out.println("no33 最大連續數字長度: " + object.longestConsecutive(no33));
        runtime(rs);

        // no35
        String no35 = "phppjpppi";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("pi");
        wordDict.add("pp");
        wordDict.add("pj");
        wordDict.add("php");
        rs = now();
        System.out.println("no35 是否能只用字典拼出字串: " + object.wordBreak(no35, wordDict));
        runtime(rs);

        // no36
        rs = now();
        ListNode no36 = new ListNode(3);
        ListNode temp = no36;
        for(int i=0; i<10000000; i++){
            no36.next = new ListNode(i);
            if(i==5000){
                temp = no36;
            }
            if(i==9999999){
                no36.next = temp;
            }
            no36 = no36.next;
        }
        long re = now();  
        System.out.println("生成一千萬筆測資時間 "+(re-rs)+ "ms"); 
        rs = now();
        System.out.println("no36 是否為循環鏈結串列: " + object.hasCycle(no36));
        runtime(rs);

        // no37
        ListNode no37 = new ListNode(0);
        temp = no37;
        for(int i=1; i<15; i++){
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        rs = now();
        System.out.println("no37 重新排序節點(較差): " + object.reorderList_BAD(no37));
        runtime(rs);
        rs = now();
        System.out.println("no37 重新排序節點(較好): " + object.reorderList(no37));
        runtime(rs);

        // no38
        int[] no38 = {1,2,0,9,-1,1,1,1,1,1,1,-2};
        rs = now();
        System.out.println("no38 最大連續乘積: " + object.maxProduct(no38));
        runtime(rs);

        // no39
        int[] no39 = {11,12,13,14,1,2,3,4,5,6,7,8,9,10}; // Java int 的除法為到整數位的無條件捨去，不留小數。
        rs = now();
        System.out.println("no39 最小數(起點): " + object.findMin(no39));
        runtime(rs);

        // no42
        rs = now();  
        int[] no42 = new int [1000000];
        for(int i=0; i<1000000; i++){
            no42[i] = 1;
        }
        re = now();  
        System.out.println("生成一百萬筆測資時間: "+(re-rs)+ "ms"); 
        rs = now();
        System.out.println("no42 搶到最大金額可能數為: " + object.rob(no42));
        runtime(rs);

        // no43
        rs = now();
        char[][] no43 = {/*  執行次數 = 總元素量 + (陸地數*4) - 島嶼數
                             最差為全陸地 => M*N + M*N*4 - 1
                                         => 5(M*N) => O(M*N)         */
            {'1','1','1','1','1','1','0','0','1','1','1','0'},
            {'1','1','1','1','1','0','0','0','1','1','1','0'},
            {'1','1','1','1','0','0','1','1','1','1','1','1'},
            {'1','1','0','0','0','0','0','1','1','1','1','0'},
            {'1','0','0','0','0','0','0','0','1','1','1','0'}
        };
        System.out.println("no43 島嶼數量: " + object.numIslands(no43));
        runtime(rs);

        // no50
        rs = now();
        int[] no50 = {17556,2,48,46,120,0,5,484,784,45,254,11,66,46}; 
        System.out.println("no50 是否有包含重複數字: " + object.containsDuplicate(no50));
        runtime(rs);

        // no55
        rs = now();
        String no50S = "thecountryside";
        String no50T = "nocitydusthere";
        System.out.println("no55 是否為變位字: " + object.isAnagram(no50S, no50T));
        runtime(rs);

        // no59
        rs = now();
        int[] no59 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}; 
        System.out.println("no59 缺少的數字為: " + object.missingNumber(no59));
        runtime(rs);
    }
}