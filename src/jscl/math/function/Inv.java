package jscl.math.function;

import jscl.math.*;

public class Inv extends Frac {
	public Inv(Generic generic) {
		super(JSCLInteger.valueOf(1),generic);
	}

	public Generic evaluate() {
		try {
			return JSCLInteger.valueOf(1).divide(parameter());
		} catch (NotDivisibleException e) {}
		return expressionValue();
	}

	public Generic parameter() {
		return parameter[1];
	}

	protected Variable newinstance() {
		return new Inv(null);
	}
}
