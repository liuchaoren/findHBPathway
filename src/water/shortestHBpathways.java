package water;
import java.util.*;

/**
 * Created by Chaoren on 11/11/16.
 */
public class shortestHBpathways {


    List<water> waterList;
    int n;
    List<ArrayList<Integer>> graph;
    int waterDonor, waterAcceptor;
    float[] a, b;

    public shortestHBpathways(List<water> waterList, float[] a, float[] b) {
        this.waterList = waterList;
        this.n = this.waterList.size();
        this.a = a;
        this.b = b;

        this.graph = new ArrayList<>();
        for (int i=0; i<n; i++) {
            this.graph.add(new ArrayList<Integer>());
        }

        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                if (hbconnected(waterList.get(i), waterList.get(j))) {
                    this.graph.get(i).add(j);
                    this.graph.get(j).add(i);
                }
            }
        }

        findDonorAcceptor();
        System.out.println(waterList.get(waterDonor));
        System.out.println(waterList.get(waterAcceptor));

    }

    public List<water> findShortestPath() {

        int[] color = new int[n];
        int[] parent = new int[n];
        int[] distance = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = -1;
            distance[i] = -1;
        }

        Queue<Integer> myqueue = new LinkedList<>();
        myqueue.add(waterDonor);
        distance[waterDonor] = 0;
        color[waterDonor] = 1;

        while (! myqueue.isEmpty()) {
            int onewater = myqueue.poll();
            ArrayList<Integer> neighbors = graph.get(onewater);

            for (int i : neighbors) {
                if (color[i] == 0) {
                    color[i] = 1;
                    myqueue.add(i);
                    parent[i] = onewater;
                    distance[i] = distance[onewater] + 1;
                }
            }
            color[onewater] = 2;
        }

        if (distance[waterAcceptor] == -1)
            return null;


        int index = waterAcceptor;
        List<water> res = new ArrayList<>();
        while (index != -1) {
            res.add(waterList.get(index));
            index = parent[index];
        }

        int i=0;
        int j=res.size()-1;
        while (j>i) {
            water tmp;
            tmp = res.get(i);
            res.set(i, res.get(j));
            res.set(j, tmp);
            i++;
            j--;
        }
        return res;

    }


    public boolean hbconnected(water water1, water water2) {
        float threshold=(float) 2.0;
        return distance(water1.O.pos, water2.H1.pos) < threshold || distance(water1.O.pos, water2.H2.pos) < threshold || distance(water2.O.pos, water1.H1.pos) < threshold || distance(water2.O.pos, water1.H2.pos) < threshold;
    }

    public void findDonorAcceptor() {
        waterDonor = 0;
        waterAcceptor = 0;
        float donorDis = distance(a, waterList.get(waterDonor).O.pos);
        float acceptorDis = distance(b, waterList.get(waterAcceptor).O.pos);
        float newdis;
        for (int i=1; i<n; i++) {
            if ((newdis = distance(waterList.get(i).O.pos, a)) < donorDis) {
                donorDis = newdis;
                waterDonor = i;
            }
            if ((newdis = distance(waterList.get(i).O.pos, b)) < acceptorDis) {
                acceptorDis = newdis;
                waterAcceptor = i;
            }
        }
    }

    public float distance (float[] a, float[] b) {
        float sum=(float) 0.;
        for (int i=0; i<a.length; i++) {
            sum += Math.pow(a[i]-b[i], 2);
        }
        return (float) Math.sqrt(sum);
    }

}
