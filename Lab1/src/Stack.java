public class Stack {

    private Node topOfStack; // First node of Stack
    private int size;   // Size of the stack

    /**
     * Consturucter of the Stack
     */
    public Stack(){
        this.topOfStack = null;
        this.size = 0;
    }

    /**
     * Function
     * @return size of the Stack
     */
    public int getSize() {
        return size;
    }

    /**
     * Pushes the @param t to the top of the Stack
     *
     */
    public void push(int t) {
        topOfStack = new Node(t, topOfStack);
        size++;
    }

    /**
     * Pushes n elements to the top of the Stack from
     * the given array.
     * @param n the number of elements to push
     * @param t The given array to push
     */
    public void push(int n, int[] t){
        if(n > t.length) n = t.length;
        for(int i = n-1; i >=0 ; i--){
            push(t[i]);
        }
    }

    /**
     * Pops the first item in the stack.
     * @return the top of the stacks item
     * @throws Exception
     */
    public int pop() throws Exception {
        if(getSize() == 0){
            throw new Exception("Stack is empty");
        } 
        int data = topOfStack.getData();
        topOfStack = topOfStack.getNext();
        size--;
        return data;
    }

    /**
     * Function pops the amount of elements from the stack
     * @param n
     * @return
     * @throws Exception
     */
    public int[] pop(int n) throws Exception{
        if(n > getSize()) n = getSize();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = pop();
        }
        return arr;
    }

    /**
     * @return a copy of the stack
     */
    public Stack copy(){
        Stack stack = new Stack();
        stack.topOfStack = this.topOfStack;
        stack.size = getSize();
        return stack;
    }

    /**
     * @return the first Node in the Stack
     */
    public Node peek() {
        return this.topOfStack;
    }

    /**
     * @return Stack in Array for
     * @throws Exception
     */
    public int[] toArray() throws Exception{
        Stack stack = copy();
        int[] array = new int[getSize()];
        for(int i = array.length - 1; i > -1; i--){
            array[i] = stack.pop();
        }
        return array;
    }
}