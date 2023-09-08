import java.util.ArrayList;

public class Productor extends Thread{
    Bodega bodega;
    int n;

    public Productor(Bodega bodega, int n){
        this.bodega=bodega;
        this.n=n;
    }

    @Override
    public void run() {
        bodega.depositar(1);
        System.out.println("Se produjó el producto " +  1);
    }
}
