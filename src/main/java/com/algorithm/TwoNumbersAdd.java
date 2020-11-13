package com.algorithm;

/**
 * The type of code-snippet.
 *
 * <p>
 * .
 *
 * @author ganxiangyong
 */
public class TwoNumbersAdd {
    /**
     * 2. 两数相加
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */

    public static void main(String[] args) {
        TwoNumbersAdd obj = new TwoNumbersAdd();

        ListNode node = new ListNode(2);
        node.next = new ListNode(4);
        ListNode node2 = new ListNode(5);


    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 结果node的头
        ListNode head = null;
        // 指向当前结果list的最后一个元素
        ListNode tail = null;

        // 进位
        int carry = 0;

        while (l1 != null || l2 != null) {
            int val1 = 0;
            int val2 = 0;

            if (l1 != null) {
                val1 = l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                val2 = l2.val;
                l2 = l2.next;
            }

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
        }

        if (carry != 0) {
            tail.next = new ListNode(1);
        }

        // 第一个是空node，作为List头
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

