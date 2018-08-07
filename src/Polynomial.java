import java.util.Collections;
import java.util.TreeMap;

public class Polynomial {
	TreeMap<Integer, Integer> treeMap= new TreeMap<>(Collections.reverseOrder());
	
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
			if(this.treeMap.get(key)>0)
				polynomialString+=("+" + this.treeMap.get(key)+"x^"+key);
			if(this.treeMap.get(key)<0)
				polynomialString+=(this.treeMap.get(key)+"x^"+key);
		}
		if(polynomialString.charAt(0)=='+'){
			return polynomialString.substring(1,polynomialString.length());
		}
		return polynomialString;
		
	}
	public static void main(String args[]){
		Polynomial p1 = new Polynomial();
		p1.treeMap.put(2, 4);
		p1.treeMap.put(1, 3);
		Polynomial p2 = new Polynomial();
		p2.treeMap.put(2, 3);
		p2.treeMap.put(1, 3);
		p1.add(p2);
		System.out.println(p1);
	}
}
