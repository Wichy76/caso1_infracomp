import java.util.ArrayList;
import java.util.Random;

public class Despachador implements Runnable{
    

    void metodoCualquiera(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int randomNum = rand.nextInt(100);
            list.add(randomNum);
        }

        System.out.println("Despachador está ejecutando un método cualquiera.");
    
    }

    @Override
    public void run() {
        
        int n = 5;
        
        while (true){
            if (App.bodega.isEmpty() && n > 0){
                metodoCualquiera();
                n --;
            }
            else if (n >0){
                int producto = App.bodega.get(0);
                App.bodega.remove(0);
                System.out.println("entregamos producto " +  String.valueOf(producto) );  
            }
            else {break;}
            }
        }
    }


    


