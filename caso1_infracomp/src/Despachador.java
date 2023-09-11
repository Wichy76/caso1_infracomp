
public class Despachador extends Thread{

    //Despacho de productos
    private final Despacho despacho;
    private final Bodega bodega;

    //private final int numProductores;

    private final int numProductos;


    public Despachador(Despacho despacho, Bodega bodega, int numProductos){
        this.despacho=despacho;
        this.bodega=bodega;
        this.numProductos = numProductos;
    }

    private void metodoCualquiera(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        boolean continuar = true;

        while (continuar) {
            Producto producto = this.bodega.retirar();

            if (producto == null) {
                // Si no hay nada en bodega, espera activa pero útil
                metodoCualquiera();
            } else {
                // Si hay algo, intentamos pasar a un repartidor
                this.despacho.depositarProducto(producto);

                if (despacho.getProductosRecibidos() == numProductos) {
                    // Si el número de productos despachados es igual al total de productos, terminamos
                    despacho.setDespachoAbierto(false);
                    continuar = false; // Aquí controlamos la condición de salida sin usar break
                }
            }
        }
    }
}


    


