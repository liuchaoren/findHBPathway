package water;

import java.io.*;
import java.util.*;

public class mainPathFinder{


    public static void main(String[] args) throws IOException {
	// write your code here

        String input = args[0];
        Scanner myinput = new Scanner(new FileReader(input));
        String pdbfilename = myinput.nextLine();
        String pathwayfilename = myinput.nextLine();

        float[] donor = new float[] {myinput.nextFloat(), myinput.nextFloat(), myinput.nextFloat()};
        float[] acceptor = new float[] {myinput.nextFloat(), myinput.nextFloat(), myinput.nextFloat()};

        formator myformat = new formator();
        List<water> waterList = myformat.waterFormater(pdbfilename);
//        for (water onewater:waterList) {
//            System.out.println(onewater);
//        }

        shortestHBpathways pathcalculaor = new shortestHBpathways(waterList, donor, acceptor);
        List<water> waterChain = pathcalculaor.findShortestPath();
        if (waterChain != null)
            myformat.output(pathwayfilename, waterChain);
    }

}


