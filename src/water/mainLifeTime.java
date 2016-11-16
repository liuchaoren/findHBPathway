package water;
import java.io.*;
import java.util.*;

/**
 * Created by Chaoren on 11/14/16.
 */
public class mainLifeTime {

    public static void main(String[] args) throws IOException {

        String basepath = args[0];
        int snapshotNum = Integer.parseInt(args[1]);
        String sumname = args[2];
        String statname = args[3];

        pathLife workhorse = new pathLife();
        workhorse.statistics(basepath, snapshotNum, sumname, statname);

    }
}
