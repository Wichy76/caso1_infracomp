import java.util.ArrayList;

public class Bodega {
    private ArrayList<Integer> buff;
    private int n;

    public Bodega(int n) {
        this.n = n;
        this.buff = new ArrayList<Integer>();
    }

    public synchronized void depositar(int producto) {
        while (buff.size() == n) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buff.add(producto);
        notifyAll();
    }

    public synchronized int retirar() {
        if (buff.isEmpty()) 
            return -1;
        
        int producto = buff.remove(0);
        notifyAll();
        return producto;
    }
}
