import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class MessageQueueTest {

    private MessageQueue queue;
    private Message message1, message2, message3, message4;

    @Before
    public void setUp() throws Exception {
        queue = new MessageQueue(3);
        message1 = new Message("Hello");
        message2 = new Message("World");
        message3 = new Message("Java");
        message4 = new Message("Programming");
    }

    @Test(expected = AssertionError.class)
    public void testMessageQueueConstructorWithInvalidArgument() {
        new MessageQueue(-1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveOnEmptyQueue() {
        queue.remove();
    }

    @Test
    public void testAddAndRemove() {
        queue.add(message1);
        assertEquals(1, queue.size());

        queue.add(message2);
        assertEquals(2, queue.size());

        queue.add(message3);
        assertEquals(3, queue.size());

        assertEquals(message1, queue.remove());
        assertEquals(2, queue.size());

        assertEquals(message2, queue.remove());
        assertEquals(1, queue.size());

        queue.add(message4);
        assertEquals(2, queue.size());

        assertEquals(message3, queue.remove());
        assertEquals(1, queue.size());

        assertEquals(message4, queue.remove());
        assertEquals(0, queue.size());
    }

    @Test
    public void testGrowing() {
        queue.add(message1);
        queue.add(message2);
        queue.add(message3);
        queue.add(message4);
        assertEquals(4, queue.size());

        assertEquals(message1, queue.remove());
        assertEquals(3, queue.size());

        assertEquals(message2, queue.remove());
        assertEquals(2, queue.size());

        assertEquals(message3, queue.remove());
        assertEquals(1, queue.size());

        assertEquals(message4, queue.remove());
        assertEquals(0, queue.size());
    }

    @Test
    public void testPeek() {
        queue.add(message1);
        assertEquals(message1, queue.peek());

        queue.add(message2);
        assertEquals(message1, queue.peek());

        queue.remove();
        assertEquals(message2, queue.peek());
    }

    @Test
    public void testIsFull() {
        assertFalse(queue.isFull());
        queue.add(message1);
        queue.add(message2);
        queue.add(message3);
        assertTrue(queue.isFull());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.add(message1);
        assertFalse(queue.isEmpty());
        queue.remove();
        assertTrue(queue.isEmpty());
    }


    @Test
    public void testGetHead() {
        queue.add(message1);
        queue.add(message2);
        queue.add(message3);
        assertEquals(0, queue.getHead());
        queue.remove();
        assertEquals(1, queue.getHead());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(3, queue.getCapacity());
        queue.add(message1);
        assertEquals(3, queue.getCapacity());
        queue.add(message2);
        assertEquals(3, queue.getCapacity());
        queue.add(message3);
        assertEquals(3, queue.getCapacity());
        queue.add(message4);
        assertEquals(6, queue.getCapacity());
    }

}
