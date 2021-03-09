
import java.util.ArrayList;

public class Node implements Comparable<Node>{
    private final Integer num;
    private String color;
    public String getColor(){
        return color;
    }
    public void setColor(String c){
        color = c;
    }
    public Integer getNum(){
        return num;
    }
    private ArrayList<Node> edges;
    public Node(Integer num){
        this.num = num;
        edges = new ArrayList<Node>();
        this.color = "None";
    }
    public void addEdge(Node n){
        edges.add(n);
    }
    public ArrayList<Node> getEdges(){
        return edges;
    }
    public Integer getNumEdges(){
        return edges.size();
    }
    public String toString(){
        return String.valueOf(num);
    }

    @Override
    public int compareTo(Node o) {
        if(o.getNumEdges() < this.getNumEdges()){
            return -1;
        }else if(o.getNumEdges() == this.getNumEdges()){
            if(this.getNum() < o.getNum()){
                return -1;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
