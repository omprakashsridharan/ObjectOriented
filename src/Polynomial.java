
import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer,Integer> treeMap;

    public TreeMap<Integer, Integer> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(TreeMap<Integer, Integer> treeMap) {
        this.treeMap = treeMap;
    }

    Polynomial(String polynomial){
        treeMap = new TreeMap<>();
        String processedPolynomial = polynomial.replace("-","+-");
        String[] elements = processedPolynomial.split("\\+");
        for(String element : elements){
            int power;
            int coefficient;
            String[] parts = element.split("\\^");
            String numbersOnly = parts[0].replaceAll("[^0-9]","");
            power = Integer.valueOf(parts[1]);
            coefficient = Integer.valueOf(numbersOnly);
            treeMap.put(power,coefficient);
        }
    }
}
