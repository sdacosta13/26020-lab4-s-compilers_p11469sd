

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Graph {
    ArrayList<Node> nodes;
    public Graph(){
        nodes = new ArrayList<Node>();
    }
    public void PopulateGraph(File file) {
        nodes = new ArrayList<Node>();
        ArrayList<ArrayList<Integer>> parsableLines = new ArrayList<ArrayList<Integer>>();
        ArrayList<String> nodesSeen = new ArrayList<String>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                ArrayList<Integer> ints = new ArrayList<Integer>();
                for(String x : Arrays.asList(line.split(" "))){
                    ints.add(Integer.parseInt(x));
                }
                parsableLines.add(ints);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        //Create all nodes
        for(ArrayList<Integer> arr: parsableLines){
            addNode(new Node(arr.get(0)));
        }
        //Create all edges between nodes
        for(int i =0; i < parsableLines.size(); i++){
            Node nodeToAddTo = this.getNodeWith(parsableLines.get(i).get(0));
            for(int j = 1; j < parsableLines.get(i).size();j++){
                nodeToAddTo.addEdge(this.getNodeWith(parsableLines.get(i).get(j)));
            }
        }


    }
    public void addNode(Node node){
        nodes.add(node);
    }
    public Node getNodeWith(Integer i){
        for(Node node : nodes){
            if(node.getNum().equals(i)){
                return node;
            }
        }
        return null;
    }
    public String toString(){
        //Not Perfect but not worth the effort to fix
        //The data printed is descriptive enough
        String str = "";
        for(Node node : nodes){
            str += node.toString();
            str += ": ";
            for(Node i : node.getEdges()){
                str += i.toString();
                str += ", ";
            }
            str += "\n";
        }
        return str;
    }
    public String toColourString(){
        //Not Perfect but not worth the effort to fix
        //The data printed is descriptive enough
        String str = "";
        for(int i = 1; i < nodes.size()+1; i++){
            for(Node node : nodes){
                if(node.getNum().equals(i)){
                    str += node.toString();
                    str += node.getColor();
                    str += "\n";
                }
            }
        }
        return str;
    }
    public void writeColorToFile(File path){
        String data = this.toColourString();
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(path));
            out.print(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Node> getNodeOrderByDegree(){
        ArrayList<Node> temp = nodes;
        Collections.sort(temp);
        return temp;
    }
    public void Order(){
        // Used to check my ordering function is working
        Collections.sort(nodes);
    }
    public void ColorNodes(){
        ArrayList<Node> ordered = this.getNodeOrderByDegree();
        char[] colors = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        for(Node n : ordered){
            ArrayList<String> colorInUse = new ArrayList<String>();
            for(Node edge : n.getEdges()){
                colorInUse.add(edge.getColor());
            }
            for(char c : colors){
                String strC = String.valueOf(c);
                if(!colorInUse.contains(strC)){
                    n.setColor(strC);
                    break;
                }
            }
            if(n.getColor().equals("None")){
                System.out.println("Too Many Nodes, Output untrustworthy");
            }
        }
    }
    public void print(){
        System.out.print(this.toString());
    }
}
