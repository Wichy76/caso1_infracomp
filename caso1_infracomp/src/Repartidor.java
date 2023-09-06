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
    public void esperaAleatoria(int tiempoMaximo, int tiempoMinimo, String producto) {
        System.out.println("El producto: " + producto + " se encuentra en REPARTO");
        int espera = (int) (Math.random() * (tiempoMaximo-tiempoMinimo))+tiempoMinimo; 
        System.out.println("El repartidor R"+this.id+" entrega el producto en: " + espera + " segundos" );
        try {
                    Thread.sleep(espera*1000);
              } catch (InterruptedException e) {
                    e.printStackTrace();
        } 
    }
        

    public void run() {
        while(!App.isFinnished() || this.despacho.hasMessages()){
            String producto=this.despacho.retirarProducto();
            esperaAleatoria(TIEMPO_MAXIMO, TIEMPO_MINIMO, producto);
            System.out.println("El repartidor R"+this.id+" entrego el producto: " + producto);
            
            
        }

    } 
}
