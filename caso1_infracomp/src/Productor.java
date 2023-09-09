import java.util.ArrayList;

public class Productor extends Thread{
    private Bodega bodega; //este es el buffer
    private int n; //numero de productos a producir
    private static int p = 0; //productos producidos
    private int id; //identificador del productor

    public Productor(Bodega bodega, int n, int id){
        this.bodega=bodega;
        this.n=n;
        this.id=id;
    }

    public synchronized void producir(){
        
        p++;
        int p2= p;
        System.out.println("Productor "+id+ " produjo: "+p);
        bodega.depositar(p2);
    }

    @Override
    public void run() {
        while(n>p){
            producir();
        }
    }
}
