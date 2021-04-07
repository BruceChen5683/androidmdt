package handler;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        System.out.println("start");
        Looper.prepare();

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                System.out.println("Thread id "+Thread.currentThread().getId() + "received msg "+msg.toString());
            }
        };

//        new Thread(new Runnable() {
//            int count =  100;
//            @Override
//            public void run() {
//                while (count > 0){
//                    Message msg = new Message(UUID.randomUUID().toString());
//                    System.out.println("Thread id "+Thread.currentThread().getId() + "send msg "+msg.toString() + "  count "+count);
//                    handler.sendMessage(msg);
//
//                    try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    count--;
//                }
//            }
//        }).start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                int count =  10;
                @Override
                public void run() {
                    while (count > 0){
                        Message msg = new Message(UUID.randomUUID().toString());
                        System.out.println("Thread id "+Thread.currentThread().getId() + "send msg "+msg.toString() + "  count "+count);
                        handler.sendMessage(msg);

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count--;
                    }
                }
            }).start();
        }
        Looper.loop();

    }
}
