import java.util.concurrent.Semaphore;

class Foo {

    private Semaphore s1;
    private Semaphore s2;

    public Foo() {
        s1 = new Semaphore(0);
        s2 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first".
        printFirst.run();
        s1.release();   // Allow second() to execute
    }

    public void second(Runnable printSecond) throws InterruptedException {
        s1.acquire();   // Wait until first() finishes
        printSecond.run();
        s2.release();   // Allow third() to execute
    }

    public void third(Runnable printThird) throws InterruptedException {
        s2.acquire();   // Wait until second() finishes
        printThird.run();
    }
}