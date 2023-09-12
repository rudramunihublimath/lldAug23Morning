package com.scaler.prodconssema;

import java.util.concurrent.Semaphore;

public class Producer implements Runnable {
    private Store store;
    private Semaphore producerSemaphore;
    private Semaphore consumerSemaphore;

    public Producer(Store store, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                producerSemaphore.acquire();
                Thread.sleep(10);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            store.addItem();
            consumerSemaphore.release();
        }
    }
}
