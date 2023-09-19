package rockpaperscissors;

public class CircularList<T> {
    private Node<T> head;  // Reference to the first node in the list
    public int size = 0;

    // Constructor to initialize an empty circular linked list
    public CircularList() {
        head = null;
    }

    // Method to add a node to the end of the circular linked list
    public void append(T data) {
        (this.size)++;
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            Node<T> current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head;
        }

    }

    public Node<T> getHead() {
        return head;
    }


}

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
