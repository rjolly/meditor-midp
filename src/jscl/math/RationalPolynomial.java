package jscl.math;

import myjava.util.*;
import jscl.util.*;

class RationalPolynomial extends MultivariatePolynomial {
	RationalPolynomial(Variable unknown[], myComparator ordering) {
		super(unknown,ordering);
	}

	void mutableMultiply(Generic generic) {
		if(generic.compareTo(JSCLInteger.valueOf(1))==0) return;
		myIterator it=content.entrySet().myIterator();
		while(it.hasNext()) {
			myMap.Entry e=(myMap.Entry)it.next();
			e.setValue(((Generic)e.getValue()).multiply(generic));
		}
	}

	void mutableNormalize() {
		Generic gcd=gcd();
		if(gcd.signum()==0) return;
		if(gcd.signum()!=signum()) gcd=gcd.negate();
		mutableMultiply(((Rational)gcd).inverse());
	}

	public Polynomial s_polynomial(Polynomial polynomial) {
		RationalPolynomial q=(RationalPolynomial)polynomial;
		myMap.Entry e1=headTerm();
		Monomial m1=(Monomial)e1.getKey();
		Generic c1=(Generic)e1.getValue();
		myMap.Entry e2=q.headTerm();
		Monomial m2=(Monomial)e2.getKey();
		Generic c2=(Generic)e2.getValue();
		Monomial m=m1.gcd(m2);
		m1=m1.divide(m);
		m2=m2.divide(m);
		Generic c=c1.divide(c2);
		RationalPolynomial p=(RationalPolynomial)multiply(m2);
		p.mutableReduce(q,m1,c);
		p.mutableNormalize();
		return p;
	}

	public Polynomial reduce(Basis basis, boolean completely, boolean tail) {
		RationalPolynomial p=(RationalPolynomial)valueof(this);
		Monomial l=null;
		loop: while(p.signum()!=0) {
			myIterator it=p.subContent(l,completely,tail).entrySet().myIterator(true);
			while(it.hasNext()) {
				myMap.Entry e1=(myMap.Entry)it.next();
				Monomial m1=(Monomial)e1.getKey();
				Generic c1=(Generic)e1.getValue();
				myIterator it2=basis.content.values().myIterator();
				while(it2.hasNext()) {
					RationalPolynomial q=(RationalPolynomial)it2.next();
					myMap.Entry e2=q.headTerm();
					Monomial m2=(Monomial)e2.getKey();
					if(m1.multiple(m2)) {
						Generic c2=(Generic)e2.getValue();
						Monomial m=m1.divide(m2);
						Generic c=c1.divide(c2);
						p.mutableReduce(q,m,c);
						l=m1;
						continue loop;
					}
				}
			}
			break;
		}
		p.mutableNormalize();
		return p;
	}

	void mutableReduce(RationalPolynomial p, Monomial m, Generic c) {
		myIterator it=p.content.entrySet().myIterator();
		while(it.hasNext()) {
			myMap.Entry e=(myMap.Entry)it.next();
			put(
				((Monomial)e.getKey()).multiply(m),
				((Generic)e.getValue()).multiply(c).negate()
			);
		}
		sugar=Math.max(sugar,p.sugar+m.degree());
	}

	protected Generic uncoefficient(Generic generic) {
		return generic.integerValue();
	}

	protected Generic coefficient(Generic generic) {
		return Rational.valueOf((JSCLInteger)generic);
	}

	void put(Generic generic) {
		if(generic instanceof Rational) {
			put(monomial(new Literal()),(Rational)generic);
		} else super.put(generic);
	}

	protected Polynomial newinstance() {
		return new RationalPolynomial(unknown,ordering);
	}
}
