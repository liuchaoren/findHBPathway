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
        String outputname = args[2];

        int index=0;

        List<Integer> pre = null;
        int[] res = new int[snapshotNum+1];
        BufferedWriter output = new BufferedWriter(new FileWriter(basepath+"/" + outputname));

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
                pre = oneresult;
            } else {
                res[i] = -1;
                pre = null;
            }
        }

        for (int i=1; i<=snapshotNum; i++) {
            output.write(String.format("%d\n", res[i]));
        }
        output.close();
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
