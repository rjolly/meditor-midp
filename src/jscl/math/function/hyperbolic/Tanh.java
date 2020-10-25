package jscl.math.function.hyperbolic;

import jscl.math.*;
import jscl.math.function.*;

public class Tanh extends Trigonometric {
	public Tanh(Generic generic) {
		super("tanh",new Generic[] {generic});
	}

	public Generic antiderivative(int n) throws NotIntegrableException {
		return new Log(
			JSCLInteger.valueOf(4).multiply(
				new Cosh(parameter[0]).evaluate()
			)
		).evaluate();
	}

	public Generic derivative(int n) {
		return JSCLInteger.valueOf(1).subtract(
			new Tanh(parameter[0]).evaluate().pow(2)
		);
	}

	public Generic evaluate() {
		if(parameter[0].signum()<0) {
			return new Tanh(parameter[0].negate()).evaluate().negate();
		} else if(parameter[0].signum()==0) {
			return JSCLInteger.valueOf(0);
		}
		return expressionValue();
	}

	public Generic evalelem() {
		return new Frac(
			new Sinh(parameter[0]).evalelem(),
			new Cosh(parameter[0]).evalelem()
		).evalelem();
	}

	public Generic evalsimp() {
		if(parameter[0].signum()<0) {
			return new Tanh(parameter[0].negate()).evaluate().negate();
		} else if(parameter[0].signum()==0) {
			return JSCLInteger.valueOf(0);
		}
		try {
			Variable v=parameter[0].variableValue();
			if(v instanceof Atanh) {
				Function f=(Function)v;
				return f.parameter[0];
			}
		} catch (NotVariableException e) {}
		return identity();
	}

	public Generic identity(Generic a, Generic b) {
		Generic ta=new Tanh(a).evalsimp();
		Generic tb=new Tanh(b).evalsimp();
		return new Frac(
			ta.add(tb),
			JSCLInteger.valueOf(1).add(
				ta.multiply(tb)
			)
		).evalsimp();
	}

	public Generic evalnum() {
		return ((NumericWrapper)parameter[0]).tanh();
	}

	protected Variable newinstance() {
		return new Tanh(null);
	}
}
