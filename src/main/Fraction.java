package main;

public class Fraction {

    public long divident;
    public long divisor;

    public Fraction(Long divident, Long divisor) throws Exception {
        if (divisor != 0) {
            simplify(divident,divisor);
        } else {
            throw new Exception("Cant devide by zero");
        }
    }

    public Fraction(int divident, int divisor) throws Exception {
        if (divisor != 0) {
            simplify(divident,divisor);
        } else {
            throw new Exception("Cant devide by zero");
        }
    }

    public Fraction(int value) {
        this.divident = value;
        this.divisor = 1;
    }

    public Fraction(double value) {
        long roundedValue = (long) Math.round(value * 1000);
        int exponent = Math.getExponent(value);
        long divident = roundedValue;
        long divisor = 1000;

        simplify(divident,divisor);
    }

    public void simplify(long divident, long divisor){
        long ggT = 1L;
        for (Long i = divisor; i > 1; i--) {
            if (divident % i == 0 && divisor % i == 0) {
                ggT = i;
                break;
            }
        }
        this.divident = divident / ggT;
        this.divisor = divisor / ggT;
    }

    public void simplify(){
        long divident = this.divident;
        long divisor = this.divisor;
        long ggT = 1L;
        for (Long i = divisor; i > 1; i--) {
            if (divident % i == 0 && divisor % i == 0) {
                ggT = i;
                break;
            }
        }
        if(divident == 0){
            divisor = 1;
        }
        this.divident = divident / ggT;
        this.divisor = divisor / ggT;
    }

    public Fraction add(Fraction additionalFraction) throws Exception {
        return new Fraction(this.divident * additionalFraction.divisor + additionalFraction.divident * this.divisor, this.divisor * additionalFraction.divisor);
    }

    public void addStatic(Fraction additionalFraction) throws Exception {
        this.divident = this.divident * additionalFraction.divisor + additionalFraction.divident * this.divisor;
        this.divisor = this.divisor * additionalFraction.divisor;
    }

    public Fraction subtract(Fraction additionalFraction) throws Exception {
        return new Fraction(this.divident * additionalFraction.divisor - additionalFraction.divident * this.divisor, this.divisor * additionalFraction.divisor);
    }

    public Fraction multiply(Fraction additionalFraction) throws Exception {
        return new Fraction(this.divident * additionalFraction.divident, this.divisor * additionalFraction.divisor);
    }

    public void multiplyStatic(Fraction additionalFraction) throws Exception{
        this.divident = this.divident * additionalFraction.divident;
        this.divisor = this.divisor * additionalFraction.divisor;
    }

    public Fraction divide(Fraction additionalFraction) throws Exception {
        return new Fraction(this.divident * additionalFraction.divisor, this.divisor * additionalFraction.divident);
    }

    public double getDoubleValue() {
        return (double) this.divident / (double) this.divisor;
    }

    public boolean equals(Fraction additionalFraction) {
        return this.divident == additionalFraction.divident && this.divisor == additionalFraction.divisor;
    }

    public String toString() {
        if (this.divisor == 1) {
            return Long.toString(this.divident);
        } else if (this.divident == 0) {
            return "0";
        } else {
            return this.divident + "/" + this.divisor;
        }
    }
}
