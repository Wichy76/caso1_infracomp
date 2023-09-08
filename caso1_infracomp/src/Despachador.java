import java.util.ArrayList;
import java.util.Random;

public class Despachador extends Thread{

    //Despacho de productos
    private Despacho despacho;
    private Bodega bodega;

    public Despachador(Despacho despacho, Bodega bodega){
        this.despacho=despacho;
        this.bodega=bodega;
    }

    void metodoCualquiera(int n){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int randomNum = rand.nextInt(100);
            list.add(randomNum);
        }


    
    }

    @Override
    public void run() {

        int n = 0;
        
        while (true){
            if (this.bodega.isEmpty()){                    //Si no hay nada en bodega espera activa pero util
                System.out.println("Despachador está ejecutando un método cualquiera por ver número " + n);
                metodoCualquiera(n);
                n++;
            }
            else{
                int producto = this.bodega.retirar();     //Si hay algo intentamos pasar a un repartidor
                String producto2 =  String.valueOf(producto);
                this.despacho.depositarProducto(producto2);
            }
        }
    }
}


    


