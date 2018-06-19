package main;

public class Vector {

    public Fraction[] fractionValues;

    public Vector(Fraction[] fractionValues) {
        this.fractionValues = fractionValues;
    }

    public Vector(double[] doubleValues) {
        this.fractionValues = new Fraction[doubleValues.length];
        for (int i = 0; i < doubleValues.length; i++) {
            fractionValues[i] = new Fraction(doubleValues[i]);
        }
    }

    public Vector(int[] intValues) {
        this.fractionValues = new Fraction[intValues.length];
        for (int i = 0; i < this.fractionValues.length; i++) {
            this.fractionValues[i] = new Fraction(intValues[i]);
        }
    }

    public Vector(int numberOfValues) {
        this.fractionValues = new Fraction[numberOfValues];
        for(int i = 0; i < numberOfValues; i++){
            this.fractionValues[i] = new Fraction(0);
        }
    }

    public Fraction[] getVectorValues() {
        return fractionValues;
    }

    public boolean deleteValues() {
        this.fractionValues = new Fraction[fractionValues.length];
        return true;
    }

    public Vector add(Vector additionalVector) throws Exception {
        if (this.fractionValues.length == additionalVector.getVectorValues().length) {
            Fraction[] newVectorValues = new Fraction[this.fractionValues.length];
            for (int i = 0; i < this.fractionValues.length; i++) {
                newVectorValues[i] = this.fractionValues[i].add(additionalVector.getVectorValues()[i]);
            }
            return new Vector(newVectorValues);
        }
        throw new Exception("Could not add the two vectors");
    }

    public Vector subtract(Vector additionalVector) throws Exception {
        if (this.fractionValues.length == additionalVector.getVectorValues().length) {
            Fraction[] newVectorValues = new Fraction[this.fractionValues.length];
            for (int i = 0; i < this.fractionValues.length; i++) {
                newVectorValues[i] = this.fractionValues[i].subtract(additionalVector.getVectorValues()[i]);
            }
            return new Vector(newVectorValues);
        }
        throw new Exception("Could not substract the two vectors");
    }

    public Vector scalarMult(Fraction factor) throws Exception{
        Fraction[] newDoubleValues = new Fraction[this.fractionValues.length];
        for (int i = 0; i < this.fractionValues.length; i++) {
            newDoubleValues[i] = this.fractionValues[i].multiply(factor);
        }
        return new Vector(newDoubleValues);
    }

    public Vector scalarMult(int factor) throws Exception{
        Fraction[] newDoubleValues = new Fraction[this.fractionValues.length];
        for (int i = 0; i < this.fractionValues.length; i++) {
            newDoubleValues[i] = this.fractionValues[i].multiply(new Fraction(factor));
        }
        return new Vector(newDoubleValues);
    }

    public Vector crossProd(Vector additionalVector) throws Exception {
        if (this.fractionValues.length == additionalVector.getVectorValues().length && this.fractionValues.length == 3) {
            Fraction[] newVectorValues = new Fraction[3];
            for (int i = 0; i < this.fractionValues.length; i++) {
                newVectorValues[i] = this.fractionValues[(i + 1) % 3].multiply(additionalVector.getVectorValues()[(i + 2) % 3]).subtract(
                        this.fractionValues[(i + 2) % 3].multiply(additionalVector.getVectorValues()[(i + 1) % 3]));
            }
            return new Vector(newVectorValues);
        }
        throw new Exception("Could not create the crossSum of the two vectors");
    }

    public Fraction scalarProd(Vector additionalVector) throws Exception {
        if (this.fractionValues.length == additionalVector.getVectorValues().length) {
            Fraction scalarValue = new Fraction(0);
            for (int i = 0; i < this.fractionValues.length; i++) {
                scalarValue.addStatic(this.fractionValues[i].multiply(additionalVector.getVectorValues()[i]));
            }
            return scalarValue;
        }
        throw new Exception("Could not calculate scalar product of the two vectors");
    }

    public double calculateAngleRad(Vector additionalVector) throws Exception {
        return Math.acos(this.scalarProd(additionalVector).getDoubleValue() /
                (
                        Math.sqrt(this.scalarProd(this).getDoubleValue())
                                * Math.sqrt(additionalVector.scalarProd(additionalVector).getDoubleValue())
                )
        );
    }

    public double calculateAngleDeg(Vector additionalVector) throws Exception {
        return Math.toDegrees(
                Math.acos(
                        this.scalarProd(additionalVector).getDoubleValue() / (this.getNorm() * additionalVector.getNorm())
                )
        );
    }

    public double getNorm() throws Exception {
        return Math.sqrt(this.scalarProd(this).getDoubleValue());
    }

    public Vector vecProjectTo(Vector additionalVector) throws Exception {
        return additionalVector.scalarMult(this.scalarProd(additionalVector).divide (additionalVector.scalarProd(additionalVector)));
    }

    public Vector negateValues() throws Exception{
        Fraction[] newValues = new Fraction[this.fractionValues.length];
        for (int i = 0; i < newValues.length; i++) {
            newValues[i] = new Fraction(-this.fractionValues[i].divident, this.fractionValues[i].divisor);
        }
        return new Vector(newValues);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (Fraction value : this.fractionValues) {
            builder.append(value + "; ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(")");
        return builder.toString();
    }

    public Vector clone() {
        Fraction[] newValues = new Fraction[this.fractionValues.length];
        for (int i = 0; i < this.fractionValues.length; i++) {
            newValues[i] = this.fractionValues[i];
        }
        return new Vector(newValues);
    }

    public boolean isNullVec() {
        boolean isNull = true;
        for (Fraction value : this.fractionValues) {
            if (value.divident != 0) {
                isNull = false;
            }
        }
        return isNull;
    }

    public boolean equals(Vector additionalVector){
        if(this.fractionValues.length != additionalVector.getVectorValues().length){
            return false;
        } else {
            for (int i = 0; i < this.fractionValues.length; i++) {
                if (!this.fractionValues[i].equals(additionalVector.getVectorValues()[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public String toVertivalString() {
        StringBuilder builder = new StringBuilder();
        for (Fraction value : this.fractionValues) {
            builder.append(value + "\n");
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }
}
