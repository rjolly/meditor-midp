package jscl.math.function.hyperbolic;

import jscl.math.*;
import jscl.math.function.*;

public class Acosh extends ArcTrigonometric {
	public Acosh(Generic generic) {
		super("acosh",new Generic[] {generic});
	}

	public Generic derivative(int n) {
		return new Inv(
			new Sqrt(
				parameter[0].pow(2).subtract(
					JSCLInteger.valueOf(1)
				)
			).evaluate()
		).evaluate();
	}

	public Generic evaluate() {
		if(parameter[0].signum()==0) {
			return JSCLInteger.valueOf(0);
		}
		return expressionValue();
	}

	public Generic evalelem() {
		return new Log(
			new Root(
				new Generic[] {
					JSCLInteger.valueOf(-1),
					JSCLInteger.valueOf(2).multiply(parameter[0]),
					JSCLInteger.valueOf(-1)
				},
				0
			).evalelem()
		).evalelem();
	}

	public Generic evalnum() {
		return ((NumericWrapper)parameter[0]).acosh();
	}

	protected Variable newinstance() {
		return new Acosh(null);
	}
}