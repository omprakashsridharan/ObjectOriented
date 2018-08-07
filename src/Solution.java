public class Solution {
    public static void main(String[] args){

        Polynomial p1 = new Polynomial("1x^2-5x^1+7x^0");

        Polynomial p2 = new Polynomial("1x^1-3x^0");
        p1.divide(p2);
        

    }
}
