package zionsaaan.numer0n;

public class Number {

    private boolean change;
    private int number1;
    private int number2;
    private int number3;

    public Number(boolean change, int number1, int number2, int number3) {
        this.change = change;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    public boolean isChange() {
        return change;
    }
    public void setChange(boolean change) {
        this.change = change;
    }
    public int getNumber1() {
        return number1;
    }
    public void setNumber1(int number1) {
        this.number1 = number1;
    }
    public int getNumber2() {
        return number2;
    }
    public void setNumber2(int number2) {
        this.number2 = number2;
    }
    public int getNumber3() {
        return number3;
    }
    public void setNumber3(int number3) {
        this.number3 = number3;
    }

}
