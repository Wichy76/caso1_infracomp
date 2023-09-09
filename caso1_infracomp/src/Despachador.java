public class Despachador extends Thread{

    //Despacho de productos
    private final Despacho despacho;
    private final Bodega bodega;
    private int p;

    public Despachador(Despacho despacho, Bodega bodega, int p){
        this.despacho=despacho;
        this.bodega=bodega;
        this.p = p;
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
            int producto = this.bodega.retirar();

            if (producto == -1){
                    //Si no hay nada en bodega espera activa pero util
                System.out.println("Despachador está ejecutando un método cualquiera por ver número " + n);
                metodoCualquiera();
                n++;
            }
            else{
                     //Si hay algo intentamos pasar a un repartidor
                String producto2 =  String.valueOf(producto);
                this.despacho.depositarProducto(producto2);
                if(despacho.getProductosRecibidos() == p){
                    despacho.setDespachoAbierto(false);
                }
            }
        }
    }
}


    


