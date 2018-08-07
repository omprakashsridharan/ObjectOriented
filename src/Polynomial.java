import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
	private TreeMap<Integer,Integer> treeMap;

	Polynomial(){
		treeMap = new TreeMap<>();
	}

    public TreeMap<Integer, Integer> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(TreeMap<Integer, Integer> treeMap) {
        this.treeMap = treeMap;
    }

    Polynomial(String polynomial){
        treeMap = new TreeMap<>(Collections.reverseOrder());
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
	
	public void add(Polynomial poly){
		for (Integer key: poly.treeMap.keySet()){
			int currentPolynomialCoefficient = 0;
			if(this.treeMap.containsKey(key))
				currentPolynomialCoefficient = this.treeMap.get(key);
			this.treeMap.put(key, currentPolynomialCoefficient+poly.treeMap.get(key));
		}
	}
	
	public void subtractFrom(Polynomial poly){
		for (Integer key: poly.treeMap.keySet()){
			int currentPolynomialCoefficient = 0;
			if(this.treeMap.containsKey(key))
				currentPolynomialCoefficient = this.treeMap.get(key);
			this.treeMap.put(key, poly.treeMap.get(key)-currentPolynomialCoefficient);
		}
	}
	
	public String toString(){
		String polynomialString = "";
		for (Integer key: this.treeMap.keySet()){
			String variableString ="";
			if(key>1){
				variableString = "x^"+key;
			}
			else if(key==1){
				variableString = "x";
			}
			
			if(this.treeMap.get(key)>0)
				polynomialString+=("+" + this.treeMap.get(key)+variableString);
			if(this.treeMap.get(key)<0)
				polynomialString+=(this.treeMap.get(key)+variableString);
		}
		if(polynomialString.charAt(0)=='+'){
			return polynomialString.substring(1,polynomialString.length());
		}
		return polynomialString;
		
	}


	 public static Polynomial initPolynomialCoeff(Polynomial p) {
		for(int i = 0; i < 20; i++) {
			p.treeMap.put(i, 0);
		}
		return p;
	}
	    public static Polynomial multiplyConstant(Polynomial p, int constant) {
		for(Map.Entry<Integer, Integer> entry: p.treeMap.entrySet()) {
			entry.setValue(entry.getValue()*constant);
		}
		return p;
	 }

	 public static Polynomial multiplyVariable(Polynomial p) {
		Polynomial ans = new Polynomial();
		for(Map.Entry<Integer, Integer> entry: p.treeMap.entrySet()) {
			ans.treeMap.put(entry.getKey() + 1, entry.getValue());
		}
		return ans;

	}

	public static Polynomial multiply(Polynomial p1, Polynomial p2) {
		Polynomial ans = new Polynomial();
		ans = initPolynomialCoeff(ans);

		for(Map.Entry<Integer, Integer> entry: p1.treeMap.entrySet()) {

			Polynomial tmp = p2;

			if(entry.getKey() > 0)
				tmp = multiplyVariable(tmp);
			tmp = multiplyConstant(tmp, entry.getValue());

			ans.add(tmp);
		}
		return ans;

	}

}

