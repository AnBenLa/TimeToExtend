package main;

import java.util.ArrayList;

public class Polygon {
    private Vector x;
    private Vector y;
    private Vector z;

    public Polygon(Vector x, Vector y, Vector z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //TODO Bounding Box

    public Vector getX() {
        return x;
    }

    public Vector getY() {
        return y;
    }

    public Vector getZ() {
        return z;
    }

    public Ebene Ebenengleichung() throws Exception{
        return new Ebene(this.x,this.y,this.z);
    }

    public boolean inView(Camera camera, int x, int y) throws Exception{
        Ebene plane = this.Ebenengleichung();
        ArrayList<Fraction> values = plane.getValues();
        Fraction reel = new Fraction(0);
        Fraction unknown = new Fraction(0);
        for(int i = 0; i < values.size() - 1; i++){
            reel = reel.add(values.get(i).multiply(camera.getDirection().getPosition().getVectorValues()[i]));
            unknown = unknown.add(camera.getDirection().getDirection().getVectorValues()[i].multiply(values.get(i)));
        }
        reel = values.get(values.size() - 1).subtract(reel);

        if(unknown.equals(new Fraction(0))){
            return false;
        }
        Fraction factor = reel.divide(unknown);
        Vector intersection = camera.getDirection().getPosition().add(camera.getDirection().getDirection().scalarMult(factor));

        Vector xy = this.y.subtract(this.x);
        Vector yz = this.z.subtract(this.y);
        Vector xz = this.z.subtract(this.x);

        Matrix mat = new Matrix(new Vector[]{xy, xz});
        Matrix mat2 = new Matrix(new Vector[]{xy, yz});

        Vector result1 = mat.solveMultiplication(intersection.subtract((this.x)));
        for(Fraction value : result1.getVectorValues()){
            if(value.getDoubleValue() < 0 || value.getDoubleValue() > 1){
                return false;
            }
        }
        Vector result2 = mat2.solveMultiplication(intersection.subtract((this.x)));
        for(Fraction value : result2.getVectorValues()){
            if(value.getDoubleValue() < 0 || value.getDoubleValue() > 1){
                return false;
            }
        }


        //x + xy.scalarMult(a) + xz.scalarMult(b) = intersection;

        return true;
    }
}
