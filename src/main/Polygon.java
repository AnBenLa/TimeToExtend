package main;

public class Polygon {
    private Vector x;
    private Vector y;
    private Vector z;

    public Polygon(Vector x, Vector y, Vector z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector getX() {
        return x;
    }

    public Vector getY() {
        return y;
    }

    public Vector getZ() {
        return z;
    }
}
