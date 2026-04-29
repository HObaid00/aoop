
import static org.junit.Assert.assertEquals;
import org.junit.*;
public class TestStack {
    private Stack stack;

    @Before
    public void init(){
        stack = new Stack();
    }

    @Test
    public void testPushPeek(){
        for(int i = 0; i < 5; i++){
            stack.push(i);
        }
        assertEquals(4, (int)stack.peek().getData());
    }

    @Test
    public void testPushAndPop() throws Exception{
        for(int i = 0; i < 3; i++){
            stack.push(i);
        }
        for(int i = 2; i > -1; i--){
            assertEquals(i, (int)stack.pop());
        }
    }

    @Test
    public void testPushAndPopArray() throws Exception{
        int arr[] = {0, 1, 2, 3, 4};
        stack.push(5, arr);
        int recv[] = stack.pop(arr.length);
        for(int i = 0; i < recv.length; i++){
            assertEquals( i, recv[i]);
        }

    }

    @Test
    public void testPushFailure() throws Exception{
        int arr[] = {0, 1, 2, 3};
        stack.push(50, arr);
    }

    @Test
    public void testPopFailure() throws Exception{
        int arr[] = {0, 1, 2, 3};
        stack.push(4, arr);
        arr = stack.pop(6);
    }


    @Test
    public void testToArray() throws Exception{
        for(int i = 0; i < 5; i++){
            stack.push(i);
        }
        int[] array = stack.toArray();
        for(int i = 0; i < 5; i++){
            assertEquals(i, array[i]);
        }
    }

}
