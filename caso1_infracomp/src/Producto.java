import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Producto {
    private final int id;
    private boolean ultimo;
    private CyclicBarrier barrier;

    public Producto(int id, boolean ultimo, CyclicBarrier barrier) {
        this.id = id;
        this.ultimo = ultimo;
        this.barrier = barrier;
    }

    public int getId() {
        return id;
    }

    public boolean isUltimo() {
        return ultimo;
    }

    public void despachar(){
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

