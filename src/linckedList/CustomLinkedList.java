package linckedList;

import java.util.ArrayList;
import java.util.List;

public class CustomLinkedList<T>{

    private int size =0;

    private Node<T> head;
    private Node<T> tail;

    public void linkedListAdd(Node <T> node) {
        if (head == null) {
            head = node;
            tail = head;
        } else if (head == tail){
            head.next = node;
            node.prev = head;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }
    public void removeNode (Node<T> node) {
        if(node != null){
            if (node.prev == null && node.next == null) {
                head = tail = null;
            } else if (node.next == null) {
                node.prev.next = null;
                tail = node.prev;
            } else if (node.prev == null) {
                node.next.prev = null;
                head = node.next;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }
    }

    public List<T> getTasks(){
        List<T> returningList = new ArrayList<>();
        Node<T> currentNode = head;
        while(currentNode != null){
            returningList.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return returningList;
    }
}