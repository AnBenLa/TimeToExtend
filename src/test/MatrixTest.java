package test;

import main.Fraction;
import main.Matrix;
import main.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MatrixTest {

    static Matrix aM,bM;

    @BeforeAll
    static void initAll(){
        Vector a = new Vector(new int[]{1, 4, 5});
        Vector b = new Vector(new int[]{3, 6, 4});
        Vector c = new Vector(new int[]{1, 4, 5});

        Vector d = new Vector(new int[]{7, 4, 2});
        Vector e = new Vector(new int[]{6, 6, 66});
        Vector f = new Vector(new int[]{3, 7, 4});

         aM = new Matrix(new Vector[]{a, b, c});
         bM = new Matrix(new Vector[]{d, e, f});
    }

    @Test
    void add() throws Exception{
        Vector a = new Vector(new int[]{8, 8, 7});
        Vector b = new Vector(new int[]{9, 12, 70});
        Vector c = new Vector(new int[]{4, 11, 9});
        Matrix result = new Matrix(new Vector[]{a,b,c});
        Vector d = new Vector(new int[]{1,6,9});
        Matrix wrongResult = new Matrix(new Vector[]{a,b,c,d});

        Assertions.assertTrue(aM.add(bM).equals(result));
        Assertions.assertFalse(aM.add(bM).equals(wrongResult));
    }

    @Test
    void subtract()  throws Exception{
        Vector a = new Vector(new int[]{-6, 0, 3});
        Vector b = new Vector(new int[]{-3, 0, -62});
        Vector c = new Vector(new int[]{-2, -3, 1});
        Matrix result = new Matrix(new Vector[]{a,b,c});
        Vector d = new Vector(new int[]{1,6,9});
        Matrix wrongResult = new Matrix(new Vector[]{a,b,d});

        Assertions.assertTrue(aM.subtract(bM).equals(result));
        Assertions.assertFalse(aM.subtract(bM).equals(wrongResult));
    }

    @Test
    void sameWidthAndHeight() {
        Vector a = new Vector(new int[]{-6, 0, 3});
        Vector b = new Vector(new int[]{-3, 0, -62});
        Vector c = new Vector(new int[]{-2, -3, 1});
        Vector d = new Vector(new int[]{1,6,9});
        Matrix wrongResult = new Matrix(new Vector[]{a,b,c,d});

        Assertions.assertTrue(aM.sameWidthAndHeight(bM));
        Assertions.assertFalse(aM.sameWidthAndHeight(wrongResult));
    }

    @Test
    void squareWith() {
        Vector a = new Vector(new int[]{-6, 0, 3});
        Vector b = new Vector(new int[]{-3, 0, -62});
        Vector c = new Vector(new int[]{-2, -3, 1});
        Vector d = new Vector(new int[]{1,6,9});
        Matrix wrongResult = new Matrix(new Vector[]{a,b,c,d});

        Assertions.assertTrue(aM.squareWith(bM));
        Assertions.assertFalse(aM.squareWith(wrongResult));
    }

    @Test
    void negate() throws Exception{
        Vector a = new Vector(new int[]{-1, -4, -5});
        Vector b = new Vector(new int[]{-3, -6, -4});
        Vector c = new Vector(new int[]{-1, -4, -5});
        Vector d = new Vector(new int[]{1,-4,-5});
        Matrix result = new Matrix(new Vector[]{a,b,c});
        Matrix wrongResult = new Matrix(new Vector[]{a,b,d});

        Assertions.assertTrue(aM.negate().equals(result));
        Assertions.assertFalse(aM.negate().equals(wrongResult));
    }

    @Test
    void multiply() throws Exception{
        Vector a = new Vector(new int[]{21, 60, 61});
        Vector b = new Vector(new int[]{90, 324, 384});
        Vector c = new Vector(new int[]{28, 70, 63});
        Vector d = new Vector(new int[]{28,70,62});
        Matrix result = new Matrix(new Vector[]{a,b,c});
        Matrix wrongResult = new Matrix(new Vector[]{a,b,d});

        Assertions.assertTrue(aM.multiply(bM).equals(result));
        Assertions.assertFalse(aM.multiply(bM).equals(wrongResult));
    }

    @Test
    void getRows() {
        //TODO
    }

    @Test
    void getRank() throws Exception{
        Assertions.assertEquals(aM.getRank(), 2);
        Assertions.assertEquals(bM.getRank(), 3);
    }

    @Test
    void getTranspDiagForm() {
        //TODO
    }

    @Test
    void getDet() throws Exception{
        Assertions.assertTrue(aM.getDet().equals(new Fraction(0)));
        Assertions.assertTrue(bM.getDet().equals(new Fraction(-2322)));
    }
}