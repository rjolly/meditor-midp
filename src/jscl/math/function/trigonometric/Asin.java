package jscl.math.function.trigonometric;

import jscl.math.*;
import jscl.math.function.*;

public class Asin extends ArcTrigonometric {
	public Asin(Generic generic) {
		super("asin",new Generic[] {generic});
	}

	public Generic derivative(int n) {
		return new Inv(
			new Sqrt(
				JSCLInteger.valueOf(1).subtract(parameter[0].pow(2))
			).evaluate()
		).evaluate();
	}

	public Generic evaluate() {
		if(parameter[0].signum()<0) {
			return new Asin(parameter[0].negate()).evaluate().negate();
		} else if(parameter[0].signum()==0) {
			return JSCLInteger.valueOf(0);
		}
		return expressionValue();
	}

	public Generic evalelem() {
		return Constant.i.multiply(
			new Log(
				new Root(
					new Generic[] {
						JSCLInteger.valueOf(-1),
						JSCLInteger.valueOf(2).multiply(Constant.i.multiply(parameter[0])),
						JSCLInteger.valueOf(1)
					},
					0
				).evalelem()
			).evalelem()
		);
	}

	public Generic evalnum() {
		return ((NumericWrapper)parameter[0]).asin();
	}

	protected Variable newinstance() {
		return new Asin(null);
	}
}
