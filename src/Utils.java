import java.util.Map;

public class Utils {

    static Polynomial initPolynomialCoeff(Polynomial p) {
        for(int i = 0; i < 20; i++) {
            p.treeMap.put(i, 0);
        }
        return p;
    }
    static Polynomial multiplyConstant(Polynomial p, int constant) {
        for(Map.Entry<Integer, Integer> entry: p.treeMap.entrySet()) {
            entry.setValue(entry.getValue()*constant);
        }
        return p;
    }

    static Polynomial multiplyVariable(Polynomial p) {
        Polynomial ans = new Polynomial();
        for(Map.Entry<Integer, Integer> entry: p.treeMap.entrySet()) {
            ans.treeMap.put(entry.getKey() + 1, entry.getValue());
        }
        return ans;

    }

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        return p1;
    }
    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial ans = new Polynomial();
        ans = initPolynomialCoeff(ans);

        for(Map.Entry<Integer, Integer> entry: p1.treeMap.entrySet()) {

            Polynomial currTerm = p2;

            if(entry.getKey() > 0)
                currTerm = multiplyVariable(currTerm);
            currTerm = multiplyConstant(currTerm, entry.getValue());

            ans = add(ans, currTerm);

        }
        return ans;

    }
}
