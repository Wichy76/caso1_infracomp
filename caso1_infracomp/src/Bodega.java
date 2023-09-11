import java.util.ArrayList;

public class Bodega {
    private final ArrayList<Producto> buff;
    private final int n;

    public Bodega(int n) {
        this.n = n;
        this.buff = new ArrayList<Producto>();
    }

    public synchronized void depositar(Producto producto) {
        while (buff.size() == n) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buff.add(producto);
        System.out.println("Se depositó el producto " + producto.getId() + " en la bodega");
    }

    public synchronized Producto retirar() {
        if (buff.isEmpty())
            return null;
        Producto producto = buff.remove(0);
        System.out.println("Se retiró el producto " + producto.getId() + " de la bodega");
        notify();
        return producto;
    }

}