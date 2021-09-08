public class TreeNode {

    // 實現 二元樹
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
    /////////////////////////////

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public String toString(){
        return Integer.toString(val);
    }

//    int []arr = {3, 9, 20, Integer.MAX_VALUE, Integer.MAX_VALUE, 15, 7};
    private static int[] StrToIntArray(String str) {
        str = str.substring(1, str.length() - 1);
        String []strs = str.split(",");
        int []arr = new int[strs.length];

        for (int i = 0; i < arr.length; i++) {
            if (strs[i].equals("null")) {
                arr[i] = Integer.MAX_VALUE;
            } else {
                arr[i] = Integer.parseInt(strs[i]);
            }
        }

        return arr;
    }

//    String str = "[3,9,20,null,null,15,7]";
    public static TreeNode mkTree(String str) {

        int []arr = StrToIntArray(str);
        TreeNode []nodes = new TreeNode[arr.length + 1];
        for (int i = 1; i < nodes.length; i++) {
            if (arr[i - 1] != Integer.MAX_VALUE) {
                nodes[i] = new TreeNode(arr[i - 1]);
            }else {
                nodes[i] = null;
            }
        }

        TreeNode node = null;
        for (int i = 1; i < nodes.length / 2; i++) {
            node = nodes[i];
            if (node == null) continue;
            node.left = nodes[2 * i];
            node.right = nodes[2 * i + 1];
        }
        return nodes[1];
    }
}

/*
   二元樹是每個節點最多有兩個子樹的樹結構，子樹有左右之分，
   二元樹常被用於實現二元搜尋樹(binary search tree)和二元堆(binary heap)。

   @ Tree traversal 樹的遍歷
   
   從二元樹的根節點出發，節點的遍歷分為三個主要步驟：
    (1) 對當前節點進行操作（稱為「訪問」節點，或者根節點）、
    (2) 遍歷左邊子節點
    (3) 遍歷右邊子節點
   
    訪問節點順序的不同也就形成了不同的遍歷方式。需要注意的是樹的遍歷通常使用遞迴的方法進行理解和實現，
    在訪問元素時也需要使用遞迴的思想去理解。

    按照訪問根元素(當前元素)的前後順序，遍歷方式可劃分為如下幾種：

     (1) 深度優先(depth-first)：
         先訪問子節點，再訪問父節點，最後訪問第二個子節點。
         根據根節點相對於左右子節點的訪問先後順序又可細分為以下三種方式。

         1. 前序(pre-order)： 先根後左再右
         2. 中序(in-order)：  先左後根再右
         3. 後序(post-order)：先左後右再根

     (2) 廣度優先(breadth-first)：
         先訪問根節點，沿著樹的寬度遍歷子節點，直到所有節點均被訪問為止，又稱為層次(level-order)遍歷。

    二元樹的廣度優先遍歷和樹的前序/中序/後序遍歷不太一樣，前/中/後序遍歷使用遞迴，
    也就是用堆疊(stack)的思想對二元樹進行遍歷，廣度優先一般使用隊列(queue)的思想對二元樹進行遍歷。

    如果已知中序遍歷和前序遍歷或者後序遍歷，那麼就可以完全恢復出原二元樹結構。
    其中最爲關鍵的是前序遍歷中第一個一定是根，而後序遍歷最後一個一定是根，
    中序遍歷在得知根節點後又可進一步遞歸得知左右子樹的根節點。
    但是這種方法也是有適用範圍的：元素不能重複！否則無法完成定位。

    @ Binary Search Tree - 二元搜尋樹

    一顆二元搜尋樹(BST)是一顆二元樹，其中每個節點都含有一個可進行比較的鍵及相應的值，
    且每個節點的鍵都大於等於左子樹中的任意節點的鍵，而小於右子樹中的任意節點的鍵。

    使用中序遍歷可得到有序數列，這是二元搜尋樹的一個重要特徵。

    二元搜尋樹使用的每個節點含有兩個鏈接，它是將鏈表插入的靈活性和有序陣列查找的高效性結合起來的高效符號表實現。
*/

/*
   BFS:

   void traverse(TreeNode root) {
  
      if (root == null) { // 終止條件
          return;
      }

      traverse(root.left);
      traverse(root.right);
}
*/
