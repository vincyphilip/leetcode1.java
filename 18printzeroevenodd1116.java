import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private int count = 1;
    
    Semaphore zeroSem = new Semaphore(1);
    Semaphore evenSem = new Semaphore(0);
    Semaphore oddSem = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x"
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroSem.acquire(); // zero ku turn
            printNumber.accept(0);
            if (count % 2 == 0) {
                evenSem.release(); // even ku signal
            } else {
                oddSem.release(); // odd ku signal
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire(); // even ku turn varum varaikum wait
            printNumber.accept(i);
            count++;
            zeroSem.release(); // next zero ku signal
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire(); // odd ku turn varum varaikum wait
            printNumber.accept(i);
            count++;
            zeroSem.release(); // next zero ku signal
        }
    }
}