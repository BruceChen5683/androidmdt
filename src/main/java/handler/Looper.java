package handler;

public class Looper {
    private  static Looper sMainLooper;
    final MessageQueue mQueue;
    final Thread mThread;
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    
    private Looper(){
        mQueue = new MessageQueue();
        mThread = Thread.currentThread();
    }

    public static void prepare(){
        if (sThreadLocal.get() != null){
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static void loop(){
        final Looper looper = myLooper();
        final MessageQueue queue = looper.mQueue;
        for (;;){
            Message msg = queue.next();
            if (msg != null){
                msg.target.dispatchMessage(msg);
            }
        }
    }

    public static void prepareMainLooper(){
        prepare();
        synchronized (Looper.class){
            if (sMainLooper != null){
                throw new IllegalStateException("The main Looper has already been prepared.");
            }
            sMainLooper = myLooper();
        }
    }
    
    public static MessageQueue myQueue(){
        return myLooper().mQueue;
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static Looper getMainLooper(){
        synchronized (Looper.class){
            return sMainLooper;
        }
    }
}
