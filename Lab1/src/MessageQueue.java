import java.util.NoSuchElementException;

/**
 A first-in, first-out bounded collection of messages.
 */
public class MessageQueue {
    /**
     * Constructs an empty message queue.
     *
     * @param capacity the maximum capacity of the queue
     * @precondition capacity > 0
     */
    public MessageQueue(int capacity) {
        assert capacity > 0;
        elements = new Message[capacity];
        count = 0;
        head = 0;
        tail = -1;
    }

    /**
     * Remove message at head.
     *
     * @return the message that has been removed from the queue
     * @precondition size() > 0
     */
    public Message remove() {
        if (isEmpty()){
            throw new NoSuchElementException("Stop, Nothing to dequeue");
        }
        Message r = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        count--;
        return r;
    }

    /**
     * Append a message at tail.
     *
     * @param aMessage the message to be appended
     * @precondition !isFull();
     */
    public void add(Message aMessage) {
        if (isFull()){
            /*growing the elements array when the queue is full.*/
            Growing();
        }

        if((tail == elements.length) && !isFull()){
            int calculate = 0;
            Message[] empty = new Message[elements.length];
            for(int i = 0; i < elements.length ; i++){
                if (elements[i % elements.length] != null){
                    empty[calculate] = elements[i % elements.length];
                    if (i == head ){
                        head = calculate;
                    }
                    calculate++;
                }
            }
            elements = empty;
            tail = calculate;
        }

        tail = (tail + 1) % (elements.length);
        elements[tail] = aMessage;
        count++;
    }

    private void Growing(){
        int capacity = elements.length * 2;
        Message[] empty = new Message[capacity];
        for(int i = 0; i < elements.length ; i++){
            empty[i] = elements[i % elements.length];
        }
        elements = empty;
    }


    /**
     * Get the total number of messages in the queue.
     *
     * @return the total number of messages in the queue
     */
    public int size(){
        return count;
    }

    /**
     * Checks whether this queue is full
     *
     * @return true if the queue is full
     */
    public boolean isFull() {
        return count == elements.length;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public int getTail() {
        return tail;
    }

    public int getHead() {
        return head;
    }

    public int getCapacity() {
        return elements.length;
    }

    /**
     * Get message at head.
     *
     * @return the message that is at the head of the queue
     * @precondition size() > 0
     */
    public Message peek() {
        return elements[head];
    }

    private Message[] elements;
    private int head;
    private int tail;
    private int count;

}

