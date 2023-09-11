import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Productor extends Thread{
    private Bodega bodega; //este es el buffer
    private int n; //numero de productos a producir
    private static int p = 0; //productos producidos
    private int id; //identificador del productor


    public Productor(Bodega bodega, int n, int id, Despachador despachador){
        this.bodega=bodega;
        this.n=n;
        this.id=id+1;
    }

    public synchronized void producir(){
        p++;
        int p2 = p;
        Producto producto;
        CyclicBarrier espera = new CyclicBarrier(2);
        if(p2==n)
            producto= new Producto(p2, true, espera);
        else
            producto= new Producto(p2, true, espera);
       
        System.out.println("Productor "+id+ " produjo: "+producto.getId());
        bodega.depositar(producto);
        try {
            espera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(n>p){
            producir();
        }
    }
}
