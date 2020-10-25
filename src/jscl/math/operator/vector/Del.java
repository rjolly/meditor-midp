package jscl.math.operator.vector;

import jscl.math.*;
import jscl.math.operator.*;
import jscl.text.*;

public class Del extends VectorOperator {
	public Del(Generic vector, Generic variable) {
		super("del",new Generic[] {vector,variable});
	}

	public Generic compute() {
		Variable variable[]=variables(parameter[1]);
		if(parameter[0] instanceof JSCLVector) {
			JSCLVector vector=(JSCLVector)parameter[0];
			return vector.del(variable);
		}
		return expressionValue();
	}

	protected String bodyToMathML() {
		IndentedBuffer buffer=new IndentedBuffer();
		buffer.append(operator("nabla"));
		buffer.append(parameter[0].toMathML(null));
		return buffer.toString();
	}

	protected Variable newinstance() {
		return new Del(null,null);
	}
}