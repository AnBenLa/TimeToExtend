package main;

import java.util.ArrayList;

public class Ebene {

    private Vector x;
    private Vector y;
    private Vector z;
    private ArrayList<Fraction> values = new ArrayList<>();

    public Ebene(Vector x, Vector y, Vector z) throws Exception{
        this.x = x;
        this.y = y;
        this.z = z;
        Vector xy = y.subtract(x);
        Vector xz = z.subtract(x);
        Vector n = xy.crossProd(xz);
        Fraction value = x.scalarProd(n);
        for(Fraction frac : n.getVectorValues()){
            this.values.add(frac);
        }
        this.values.add(value);
    }

    public Vector getX() {
        return x;
    }

    public void setX(Vector x) {
        this.x = x;
    }

    public Vector getY() {
        return y;
    }

    public void setY(Vector y) {
        this.y = y;
    }

    public Vector getZ() {
        return z;
    }

    public void setZ(Vector z) {
        this.z = z;
    }

    public ArrayList<Fraction> getValues() {
        return values;
    }

    public void setValues(ArrayList<Fraction> values) {
        this.values = values;
    }
}
