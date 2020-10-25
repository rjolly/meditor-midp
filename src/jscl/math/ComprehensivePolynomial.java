package jscl.math;

import myjava.util.*;

abstract class ComprehensivePolynomial extends MultivariatePolynomial {
	ComprehensivePolynomial(Variable unknown[], myComparator ordering) {
		super(unknown,ordering);
	}

	protected Generic uncoefficient(Generic generic) {
		return generic;
	}

	protected Generic coefficient(Generic generic) {
		return generic;
	}
}
