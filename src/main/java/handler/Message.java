package handler;
public class Message {
    public Runnable callback;

    String obj;
    Handler target;

    public Message(String obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Message{" +
                "obj='" + obj + '\'' +
                '}';
    }
}
