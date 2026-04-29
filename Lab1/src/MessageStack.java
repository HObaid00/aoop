

public class MessageStack{
    private Message topOfStack;
    private int size;

    public MessageStack(){
        this.topOfStack = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void push(String T) {
        topOfStack = new Message(T, topOfStack);
        size++;
    }
    
    public void push(int n, String[] T){
        if(n > T.length) n = T.length;
        for(int i = n-1; i >= 0; i--)
            push(T[i]);
    }

    public String pop() {
        String data = topOfStack.getData();
        topOfStack = topOfStack.getNext();
        size--;
        return data;
    }

    public String[] pop(int n) throws Exception{
        if(n > getSize()) n = getSize();
        String[] arr = new String[n];
        for(int i = 0; i < n; i++){
            arr[i] = pop();
        }
        return arr;
    }


    public MessageStack copy(){
        MessageStack stack = new MessageStack();
        stack.topOfStack = this.topOfStack;
        stack.size = getSize();
        return stack;
    }

    public Message peek() {
        return this.topOfStack;
    }

    public String[] toArray(){
        MessageStack stack = copy();
        String[] array = new String[stack.getSize()];
        for(int i = array.length - 1; i > -1; i--){
            array[i] = pop();
        }
        return array;
    }
}