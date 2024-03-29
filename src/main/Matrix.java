package main;

public class Matrix {
    public Vector[] rows;

    //create Matrix with rows
    public Matrix(Vector[] rows) {
        this.rows = rows;
    }

    //create Matrix with colums
    public Matrix(double[][] values) throws Exception {
        for (double[] colums : values) {
            if (colums.length != values[0].length) {
                throw new Exception("Could not create a new matrix");
            }
        }

        Vector[] rowVectors = new Vector[values[0].length];
        for (int c = 0; c < values[0].length; c++) {
            double[] row = new double[values.length];
            for (int i = 0; i < values.length; i++) {
                row[i] = values[i][c];
            }
            Vector rowVector = new Vector(row);
            rowVectors[c] = rowVector;
        }
        this.rows = rowVectors;
    }

    public Matrix(Fraction[][] values) throws Exception {
        for (Fraction[] colums : values) {
            if (colums.length != values[0].length) {
                throw new Exception("Could not create a new matrix");
            }
        }

        Vector[] rowVectors = new Vector[values[0].length];
        for (int c = 0; c < values[0].length; c++) {
            Fraction[] row = new Fraction[values.length];
            for (int i = 0; i < values.length; i++) {
                row[i] = values[i][c];
            }
            Vector rowVector = new Vector(row);
            rowVectors[c] = rowVector;
        }
        this.rows = rowVectors;
    }

    public Matrix add(Matrix additionalMatrix) throws Exception {
        if (sameWidthAndHeight(additionalMatrix)) {
            Vector[] newRows = new Vector[this.rows.length];
            for (int i = 0; i < this.rows.length; i++) {
                newRows[i] = this.rows[i].add(additionalMatrix.getRows()[i]);
            }
            return new Matrix(newRows);
        }
        throw new Exception("The matrices dont allow an addition");
    }

    public Matrix subtract(Matrix additionalMatrix) throws Exception {
        if (sameWidthAndHeight(additionalMatrix)) {
            Vector[] newRows = new Vector[this.rows.length];
            for (int i = 0; i < this.rows.length; i++) {
                newRows[i] = this.rows[i].subtract(additionalMatrix.getRows()[i]);
            }
            return new Matrix(newRows);
        }
        throw new Exception("The matrices dont allow an subtraction");
    }

    public boolean sameWidthAndHeight(Matrix additionalMatrix) {
        return this.rows.length == additionalMatrix.getRows().length
                && this.rows[0].getVectorValues().length == additionalMatrix.getRows()[0].getVectorValues().length;
    }

    public boolean squareWith(Matrix additionalMatrix) {
        return this.rows.length == additionalMatrix.getRows().length &&
                this.rows[0].getVectorValues().length == additionalMatrix.getRows()[0].getVectorValues().length &&
                this.rows[0].getVectorValues().length == additionalMatrix.getRows().length;
    }

    public Matrix negate() throws Exception {
        Vector[] newRows = new Vector[this.rows.length];
        for (int i = 0; i < newRows.length; i++) {
            newRows[i] = this.rows[i].negateValues();
        }
        return new Matrix(newRows);
    }

    public Matrix multiply(Matrix additionalMatrix) throws Exception {
        if (squareWith(additionalMatrix)) {
            int width = this.rows.length;
            Vector[] newRows = new Vector[width];

            for (int d = 0; d < width; d++) {
                Fraction[] newRowValues = new Fraction[width];

                for (int i = 0; i < width; i++) {
                    Fraction[] columValues = new Fraction[width];

                    for (int c = 0; c < width; c++) {
                        columValues[c] = this.rows[c].getVectorValues()[i];
                    }
                    Vector columVector = new Vector(columValues);
                    newRowValues[i] = columVector.scalarProd(additionalMatrix.getRows()[d]);
                }
                newRows[d] = new Vector(newRowValues);
            }
            return new Matrix(newRows);
        }
        throw new Exception("The matrices dont allow multiplication");
    }

    public Vector[] getRows() {
        return rows;
    }

    public int getRank() throws Exception {
        Matrix newMatrix = this.getTranspDiagForm();
        int rank = newMatrix.rows.length;
        for (Vector rowVec : newMatrix.rows) {
            if (rowVec.isNullVec()) {
                rank -= 1;
            }
        }
        return rank;
    }

    public Matrix getTranspDiagForm() throws Exception {
        Matrix newMatrix = this.clone();
        for (int i = 0; i < this.rows.length; i++) {
            for (int c = i + 1; c < this.rows.length; c++) {
                if (!newMatrix.rows[i].getVectorValues()[i].equals(new Fraction(0))) {
                    Fraction factor = newMatrix.rows[c].getVectorValues()[i].divide(newMatrix.rows[i].getVectorValues()[i]);
                    newMatrix.rows[c] = newMatrix.rows[c].subtract(newMatrix.rows[i].scalarMult(factor));
                }
            }
        }
        return newMatrix;
    }

    public Matrix getTriangForm() throws Exception {
        Matrix transposedMatr = this.transpose();
        for (int i = 0; i < transposedMatr.getRows()[0].getVectorValues().length; i++) {
            Fraction tmpFactor = new Fraction(1).divide(transposedMatr.getRows()[i].getVectorValues()[i]);
            transposedMatr.getRows()[i] = transposedMatr.getRows()[i].scalarMult(tmpFactor);

            for (int c = i + 1; c < transposedMatr.getRows().length; c++) {
                transposedMatr.getRows()[c] = transposedMatr.getRows()[c]
                        .subtract(
                                transposedMatr.getRows()[i].
                                        scalarMult(
                                                transposedMatr.getRows()[c].getVectorValues()[i]));

            }
        }
        return transposedMatr.transpose();
    }

    public Matrix transpose() throws Exception {
        Fraction[][] newValues = new Fraction[this.rows.length][this.rows[0].getVectorValues().length];
        for (int i = 0; i < this.rows.length; i++) {
            Fraction[] tmp = new Fraction[this.rows[0].getVectorValues().length];
            for (int c = 0; c < this.rows[0].getVectorValues().length; c++) {
                tmp[c] = this.rows[i].getVectorValues()[c];
            }
            newValues[i] = tmp;
        }
        return new Matrix(newValues);
    }

    public Matrix inverse() throws Exception {
        //TODO implement
        if (sameWidthAndHeight(this)) {
            Matrix inverse = this.clone();
        }
        return null;
    }

    public Vector solveMultiplication(Vector result) throws Exception {
        //TODO vereinfachen!
        Vector tmpResult = result.clone();
        Matrix transposedMatr = this.transpose();
        for (int i = 0; i < transposedMatr.getRows()[0].getVectorValues().length; i++) {
            Fraction tmpFactor = new Fraction(1).divide(transposedMatr.getRows()[i].getVectorValues()[i]);
            transposedMatr.getRows()[i] = transposedMatr.getRows()[i].scalarMult(tmpFactor);
            tmpResult.getVectorValues()[i] = tmpResult.getVectorValues()[i].multiply(tmpFactor);

            for (int c = i + 1; c < transposedMatr.getRows().length; c++) {
                tmpResult.getVectorValues()[c] = tmpResult.getVectorValues()[c]
                        .subtract(
                                tmpResult.getVectorValues()[i]
                                        .multiply(transposedMatr.getRows()[c].getVectorValues()[i]));

                transposedMatr.getRows()[c] = transposedMatr.getRows()[c]
                        .subtract(
                                transposedMatr.getRows()[i].
                                        scalarMult(
                                                transposedMatr.getRows()[c].getVectorValues()[i]));

            }
        }

        int diff = transposedMatr.getRows().length - transposedMatr.getRows()[0].getVectorValues().length;

        if(diff > 0){
            int size = transposedMatr.getRows().length - diff;
            Vector[] newVectors = new Vector[size];
            for(int i = 0; i < size; i++){
                newVectors[i] = transposedMatr.getRows()[i];
            }
            transposedMatr = new Matrix(newVectors);
        }

        for (int i = 1; i < transposedMatr.getRows()[0].getVectorValues().length; i++) {
            for (int c = i; c < transposedMatr.getRows()[0].getVectorValues().length; c++) {
                Fraction tmpFactor = transposedMatr.getRows()[transposedMatr.getRows().length - c - 1]
                            .getVectorValues()[transposedMatr.getRows()[0].getVectorValues().length - i];
                transposedMatr.getRows()[transposedMatr.getRows().length - c - 1]
                            .getVectorValues()[transposedMatr.getRows()[0]
                            .getVectorValues().length - i] = new Fraction(0);
                tmpResult.getVectorValues()[transposedMatr.getRows().length - c - 1] = tmpResult.getVectorValues()[transposedMatr.getRows().length - c - 1]
                            .subtract(tmpFactor.multiply(tmpResult.getVectorValues()[transposedMatr.getRows().length - i]));

            }
        }
        return tmpResult;
    }

    public Fraction getDet() throws Exception {
        if (squareWith(this)) {
            Fraction det = new Fraction(1);
            Matrix newMatrix = getTranspDiagForm();
            for (int i = 0; i < newMatrix.rows.length; i++) {
                det.multiplyStatic(newMatrix.rows[i].fractionValues[i]);
            }
            det.simplify();
            return det;
        }
        throw new Exception("Not a square matrix.");
    }

    public Matrix clone() {
        Vector[] newRows = new Vector[this.rows.length];
        for (int i = 0; i < rows.length; i++) {
            newRows[i] = rows[i].clone();
        }
        return new Matrix(newRows);
    }

    public boolean equals(Matrix additionalMatrix) {
        if (this.rows.length != additionalMatrix.getRows().length) {
            return false;
        } else {
            for (int i = 0; i < this.rows.length; i++) {
                if (!this.rows[i].equals(additionalMatrix.getRows()[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows[0].getVectorValues().length; i++) {
            builder.append("|");
            for (int c = 0; c < rows.length; c++) {
                builder.append(rows[c].getVectorValues()[i] + "  ");
            }
            builder.setLength(builder.length() - 2);
            builder.append("|\n");
        }
        return builder.toString();
    }
}
