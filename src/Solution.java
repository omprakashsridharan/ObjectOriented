public class Solution {
    public static void main(String[] args){
        Polynomial p1 = new Polynomial("3x^1");
        Polynomial p2 = new Polynomial("3x^1");
        p1.subtractFrom(p2);
        System.out.println(p1.toString());
    }
}
