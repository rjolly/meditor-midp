package jscl.math.function;

import jscl.math.*;

public abstract class ArcTrigonometric extends Function {
	public ArcTrigonometric(String name, Generic parameter[]) {
		super(name,parameter);
	}

	public Generic antiderivative(int n) throws NotIntegrableException {
		throw new NotIntegrableException();
	}

	public Generic evalsimp() {
		return evaluate();
	}
}
