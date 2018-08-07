import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
	private TreeMap<Integer,Float> treeMap;
	private int degree = 0; 
	
	Polynomial(){
		treeMap = new TreeMap<>();
	}

    public TreeMap<Integer, Float> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(TreeMap<Integer, Float> treeMap) {
        this.treeMap = treeMap;
    }

    Polynomial(String polynomial){
        treeMap = new TreeMap<>(Collections.reverseOrder());
        String processedPolynomial = polynomial.replace("-","+-");
        String[] elements = processedPolynomial.split("\\+");
        for(String element : elements){
        	if(element.equals("")) continue;
            int power;
            float coefficient;
            String[] parts = element.split("\\^");
            String numbersOnly = parts[0].replaceAll("[a-z]","");
            power = Integer.valueOf(parts[1]);
            coefficient = Integer.valueOf(numbersOnly);
            treeMap.put(power,coefficient);
        }
        degree = treeMap.firstKey();
    }
	
	public void add(Polynomial poly){
		for (Integer key: poly.treeMap.keySet()){
			float currentPolynomialCoefficient = 0;
			if(this.treeMap.containsKey(key))
				currentPolynomialCoefficient = this.treeMap.get(key);
			this.treeMap.put(key, currentPolynomialCoefficient+poly.treeMap.get(key));
		}
	}
	
	public void subtract(Polynomial poly){
		for (Integer key: poly.treeMap.keySet()){
			float currentPolynomialCoefficient = 0;
			if(this.treeMap.containsKey(key))
				currentPolynomialCoefficient = this.treeMap.get(key);
			this.treeMap.put(key,currentPolynomialCoefficient- poly.treeMap.get(key));
		}
	}
	
	public void divide(Polynomial p1){
		Polynomial answer = new Polynomial();
		while(this.degree>=p1.degree){

			float tmp = this.treeMap.get(this.degree)/p1.treeMap.get(p1.degree);
			Polynomial tmp_poly = new Polynomial();
			tmp_poly.treeMap.put(this.degree-p1.degree, tmp);
			this.subtract(multiply(tmp_poly,p1));
			answer.add(tmp_poly);
			updateDegree();
		}
		System.out.println(answer);
		System.out.println(this);
	}
	
	private void updateDegree() {
		this.degree = 0;
		for(Integer key: treeMap.keySet()){
			if(this.treeMap.get(key)!=0)
				if(this.degree<key)
					this.degree = key;
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

		if(polynomialString != "" && polynomialString.charAt(0)=='+'){
			return polynomialString.substring(1,polynomialString.length());
		}else if( this.treeMap.size() == 1 && this.treeMap.get(1) == 0 ){
			return "0";
		}
		return polynomialString;
		
	}


	 public static Polynomial initPolynomialCoeff(Polynomial p) {
		for(int i = 0; i < 20; i++) {
			p.treeMap.put(i, (float)0);
		}
		return p;
	}
	 
    public static Polynomial multiplyConstant(Polynomial p, float constant) {
		for(Map.Entry<Integer, Float> entry: p.treeMap.entrySet()) {
			entry.setValue(entry.getValue()*constant);
		}
		return p;
    }

	 public static Polynomial multiplyVariable(Polynomial p, int power) {
		Polynomial ans = new Polynomial();

		for(Map.Entry<Integer, Float> entry: p.treeMap.entrySet()) {
			ans.treeMap.put(entry.getKey() + power, entry.getValue());
		}
		return ans;

	}


	public static Polynomial multiply(Polynomial p1, Polynomial p2) {
		Polynomial ans = new Polynomial();
		ans = initPolynomialCoeff(ans);

		for(Map.Entry<Integer, Float> entry: p1.treeMap.entrySet()) {

			Polynomial tmp = p2;

			if(entry.getKey() > 0)
				tmp = multiplyVariable(tmp, entry.getKey());
			tmp = multiplyConstant(tmp, entry.getValue());

			ans.add(tmp);
		}
		return ans;

	}

}

