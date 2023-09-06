import java.util.ArrayList;
import java.util.List;

public class App {

    static public List<Integer> bodega = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        for (int i= 0; i < 3; i++)
        {
            bodega.add(i);
        }

        Despachador despachador = new Despachador();
        Thread threadDespachador = new Thread(despachador);
        threadDespachador.start();

    }


    

}
