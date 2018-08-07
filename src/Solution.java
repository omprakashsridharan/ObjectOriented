public class Solution {
    public static void main(String[] args){
        Polynomial p1 = new Polynomial("4x^2+3x^1");

        Polynomial p2 = new Polynomial("2x^1");

        Polynomial result = Polynomial.multiply(p1,p2);
        System.out.println(result.toString());

    }
}
