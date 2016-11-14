package water;
import java.io.*;
import java.util.*;

/**
 * Created by Chaoren on 11/14/16.
 */
public class mainLifeTime {

    public static void main(String[] args) throws IOException {

        formator myformat = new formator();
        String basepath = args[0];
        int snapshotNum = Integer.parseInt(args[1]);
        String sumname = args[2];
        String statname = args[3];

        int index=0;

        List<Integer> pre = null;
        int[] res = new int[snapshotNum+1];
        int[] waterCount = new int[snapshotNum+1];

        BufferedWriter summary = new BufferedWriter(new FileWriter(basepath+"/" +sumname));
        BufferedWriter stat = new BufferedWriter(new FileWriter(basepath+"/" +statname));
        mainLifeTime workhorse = new mainLifeTime();

        for (int i=1; i<=snapshotNum; i++) {
            String filename = basepath + "/" + String.format("pathway_%d.pdb",i);
            File f = new File(filename);
            if (f.isFile()) {
                List<Integer> oneresult = myformat.getPath(filename);
                if (workhorse.pathEqual(oneresult, pre)) {
                    res[i] = index;
                } else {
                    res[i] = ++index;
                }
                waterCount[i] = oneresult.size();
                pre = oneresult;
            } else {
                res[i] = -1;
                waterCount[i] = -1;
                pre = null;
            }
        }

        for (int i=1; i<=snapshotNum; i++) {
            summary.write(String.format("%d\t%d\n", res[i], waterCount[i]));
        }
        summary.close();

        HashMap<Integer, Integer> life = new HashMap<>();
        HashMap<Integer, Integer> waters = new HashMap<>();
        for (int i=1; i<=snapshotNum; i++) {
            if (res[i] != -1) {
                if (life.containsKey(res[i]))
                    life.put(res[i], life.get(res[i])+1);
                else
                    life.put(res[i], 1);

                waters.put(res[i], waterCount[i]);
            }
        }

        HashMap<Integer, List<Integer>> collectWater = new HashMap<>();
        for (int k: life.keySet()) {
            if (! collectWater.containsKey(waters.get(k)))
                collectWater.put(waters.get(k), new ArrayList<Integer>());
            collectWater.get(waters.get(k)).add(life.get(k));
        }

        List<Integer> watersort = new ArrayList(collectWater.keySet());
        Collections.sort(watersort);
        stat.write("# path_length\toccurence\tmean_lifetime\n");
        for (int k:watersort) {
            stat.write(String.format("%d\t%d\t%f\n", k, collectWater.get(k).size(), workhorse.averageLife(collectWater.get(k))));
        }
        stat.close();

    }


    public float averageLife(List<Integer> a) {
        float sum = (float) 0.;
        for (int i:a) {
            sum += i;
        }
        return sum/a.size()/2;
    }

    public boolean pathEqual(List<Integer> a, List<Integer> b) {
//        System.out.println("I am comparing one");
//        if (a!=null)
//            for (int i:a)
//                System.out.println(i);
//        if (b!=null)
//            for (int j:b)
//                System.out.println(j);

        if (a == null || b == null) {
//            System.out.println("jump out 1");
            return false;
        }
        if (a.size() != b.size()) {
//            System.out.println("jump out 2");
            return false;
        }
        for (int i=0; i<a.size(); i++) {
            if (a.get(i).intValue() != b.get(i).intValue()) {
//                System.out.println("jump out 3");
                return false;
            }
        }
//        System.out.print(true);
        return true;
    }
}
