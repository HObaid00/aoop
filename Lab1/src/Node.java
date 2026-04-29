
public class Node {
    private int data;
    private Node next = null;

    public int getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public Node(int newData) {
        this.data = newData;
    }

    public Node(int newData, Node next) {
        this.data = newData;
        this.next = next;
    }
}
