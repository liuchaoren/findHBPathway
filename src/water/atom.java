package water;

/**
 * Created by Chaoren on 11/12/16.
 */

public class atom {
    char atomtype;
    float[] pos;
    public atom(char atomtype, float[] pos) {
        this.atomtype = atomtype;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return String.format("%c [%8.3f, %8.3f, %8.3f]", atomtype, pos[0], pos[1], pos[2]);
    }
}
