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

    private synchronized void entregarProducto(Producto producto){
        int espera = (int) (Math.random() * (TIEMPO_MAXIMO-TIEMPO_MINIMO))+TIEMPO_MINIMO; 
        try {
            Thread.sleep(espera*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El repartidor R"+this.id+" ENTREGA el producto " + producto.getId() + " en: " + espera + " segundos" );  
        producto.despachar();
    }
        

    public void run() {
        while(despacho.isDespachoAbierto()|| this.despacho.hasMessages()){
            Producto producto=this.despacho.retirarProducto(id);
            if(producto==null){
                return;
            }
            entregarProducto(producto);
        }
    } 
}
