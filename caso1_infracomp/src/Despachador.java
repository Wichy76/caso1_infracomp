
public class Despachador extends Thread{

    //Despacho de productos
    private final Despacho despacho;
    private final Bodega bodega;

    private final int numProductores;

    private final int numProductos;


    public Despachador(Despacho despacho, Bodega bodega, int numProductos, int numProductores){
        this.despacho=despacho;
        this.bodega=bodega;
        this.numProductores = numProductores;
        this.numProductos = numProductos;
    }

    void metodoCualquiera(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void run() {

        int n = 0;
        
        while (true){
            Producto producto = this.bodega.retirar();

            if (producto == null){
                    //Si no hay nada en bodega espera activa pero util
                metodoCualquiera();
                n++;
            } else {
                //Si hay algo intentamos pasar a un repartidor
                this.despacho.depositarProducto(producto);

                if(despacho.getProductosRecibidos() == numProductos*numProductores){
                    //Si el número de productos despachados es igual al total de productos terminamos

                    despacho.setDespachoAbierto(false);

                    System.out.println("Despachador ha acabado");
                    break;
                }
            }
        }

    }
}


    


