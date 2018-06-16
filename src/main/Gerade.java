package main;

public class Gerade {
    private Vector position;
    private Vector direction;

    public Gerade(Vector position, Vector direction) {
        this.position = position;
        this.direction = direction;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }
}
