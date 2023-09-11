import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Producto {
    private final int id; //identificador del producto
    private CyclicBarrier barrier; //barrera para sincronizar los hilos

    /**
     * Constructor de la clase Producto
     * @param id
     * @param barrier
     */
    public Producto(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    /**
     * Metodo que devuelve el identificador del producto
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo que registra el producto como enviado y llena la barrera
     */
    public void despachar(){
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

