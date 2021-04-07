package handler;

public class Handler {

    private Callback mCallback;
    final MessageQueue messageQueue;
    final Looper mLooper;

    public Handler() {
        this.mLooper = Looper.myLooper();
        this.messageQueue = mLooper.mQueue;
    }

    public void dispatchMessage(Message msg){
        if (msg.callback != null){
            handleCallback(msg);
        }else {
            if (mCallback != null){
                if (mCallback.handleMessage(msg)){
                    return;
                }
            }
            handleMessage(msg);
        }
    }

    private void handleCallback(Message msg) {
        msg.callback.run();
    }

    public void handleMessage(Message msg){

    }

    public void sendMessage(Message msg) {
        enqueueMessage(msg);
    }

    private void enqueueMessage(Message msg) {
        msg.target = this;
        messageQueue.enqueueMessage(msg);

    }

    public interface Callback{
        public boolean handleMessage(Message msg);
    }
}
