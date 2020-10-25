package jscl.math;

import myjava.util.*;

class BooleanPolynomial extends ModularPolynomial {
	static final Integer ONE=new Integer(1);

	BooleanPolynomial(Variable unknown[], myComparator ordering) {
		super(unknown,ordering,2);
	}

	public Polynomial normalize() {
		return this;
	}

	public Polynomial s_polynomial(Polynomial polynomial) {
		BooleanPolynomial p2=(BooleanPolynomial)polynomial;
		Monomial m1=headMonomial();
		Monomial m2=p2.headMonomial();
		Monomial m=m1.gcd(m2);
		m1=m1.divide(m);
		m2=m2.divide(m);
		BooleanPolynomial p=(BooleanPolynomial)multiply(m2);
		p.mutableReduce(p2,m1);
		return p;
	}

	public Polynomial reduce(Basis basis, boolean completely, boolean tail) {
		BooleanPolynomial p=(BooleanPolynomial)valueof(this);
		Monomial l=null;
		loop: while(p.signum()!=0) {
			myIterator it=p.subContent(l,completely,tail).entrySet().myIterator(true);
			while(it.hasNext()) {
				myMap.Entry e1=(myMap.Entry)it.next();
				Monomial m1=(Monomial)e1.getKey();
				myIterator it2=basis.content.values().myIterator();
				while(it2.hasNext()) {
					BooleanPolynomial q=(BooleanPolynomial)it2.next();
					Monomial m2=q.headMonomial();
					if(m1.multiple(m2)) {
						Monomial m=m1.divide(m2);
						p.mutableReduce(q,m);
						l=m1;
						continue loop;
					}
				}
			}
			break;
		}
		return p;
	}

	void mutableReduce(BooleanPolynomial p, Monomial m) {
		myIterator it=p.content.entrySet().myIterator();
		while(it.hasNext()) {
			myMap.Entry e=(myMap.Entry)it.next();
			put(
				((Monomial)e.getKey()).multiply(m),
				((Integer)e.getValue()).intValue()
			);
		}
		sugar=Math.max(sugar,p.sugar+m.degree());
	}

	Monomial monomial(Literal literal) {
		return BooleanMonomial.valueOf(literal,unknown,ordering);
	}

	void put(Monomial monomial, int n) {
		if(n==0) return;
		Object o=content.get(monomial);
		if(o!=null) content.remove(monomial);
		else content.put(monomial,ONE);
		if(content.isEmpty()) degree=0;
		else degree=headMonomial().degree();
	}

	protected Polynomial newinstance() {
		return new BooleanPolynomial(unknown,ordering);
	}
}