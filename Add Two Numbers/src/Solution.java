/*

You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

*/


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Pointer = l1;
        ListNode l2Pointer = l2;
        
        ListNode sumHead = new ListNode((l1Pointer.val + l2Pointer.val) % 10);
        
        ListNode sumPointer = sumHead;
        
        int carryover = (l1Pointer.val + l2Pointer.val) / 10;
        
        l1Pointer = l1.next;
        l2Pointer = l2.next;
        
        while(l1Pointer != null || l2Pointer != null){
            int x = (l1Pointer != null) ? l1Pointer.val : 0;
            int y = (l2Pointer != null) ? l2Pointer.val : 0;
            
            int sum = x + y + carryover;
            
            carryover = sum / 10;
            
            sumPointer.next = new ListNode(sum % 10);
            sumPointer = sumPointer.next;
            
            if(l1Pointer != null) l1Pointer = l1Pointer.next;
            if(l2Pointer != null) l2Pointer = l2Pointer.next;
        }
        
        if(carryover > 0){
            sumPointer.next = new ListNode(carryover);
        }
        
        return sumHead;
    }
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
