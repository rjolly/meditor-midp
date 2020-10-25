package jscl.math.operator.number;

import jscl.math.*;
import jscl.math.operator.*;
import jscl.text.*;

public class EulerPhi extends Operator {
	public EulerPhi(Generic integer) {
		super("eulerphi",new Generic[] {integer});
	}

	public Generic compute() {
		try {
			JSCLInteger en=parameter[0].integerValue();
			return en.phi();
		} catch (NotIntegerException e) {}
		return expressionValue();
	}

	protected String nameToMathML() {
		IndentedBuffer buffer=new IndentedBuffer();
		buffer.append("<mi>");
		buffer.append("&phi;");
		buffer.append("</mi>\n");
		return buffer.toString();
	}

	protected Variable newinstance() {
		return new EulerPhi(null);
	}
}