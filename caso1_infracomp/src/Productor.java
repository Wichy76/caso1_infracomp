import java.util.ArrayList;

public class Productor extends Thread{
    Bodega bodega;
    int n;

    public Productor(Bodega bodega, int n){
        this.bodega=bodega;
        this.n=n;
    }

    @Override
    public void run() {

    }
}
