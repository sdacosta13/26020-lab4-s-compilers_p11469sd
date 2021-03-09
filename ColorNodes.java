
import java.io.File;

public class ColorNodes {

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Argument length incorrect");
        } else {
            File f1 = new File(args[0]);
            File f2 = new File(args[1]);
            Graph graph = new Graph();
            graph.PopulateGraph(f1);
            graph.ColorNodes();
            graph.writeColorToFile(f2);
        }

    }
}
