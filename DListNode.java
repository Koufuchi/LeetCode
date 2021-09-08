// 雙向鏈結串列
public class DListNode {
    int val;
    DListNode prev, next;
    DListNode(int val) {
        this.val = val;
        this.prev = this.next = null;
    }
}

/*
和單向鏈表的區別在於：雙向鏈表的反轉核心在於next和prev域的交換，還需要注意的是目前節點和上一個節點的遞推。

public DListNode reverse(DListNode head) {
    DListNode curr = null;
    while (head != null) {
        curr = head;
        head = curr.next;
        curr.next = curr.prev;
        curr.prev = head;
    }
    return curr;
}
*/