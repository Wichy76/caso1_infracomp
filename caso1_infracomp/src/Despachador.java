import java.util.ArrayList;


public class Despachador extends Thread{

    //Despacho de productos
    private final Despacho despacho;
    private final Bodega bodega;

    private final ArrayList<Productor> productores = new ArrayList<Productor>();

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

    public void addProductor(Productor productor) {
        productores.add(productor);
    }

    public boolean hayProductores(){
        boolean hay = false;
        for (Productor productor: productores){
            if (productor.isAlive()){
                hay = true;
            }
        }
        return  hay;
    }

    @Override
    public void run() {

        int n = 0;
        
        while (hayProductores()){
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
        System.out.println("Despachador ha acabado");
    }
}


    


