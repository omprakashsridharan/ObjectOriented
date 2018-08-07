public class Solution {
    public static void main(String[] args){
        Polynomial p1 = new Polynomial("3x^1+4x^0");

        Polynomial p2 = new Polynomial("4x^1+5x^0");

        p1.add(p2);
        p1.subtractFrom(p2);
        System.out.println(p1);

    }
}
