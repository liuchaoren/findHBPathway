package water;

/**
 * Created by Chaoren on 11/12/16.
 */
public class water {
    atom O, H1, H2;
    public water(atom O, atom H1, atom H2) {
        this.O = O;
        this.H1 = H1;
        this.H2 = H2;
    }

    @Override
    public String toString() {
        return O.toString() + "\t" + H1.toString() + "\t" + H2.toString();

    }
}
