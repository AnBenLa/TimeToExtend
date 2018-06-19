package main;

import java.awt.image.RenderedImage;

public class Main  {

    public static void main(String[] args)throws Exception {

        fractionTest();
        System.out.println("--------------------------------------------------------------------");
        vectorTest();
        System.out.println("--------------------------------------------------------------------");
        matrixTest();
        System.out.println("--------------------------------------------------------------------");
        polygonTest();

        //Frame test = new Frame();
        //test.jFrameTest();

        //RenderImage.createImage();
        //RenderImage.openImage();
    }

    public static void fractionTest() {
        try {
            Fraction a = new Fraction(1, 3);
            Fraction b = new Fraction(5, 27);
            System.out.println("Fraction a: " + a);
            System.out.println("Fraction b: " + b + "\n");

            System.out.println("a + b: " + a.add(b));
            System.out.println("a - b: " + a.subtract(b));
            System.out.println("a * b: " + a.multiply(b));
            System.out.println("a / b: " + a.divide(b) + "\n");

            Fraction c = new Fraction(0.62832);
            System.out.println("Fraction c: " + c + "\n");

            System.out.println("a == b: " + a.equals(b));
            System.out.println("double value of a: " + a.getDoubleValue());
            System.out.println("double value of b: " + b.getDoubleValue());
            System.out.println("double value of c: " + c.getDoubleValue() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void vectorTest() {
        try {
            Vector a = new Vector(new int[]{2, -4, -1});
            Vector b = new Vector(new int[]{0, 5, 2});
            System.out.println("Vector a: " + a);
            System.out.println("Vector b: " + b + "\n");

            System.out.println("a + b: " + a.add(b));
            System.out.println("a - b: " + a.subtract(b));
            System.out.println("a * 15: " + a.scalarMult(15));
            System.out.println("a x b: " + a.crossProd(b));
            System.out.println("a negated: " + a.negateValues() + "\n");

            System.out.println("angle between a and b in rad: " + a.calculateAngleRad(b));
            System.out.println("angle between a and b in deg: " + a.calculateAngleDeg(b) + "\n");

            System.out.println("norm of a: " + a.getNorm() + "\n");

            Vector c = new Vector(new int[]{1, 4, 0});
            Vector d = new Vector(new int[]{4, 2, 4});

            System.out.println("Vector c: " + c);
            System.out.println("Vector d: " + d);
            System.out.println("c projected on to d: " + c.vecProjectTo(d));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void matrixTest() {
        try {
            Vector a = new Vector(new int[]{1, 4, 5});
            Vector b = new Vector(new int[]{3, 6, 4});
            Vector c = new Vector(new int[]{1, 4, 5});
            Matrix aM = new Matrix(new Vector[]{a, b, c});

            System.out.println("Matrix A: \n" + aM);

            Vector d = new Vector(new int[]{7, 4, 2});
            Vector e = new Vector(new int[]{6, 6, 66});
            Vector f = new Vector(new int[]{3, 7, 4});
            Matrix bM = new Matrix(new Vector[]{d, e, f});

            System.out.println("Matrix B: \n" + bM);
            System.out.println("Matrix B Diag: \n" + bM.getTriangForm());
            System.out.println("Matrix B Transponiert: \n" + bM.transpose());

            Vector z = new Vector(new int[]{37,50,216});
            System.out.println("Vector z: " + z);
            System.out.println("Matrix B * x = z solution: \n" + bM.solveMultiplication(z) + "\n");


            System.out.println("B but instanciated by colums not rows:");
            System.out.println(new Matrix(new double[][]{{7, 6, 3}, {4, 6, 7}, {2, 66, 4}}));

            System.out.println("A + B: \n" + aM.add(bM));
            System.out.println("A - B: \n" + aM.subtract(bM));
            System.out.println("A negated: \n" + aM.negate());

            System.out.println("A * B: \n" + aM.multiply(bM));

            System.out.println("Rank of A: " + aM.getRank());
            System.out.println("Rank of B: " + bM.getRank() + "\n");

            System.out.println("Transp A Diag: \n" + aM.getTranspDiagForm());
            System.out.println("Transp B Diag: \n" + bM.getTranspDiagForm());

            System.out.println("Det A: \n" + aM.getDet());
            System.out.println("Det B: \n" + bM.getDet());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void polygonTest() throws Exception{
        try {
            Vector a = new Vector(new int[]{1, 0, 2});
            Vector b = new Vector(new int[]{0, 0, -4});
            Vector c = new Vector(new int[]{0, 1, -4});

            Vector a2 = new Vector(new double[]{0, 0, 3.5});
            Vector b2 = new Vector(new double[]{1, 0, 5});
            Vector c2 = new Vector(new double[]{0, 1, 6});

            Polygon abc = new Polygon(a, b, c);
            Polygon abc2 = new Polygon(a2, b2, c2);

            //TODO Testen!

            Camera camera = new Camera(new Vector(new int[]{2, 1, 3}), new Vector(new int[]{0, 2, 1}), 90);
            Camera camera2 = new Camera(new Vector(new int[]{2, 1, 3}), new Vector(new int[]{0, 1, 0}), 90);
            Camera camera3 = new Camera(new Vector(new int[]{9, -8, 10}), new Vector(new int[]{2, -2, 1}), 90);

            System.out.println(abc2.inView(camera3, 0, 0));
            System.out.println(abc.inView(camera, 0, 0));
            System.out.println(abc.inView(camera2, 0, 0));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
