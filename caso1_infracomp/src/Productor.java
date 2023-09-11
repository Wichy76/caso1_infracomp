import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Productor extends Thread{
    private Bodega bodega; //este es el buffer
    private int n; //numero de productos a producir
    private static int p = 0; //productos producidos
    private int id; //identificador del productor


    /**
     * Constructor de la clase Productor
     * @param bodega
     * @param n
     * @param id
     */
    public Productor(Bodega bodega, int n, int id){
        this.bodega=bodega;
        this.n=n;
        this.id=id+1;
    }

    /**
     * Metodo que produce un producto y lo deposita en la bodega
     */
    public synchronized void producir(){
        p++;
        int p2 = p;
        CyclicBarrier espera = new CyclicBarrier(2);
        Producto producto = new Producto(p2, espera);
        System.out.println("Productor "+id+ " produjo: "+producto.getId());
        bodega.depositar(producto);
        try {
            espera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que ejecuta el hilo
     */
    @Override
    public void run() {
        while(n>p){
            producir();
        }
    }
}
