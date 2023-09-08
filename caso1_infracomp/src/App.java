import java.util.Scanner;

public class App {
	private static volatile boolean finished = false;

	public static boolean isFinished() {
		return finished;
	}

	public static void setFinished(boolean finished) {
		App.finished = finished;
	}
	
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Ingrese el numero de productores: ");
	int N = sc.nextInt();
	System.out.println("Ingrese el numero de repartidores: ");
	int M = sc.nextInt();
	System.out.println("Ingrese el tamaño de la bodega: ");
	int TAM = sc.nextInt();
	System.out.println("Ingrese el numero de productos a producir: ");
	int P = sc.nextInt();
	sc.close();

	Bodega bodega = new Bodega(TAM);
    Despacho despacho = new Despacho(1);
    Despachador despachador = new Despachador(despacho, bodega);

    despachador.start();

    for (int i= 0; i < M; i++)
    {
        Repartidor repartidor = new Repartidor(i, despacho);
        repartidor.start();
    }
		for (int i = 0; i < N; i++) {
			  Productor productor = new Productor(bodega, P, i);
			  productor.start();
		}
    }
}
