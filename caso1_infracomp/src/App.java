import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        //int m=pedirRepartidores();

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
	public static synchronized int pedirProductores() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("Ingrese la cantidad de Productores: ");
			String numeroString = null;
			try {
				numeroString = br.readLine();
			} catch (IOException e) {

				e.printStackTrace();
			}
			int cantidadProductores = Integer.parseInt(numeroString);
			return cantidadProductores;
		}
	}

    public static synchronized int pedirProductos() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("Ingrese la cantidad de Productos: ");
			String numeroString = null;
			try {
				numeroString = br.readLine();
			} catch (IOException e) {

				e.printStackTrace();
			}
			int cantidadProductos = Integer.parseInt(numeroString);
			return cantidadProductos;
		}
	}

	public static synchronized int pedirRepartidores() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("Ingrese la cantidad de Repartidores: ");
			String numeroString = null;
			try {
				numeroString = br.readLine();
			} catch (IOException e) {

				e.printStackTrace();
			}
			int cantidadRepartidores = Integer.parseInt(numeroString);
			return cantidadRepartidores;
		}
	}
    

}
