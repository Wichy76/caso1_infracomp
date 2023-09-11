import java.util.List;
import java.util.LinkedList;

public class Despacho{
    
    //Despacho de productos
    private List<Producto> despacho;

    //Capacidad del buffer
    private int size;
    
    private int productosRecibidos;

    private boolean despachoAbierto;

    public boolean isDespachoAbierto() {
        return despachoAbierto;
    }

    public void setDespachoAbierto(boolean despachoAbierto) {
        this.despachoAbierto = despachoAbierto;
    }

    public int getProductosRecibidos() {
        return productosRecibidos;
    }

    public void setProductosRecibidos(int productosRecibidos) {
        this.productosRecibidos = productosRecibidos;
    }



    public Despacho(int size){
        this.size = size;
        this.despacho = new LinkedList<Producto>();
        despachoAbierto =  true;
    }

    public synchronized boolean hasMessages(){
        return this.despacho.size()>0;
    }

    //Cuando el despachador tiene un producto, intenta pasarlo a un repartidor. 
    //El despachador no puede pasar un nuevo producto hasta tanto el anterior no haya sido recogido para reparto por algún repartidor. 
    //En ese caso el Despachador hace una espera pasiva.
    public synchronized void depositarProducto(Producto producto){
        while(this.despacho.size() == this.size){ //El producto anterior no ha sido recogido.
            System.out.println("El despacho esta lleno, el despachador espera");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.despacho.add(producto); //Se pasa el producto al repartidor
        System.out.println("El producto " + producto.getId() + " se guardó en el despacho");
        productosRecibidos+=1;
        notify(); //Notifica a los repartidores
    }

    //Los repartidores esperan que el despachador les entregue un producto para ir a entregarlo. 
    //Si el despachador no tiene productos para entregar, esperan de manera pasiva a que sea su turno. 
    public synchronized Producto retirarProducto(int id){
        while(this.despacho.size() == 0){ //El despachador no tiene productos para entregar,
            //System.out.println("El repartidor R"+ id +" espera a que sea su turno");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Producto producto = this.despacho.remove(0);
        System.out.println("El producto " + producto.getId() + " fue sacado del despacho por el repartidor " +id);
        notifyAll();

        return producto;
    }

    
}
