import java.util.ArrayList;
import java.util.List;

public class App {

    static public List<Integer> bodega = new ArrayList<Integer>();
    private static volatile boolean finished = false;

    public static boolean isFinished() {
        return finished;
    }

    public static void setFinished(boolean finished) {
        App.finished = finished;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        for (int i= 0; i < 3; i++)
        {
            bodega.add(i);
        }

        Despacho despacho=new Despacho(1);
        Despachador despachador = new Despachador(despacho);
        Thread threadDespachador = new Thread(despachador);

        threadDespachador.start();
        int m=3;
        for (int i= 0; i < m; i++)
        {
            Repartidor repartidor = new Repartidor(i, despacho);
            repartidor.start();
        }


        setFinished(true);

    }


    

}
