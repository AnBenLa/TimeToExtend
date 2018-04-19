package main;

public class Object {
    private Polygon[] polygons;

    public Object(Polygon[] polygons) {
        this.polygons = polygons;
    }

    //should return true when the camera pixel x,y sees the polygon
    public boolean inView(Camera camera, int x, int y){
        for(Polygon polygon : polygons) {
            try {
                if (camera.getPosition().add(camera.getDirection()).equals(polygon.getX())){
                    System.out.println("Point found");
                    return true;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
