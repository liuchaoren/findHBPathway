package water;

/**
 * Created by Chaoren on 11/12/16.
 */

public class atom {
    char atomtype;
    float[] pos;
    int resid;
    public atom(int resid, char atomtype, float[] pos) {
        this.resid = resid;
        this.atomtype = atomtype;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return String.format("%d\t%c [%8.3f, %8.3f, %8.3f]", resid, atomtype, pos[0], pos[1], pos[2]);
    }
}
