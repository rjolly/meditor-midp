package jscl.math;

import myjava.lang.*;
import myjava.util.*;
import jscl.math.function.*;
import jscl.text.*;
import jscl.util.*;

public class Literal implements myComparable {
	final MyMap content=new MyTreeMap();
	int degree;

	Literal() {}

	public Literal multiply(Literal literal) {
		Literal l=valueof(this);
		l.put(literal);
		return l;
	}

	public Literal divide(Literal literal) throws ArithmeticException {
		Literal l=valueof(this);
		myIterator it=literal.content.entrySet().myIterator();
		while(it.hasNext()) {
			myMap.Entry e=(myMap.Entry)it.next();
			Variable v=(Variable)e.getKey();
			int c=((Integer)e.getValue()).intValue();
			Object o=content.get(v);
			if(o!=null) {
				int c2=((Integer)o).intValue();
				if(c>c2) throw new NotDivisibleException();
				else l.put(v,new Integer(-c));
			} else throw new NotDivisibleException();
		}
		return l;
	}

	public Literal gcd(Literal literal) {
		Literal l=newinstance();
		myIterator it=literal.content.entrySet().myIterator();
		while(it.hasNext()) {
			myMap.Entry e=(myMap.Entry)it.next();
			Variable v=(Variable)e.getKey();
			int c2=((Integer)e.getValue()).intValue();
			Object o=content.get(v);
			if(o!=null) {
				int c=((Integer)o).intValue();
				l.put(v,new Integer(Math.min(c,c2)));
			}
		}
		return l;
	}

	public Literal scm(Literal literal) {
		Literal l=valueof(this);
		myIterator it=literal.content.entrySet().myIterator();
		while(it.hasNext()) {
			myMap.Entry e=(myMap.Entry)it.next();
			Variable v=(Variable)e.getKey();
			int c2=((Integer)e.getValue()).intValue();
			Object o=content.get(v);
			if(o!=null) {
				int c=((Integer)o).intValue();
				if(c2>c) l.put(v,new Integer(c2-c));
			} else {
				l.put(v,new Integer(c2));
			}
		}
		return l;
	}

	public Literal valueof(Literal literal) {
		Literal l=newinstance();
		l.put(literal);
		return l;
	}

	public Generic[] productValue() throws NotProductException {
		Generic a[]=new Generic[content.size()];
		myIterator it=content.entrySet().myIterator();
		for(int i=0;i<a.length;i++) {
			myMap.Entry e=(myMap.Entry)it.next();
			Variable v=(Variable)e.getKey();
			int c=((Integer)e.getValue()).intValue();
			a[i]=v.expressionValue().pow(c);
		}
		return a;
	}

	public Object[] powerValue() throws NotPowerException {
		int n=content.size();
		if(n==0) return new Object[] {JSCLInteger.valueOf(1),new Integer(1)};
		else if(n==1) {
			myMap.Entry e=(myMap.Entry)content.entrySet().myIterator().next();
			Variable v=(Variable)e.getKey();
			Integer in=(Integer)e.getValue();
			return new Object[] {v.expressionValue(),in};
		} else throw new NotPowerException();
	}

	public Variable variableValue() throws NotVariableException {
		int n=content.size();
		if(n==0) throw new NotVariableException();
		else if(n==1) {
			myMap.Entry e=(myMap.Entry)content.entrySet().myIterator().next();
			Variable v=(Variable)e.getKey();
			int c=((Integer)e.getValue()).intValue();
			if(c==1) return v;
			else throw new NotVariableException();
		} else throw new NotVariableException();
	}

	public Variable[] variables() {
		Variable va[]=new Variable[content.size()];
		myIterator it=content.keySet().myIterator();
		for(int i=0;i<va.length;i++) {
			va[i]=(Variable)it.next();
		}
		return va;
	}

	public int degree() {
		return degree;
	}

	public int compareTo(Literal literal) {
		myIterator it1=content.entrySet().myIterator(true);
		myIterator it2=literal.content.entrySet().myIterator(true);
		while(true) {
			boolean b1=!it1.hasNext();
			boolean b2=!it2.hasNext();
			if(b1 && b2) return 0;
			else if(b1) return -1;
			else if(b2) return 1;
			else {
				myMap.Entry e1=(myMap.Entry)it1.next();
				myMap.Entry e2=(myMap.Entry)it2.next();
				Variable v1=(Variable)e1.getKey();
				Variable v2=(Variable)e2.getKey();
				int c=v1.compareTo(v2);
				if(c<0) return -1;
				else if(c>0) return 1;
				else {
					int c1=((Integer)e1.getValue()).intValue();
					int c2=((Integer)e2.getValue()).intValue();
					if(c1<c2) return -1;
					else if(c1>c2) return 1;
				}
			}
		}
	}

	public int compareTo(Object o) {
		return compareTo((Literal)o);
	}

	void put(Literal literal) {
		myIterator it=literal.content.entrySet().myIterator();
		while(it.hasNext()) {
			myMap.Entry e=(myMap.Entry)it.next();
			put(
				(Variable)e.getKey(),
				(Integer)e.getValue()
			);
		}
	}

	void put(Variable variable, Integer integer) {
		Object o=content.get(variable);
		int c=o!=null?((Integer)o).intValue():0;
		int c2=c+integer.intValue();
		if(c2==0) {
			if(c>0) content.remove(variable);
		} else {
			content.put(variable,new Integer(c2));
		}
		int d=c2-c;
		degree+=d;
	}

	int get(Variable variable) {
		Object o=content.get(variable);
		if(o!=null) return ((Integer)o).intValue();
		else return 0;
	}

	public String toString() {
		StringBuffer buffer=new StringBuffer();
		if(degree==0) buffer.append("1");
		myIterator it=content.entrySet().myIterator();
		for(int i=0;it.hasNext();i++) {
			myMap.Entry e=(myMap.Entry)it.next();
			if(i>0) buffer.append("*");
			Variable v=(Variable)e.getKey();
			int c=((Integer)e.getValue()).intValue();
			if(c==1) buffer.append(v);
			else {
				if(v instanceof Frac) {
					buffer.append("(").append(v).append(")");
				} else buffer.append(v);
				buffer.append("^").append(c);
			}
		}
		return buffer.toString();
	}

	public String toJava() {
		StringBuffer buffer=new StringBuffer();
		if(degree==0) buffer.append("JSCLDouble.valueOf(1)");
		myIterator it=content.entrySet().myIterator();
		for(int i=0;it.hasNext();i++) {
			myMap.Entry e=(myMap.Entry)it.next();
			if(i>0) buffer.append(".multiply(");
			Variable v=(Variable)e.getKey();
			int c=((Integer)e.getValue()).intValue();
			buffer.append(v.toJava());
			if(c==1);
			else buffer.append(".pow(").append(c).append(")");
			if(i>0) buffer.append(")");
		}
		return buffer.toString();
	}

	public String toMathML(Object data) {
		IndentedBuffer buffer=new IndentedBuffer();
		if(degree==0) buffer.append("<mn>1</mn>\n");
		myIterator it=content.entrySet().myIterator();
		for(int i=0;it.hasNext();i++) {
			myMap.Entry e=(myMap.Entry)it.next();
			Variable v=(Variable)e.getKey();
			int c=((Integer)e.getValue()).intValue();
			buffer.append(v.toMathML(new Integer(c)));
		}
		return buffer.toString();
	}

	protected Literal newinstance() {
		return new Literal();
	}
}
