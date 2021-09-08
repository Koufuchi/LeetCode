public class queue {
    
}

/*
   Queue 是一個 FIFO（First-in First-out, 先進先出）的資料結構，併發(concurrent)中經常使用，
   可以安全地將對象從一個任務傳給另一個任務。

   Queue 在 Java 中是 Interface, 一種實現方式是使用 LinkedList 向上轉型為 Queue,
   Queue 通常不能存儲 null 元素，否則會與 poll() 等方法的返回值混淆。

    ex. 
        Queue<Integer> queue = new LinkedList<Integer>();
        int queueLen = queue.size(); 

    Methods:
    0:0	      Throws exception	Returns special value
    Insert	      add(e)	          offer(e)
    Remove	      remove()	          poll()
    Examine	      element()	          peek()

    優先考慮右側方法，右側元素不存在時返回 null. 判斷非空時使用 isEmpty()方法，繼承自 Collection.

----@ Priority Queue - 優先隊列

    應用程式常常需要處理帶有優先級的業務，優先級最高的業務首先得到服務。因此產生了優先隊列這種資料結構。
    優先隊列中的每個元素都有各自的優先級，優先級最高的元素最先得到服務；優先級相同的元素按照其在優先隊列中的順序得到服務。

    優先隊列可以使用陣列或鏈表實現，從時間和空間複雜度來說，往往用二叉堆(Binary heap)來實現。

    Java 中提供 PriorityQueue 類，該類是 Interface Queue 的另外一種實現，
    和 LinkedList 的區別主要在於排序行為而不是性能，基於 priority heap 實現，非 synchronized，
    故多執行緒(Multi-thread)下應使用 PriorityBlockingQueue. 預設為自然序（小根堆），
    需要其他排序方式可自行實現Comparator接口，選用合適的構造器初始化。
    使用叠代器遍歷時不保證有序，有序訪問時需要使用Arrays.sort(pq.toArray()).

    不同方法的時間複雜度：

        (1) enqueuing and dequeuing: offer, poll, remove(), add       - O(log n)
        (2) Object:                  remove(Object), contains(Object) - O(n)
        (3) retrieval:               peek, element, size              - O(1)

----@ Deque - 雙端隊列

      雙端隊列（deque，全名double-ended queue）可以讓你在任何一端添加或者移除元素，
      因此它是一種具有隊列和堆疊性質的資料結構。

      Java 在 1.6 之後提供了 Deque 介面，可用兩種方法實現:
      
        (1) ArrayDeque（陣列）實現: 一個數組外加首尾索引。
        (2) LinkedList（鏈表）實現: 雙向鏈表。

        ex.
            Deque<Integer> deque = new ArrayDeque<Integer>();

        Methods:
                     First Element (Head)	          |     Last Element (Tail)
                Throws exception	Special value	  | Throws exception	  Special value
        Insert 	  `addFirst(e)`	      `offerFirst(e)` |  `addLast(e)`	      `offerLast(e)`
        Remove	  `removeFirst()`	  `pollFirst()`	  |  `removeLast()`	      `pollLast()`
        Examine	  `getFirst()`	      `peekFirst()`	  |  `getLast()`	      `peekLast()`

        其中 offerLast 和 Queue 中的 offer 功能相同，都是從尾部插入。
*/  