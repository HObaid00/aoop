public class Message{

    private String s;
    private Message next;

    public String getData(){
        return this.s;
    }

    public Message getNext(){
        return this.next;
    }

    public Message(String s){
        this.s = s;
    }

    public Message(String s, Message next){
        this.s = s;
        this.next = next;
    }
}