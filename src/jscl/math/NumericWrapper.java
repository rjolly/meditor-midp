package jscl.math;

import jscl.math.function.*;
import jscl.text.*;

public final class NumericWrapper extends Generic {
	public static final Parser parser=DoubleParser.parser;
	final String content;

	public NumericWrapper(JSCLInteger integer) {
		content=null;
	}

	public NumericWrapper(Rational rational) {
		content=null;
	}

	public NumericWrapper(JSCLVector vector) {
		content=null;
	}

	public NumericWrapper(Matrix matrix) {
		content=null;
	}

	public NumericWrapper(Constant constant) {
		content=null;
	}

	NumericWrapper(String str) {
		content=str;
	}

	public Generic add(Generic generic) {
		return null;
	}

	public Generic subtract(Generic generic) {
		return null;
	}

	public Generic multiply(Generic generic) {
		return null;
	}

	public Generic divide(Generic generic) throws ArithmeticException {
		return null;
	}

	public Generic gcd(Generic generic) {
		return null;
	}

	public Generic gcd() {
		return null;
	}

	public Generic abs() {
		return null;
	}

	public Generic negate() {
		return null;
	}

	public int signum() {
		return 0;
	}

	public int degree() {
		return 0;
	}

	public Generic antiderivative(Variable variable) throws NotIntegrableException {
		return null;
	}

	public Generic derivative(Variable variable) {
		return null;
	}

	public Generic substitute(Variable variable, Generic generic) {
		return null;
	}

	public Generic expand() {
		return null;
	}

	public Generic factorize() {
		return null;
	}

	public Generic elementary() {
		return null;
	}

	public Generic simplify() {
		return null;
	}

	public Generic numeric() {
		return this;
	}

	public Generic valueof(Generic generic) {
		throw new ArithmeticException();
	}

	public Generic[] sumValue() {
		return null;
	}

	public Generic[] productValue() throws NotProductException {
		return null;
	}

	public Object[] powerValue() throws NotPowerException {
		return null;
	}

	public Expression expressionValue() throws NotExpressionException {
		throw new NotExpressionException();
	}

	public JSCLInteger integerValue() throws NotIntegerException {
		throw new NotIntegerException();
	}

	public Variable variableValue() throws NotVariableException {
		throw new NotVariableException();
	}

	public Variable[] variables() {
		return new Variable[0];
	}

	public boolean isPolynomial(Variable variable) {
		return true;
	}

	public boolean isConstant(Variable variable) {
		return true;
	}

	public Generic sgn() {
		return null;
	}

	public Generic log() {
		return null;
	}

	public Generic exp() {
		return null;
	}

	public Generic pow(Generic generic) {
		return null;
	}

	public Generic sqrt() {
		return null;
	}

	public static Generic root(int subscript, Generic parameter[]) {
		return null;
	}

	public Generic conjugate() {
		return null;
	}

	public Generic acos() {
		return null;
	}

	public Generic asin() {
		return null;
	}

	public Generic atan() {
		return null;
	}

	public Generic cos() {
		return null;
	}

	public Generic sin() {
		return null;
	}

	public Generic tan() {
		return null;
	}

	public Generic acosh() {
		return null;
	}

	public Generic asinh() {
		return null;
	}

	public Generic atanh() {
		return null;
	}

	public Generic cosh() {
		return null;
	}

	public Generic sinh() {
		return null;
	}

	public Generic tanh() {
		return null;
	}

	public int compareTo(Generic generic) {
		if(generic instanceof NumericWrapper) {
			return content.compareTo(((NumericWrapper)generic).content);
		} else {
			return compareTo(valueof(generic));
		}
	}

	public String toString() {
		return content.toString();
	}

	public String toJava() {
		return "JSCLDouble.valueOf("+content+")";
	}

	public String toMathML(Object data) {
		IndentedBuffer buffer=new IndentedBuffer();
		int exponent=data instanceof Integer?((Integer)data).intValue():1;
		if(exponent==1) {
			buffer.append(bodyToMathML());
		} else {
			buffer.append("<msup>\n");
			buffer.append(1,bodyToMathML());
			buffer.append(1,"<mn>").append(exponent).append("</mn>\n");
			buffer.append("</msup>\n");
		}
		return buffer.toString();
	}

	String bodyToMathML() {
		IndentedBuffer buffer=new IndentedBuffer();
		buffer.append("<mn>").append(content).append("</mn>\n");
		return buffer.toString();
	}

	protected Generic newinstance() {
		return null;
	}
}

class DoubleParser extends Parser {
	public static final Parser parser=new DoubleParser();

	private DoubleParser() {}

	public Object parse(String str, int pos[]) throws ParseException {
		int pos0=pos[0];
		String s;
		try {
			s=(String)Singularity.parser.parse(str,pos);
		} catch (ParseException e) {
			try {
				s=(String)FloatingPointLiteral.parser.parse(str,pos);
			} catch (ParseException e2) {
				throw e2;
			}
		}
		return new NumericWrapper(s);
	}
}

class Singularity extends Parser {
	public static final Parser parser=new Singularity();

	private Singularity() {}

	public Object parse(String str, int pos[]) throws ParseException {
		int pos0=pos[0];
		String s;
		try {
			s=(String)Constant.identifier.parse(str,pos);
			if(s.compareTo("NaN")==0);
			else if(s.compareTo("Infinity")==0);
			else {
				pos[0]=pos0;
				throw new ParseException();
			}
		} catch (ParseException e) {
			throw e;
		}
		return s;
	}
}

class FloatingPointLiteral extends Parser {
	public static final Parser parser=new FloatingPointLiteral();

	private FloatingPointLiteral() {}

	public Object parse(String str, int pos[]) throws ParseException {
		int pos0=pos[0];
		StringBuffer buffer=new StringBuffer();
		boolean digits=false;
		boolean point=false;
		try {
			String s=(String)JSCLInteger.digits.parse(str,pos);
			buffer.append(s);
			digits=true;
		} catch (ParseException e) {}
		try {
			Point.parser.parse(str,pos);
			buffer.append(".");
			point=true;
		} catch (ParseException e) {
			if(!digits) {
				pos[0]=pos0;
				throw e;
			}
		}
		try {
			String s=(String)JSCLInteger.digits.parse(str,pos);
			buffer.append(s);
		} catch (ParseException e) {
			if(!digits) {
				pos[0]=pos0;
				throw e;
			}
		}
		try {
			String s=(String)ExponentPart.parser.parse(str,pos);
			buffer.append(s);
		} catch (ParseException e) {
			if(!point) {
				pos[0]=pos0;
				throw e;
			}
		}
		return buffer.toString();
	}
}

class Point extends Parser {
	public static final Parser parser=new Point();

	private Point() {}

	public Object parse(String str, int pos[]) throws ParseException {
		int pos0=pos[0];
		skipWhitespaces(str,pos);
		if(pos[0]<str.length() && str.charAt(pos[0])=='.') {
			str.charAt(pos[0]++);
		} else {
			pos[0]=pos0;
			throw new ParseException();
		}
		return null;
	}
}

class ExponentPart extends Parser {
	public static final Parser parser=new ExponentPart();

	private ExponentPart() {}

	public Object parse(String str, int pos[]) throws ParseException {
		int pos0=pos[0];
		StringBuffer buffer=new StringBuffer();
		skipWhitespaces(str,pos);
		if(pos[0]<str.length() && (str.charAt(pos[0])=='e' || str.charAt(pos[0])=='E')) {
			char c=str.charAt(pos[0]++);
			buffer.append(c);
		} else {
			pos[0]=pos0;
			throw new ParseException();
		}
		try {
			String s=(String)SignedInteger.parser.parse(str,pos);
			buffer.append(s);
		} catch (ParseException e) {
			pos[0]=pos0;
			throw e;
		}
		return buffer.toString();
	}
}

class SignedInteger extends Parser {
	public static final Parser parser=new SignedInteger();

	private SignedInteger() {}

	public Object parse(String str, int pos[]) throws ParseException {
		int pos0=pos[0];
		StringBuffer buffer=new StringBuffer();
		skipWhitespaces(str,pos);
		if(pos[0]<str.length() && (str.charAt(pos[0])=='+' || str.charAt(pos[0])=='-')) {
			char c=str.charAt(pos[0]++);
			buffer.append(c);
		}
		try {
			int n=((Integer)Constant.integer.parse(str,pos)).intValue();
			buffer.append(n);
		} catch (ParseException e) {
			pos[0]=pos0;
			throw e;
		}
		return buffer.toString();
	}
}
