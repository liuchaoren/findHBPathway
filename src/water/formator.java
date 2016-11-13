package water;
import java.util.*;
import java.io.*;

/**
 * Created by Chaoren on 11/12/16.
 */
public class formator {

    public List<water> waterFormater(String pdbfilename) throws IOException {
//        List<atom> Oatoms = new ArrayList<>();
//        List<atom> Hatoms = new ArrayList<>();
        List<water> waterlist = new ArrayList<>();
        BufferedReader myreader = new BufferedReader(new FileReader(pdbfilename));
        String line;
        int count = 0;
        int pre=0, newpre;
        List<atom> allatoms = new ArrayList<>();

        while ((line = myreader.readLine()) != null) {
            if (line.length() >= 4 && line.substring(0, 4).equals("ATOM")) {

                char atomname = line.charAt(13);
                float x = Float.parseFloat(line.substring(30, 38));
                float y = Float.parseFloat(line.substring(38, 46));
                float z = Float.parseFloat(line.substring(46, 54));
                atom oneatom = new atom(atomname, new float[] {x, y, z});

                if ((newpre=Integer.parseInt(line.substring(22, 26).trim())) != pre) {
                    if (count!=3) {
                        while (count >0) {
                            allatoms.remove(allatoms.size()-1);
                            count--;
                        }
                    } else {
                        count=0;
                    }
                    pre = newpre;
                }

                allatoms.add(oneatom);
                count++;
            }
        }

        for (int i=0; i<allatoms.size()/3; i++) {
            waterlist.add(new water(allatoms.get(i*3), allatoms.get(i*3+1), allatoms.get(i*3+2)));
        }
        myreader.close();
        return waterlist;
    }

    public void output(String filename, List<water> waterChain) throws IOException {

        BufferedWriter BW = new BufferedWriter(new FileWriter(filename));

        for (int i=0; i<waterChain.size(); i++) {
            water onewater = waterChain.get(i);
            atom O = onewater.O;
            atom H1 = onewater.H1;
            atom H2 = onewater.H2;
            BW.write(String.format("ATOM%7d  OH2              %8.3f%8.3f%8.3f                  WT1\n", i*3+1, O.pos[0], O.pos[1], O.pos[2]));
            BW.write(String.format("ATOM%7d  H1               %8.3f%8.3f%8.3f                  WT1\n", i*3+2, H1.pos[0], H1.pos[1], H1.pos[2]));
            BW.write(String.format("ATOM%7d  H2               %8.3f%8.3f%8.3f                  WT1\n", i*3+3, H2.pos[0], H2.pos[1], H2.pos[2]));
        }

        BW.write(String.format("END"));
        BW.close();
    }

}
