import java.util.*;
public class dijkstraAlgo {
    static String cities[];
    
    static class Edge{
        String src;
        String dest;
        int wt;
    
        Edge(String s , String d , int w){
            src = s;
            dest = d;
            wt = w;
        }
    }

    public static int getCityNo(String in){
        for(int i = 0; i < cities.length; i++){
            if(cities[i].equals(in)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("<-ENTER THE NO OF CITIES->");
        int vtces = Integer.parseInt(sc.nextLine());

        cities = new String[vtces];

        ArrayList<Edge> graph[] = new ArrayList[vtces];
        for(int i = 0; i < vtces; i++){
            graph[i] = new ArrayList<>();
        }

        System.out.println("<-ENTER THE CITIES' NAMES->");
        for(int i = 0; i < vtces; i++){
            cities[i] = sc.nextLine();
        }

        System.out.println("<-ENTER THE NO OF EDGES IN THE GRAPH->");
        int edges = Integer.parseInt(sc.nextLine());

        System.out.println("<-ENTER THE SOURCE , DESTINATION AND DISTANCE BETWEEN THEM (" + edges + ")->");
        for(int i = 0; i < edges; i++){

            String src =  sc.nextLine();
            int v1 = getCityNo(src);
            
            String dest = sc.nextLine();
            int v2 = getCityNo(dest);

            int distance = Integer.parseInt(sc.nextLine());

            graph[v1].add(new Edge(src , dest , distance));
            graph[v2].add(new Edge(dest , src , distance));
        }

        //DIJKSTRA'S ALGORITHM
        System.out.println("\n<==ENTER THE SOURCE CITY==>");
        String src = sc.nextLine();
        System.out.println("<==ENTER THE DESTINATION CITY==>");
        String dest = sc.nextLine();

        PriorityQueue<element> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[vtces];

        pq.add(new element(src , src + "->" , 0));

        while(pq.size() > 0){
            element rem = pq.remove();

            int n = getCityNo(rem.v);

            if(visited[n] == true){
                continue;
            }

            visited[n] = true;

            if((rem.v).equals(dest)){
                System.out.println("\n--------------SHORTEST PATH----------------");
                System.out.println(rem.v + " via " + rem.psf + " @ " + rem.wsf + "km");
                System.out.println("-------------------------------------------");
                break;
            }

            
            for(Edge e : graph[n]){
                int cNo = getCityNo(e.dest);
                if(visited[cNo] == false){
                    pq.add(new element(e.dest, rem.psf + e.dest + "->" , rem.wsf + e.wt));
                }
            }

        }

        sc.close();
    }

    static class element implements Comparable<element>{
        String v;
        String psf;
        int wsf;

        element(String v , String p , int w){
            this.v = v;
            psf = p;
            wsf = w;
        }

        public int compareTo(element o){
            return this.wsf - o.wsf;
        }



    }
    
}
