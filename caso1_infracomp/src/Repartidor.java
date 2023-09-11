public class Repartidor extends Thread{
    
    private static final int TIEMPO_MINIMO=3; 
    private static final int TIEMPO_MAXIMO=10; 

    //Identificacion del repartidor
    private int id;

    //Despacho de productos
    private Despacho despacho;

    public Repartidor(int id, Despacho despacho){
        this.id=id;
        this.despacho=despacho;
    }

    //El tiempo que demora un repartidor en entregar un producto es un valor aleatorio en 3 y 10 segundos. 
    public void esperaAleatoria(int tiempoMaximo, int tiempoMinimo, Producto producto) {
        System.out.println("El producto " + producto.getId() + " se encuentra en REPARTO");
        int espera = (int) (Math.random() * (tiempoMaximo-tiempoMinimo))+tiempoMinimo; 
        System.out.println("El repartidor R"+this.id+" entrega el producto " + producto.getId() + " en: " + espera + " segundos" );
        try {
                    Thread.sleep(espera*1000);
              } catch (InterruptedException e) {
                    e.printStackTrace();
        } 
    }

    public synchronized void entregarProducto(Producto producto){
        System.out.println("El producto " + producto.getId() + " fue ENTREGADO");
        producto.despachar();
    }
        

    public void run() {
        while(despacho.isDespachoAbierto()|| this.despacho.hasMessages()){
            Producto producto=this.despacho.retirarProducto(id);
            if(producto==null){
                return;
            }
            esperaAleatoria(TIEMPO_MAXIMO, TIEMPO_MINIMO, producto);
            entregarProducto(producto);  
        }
    } 
}
