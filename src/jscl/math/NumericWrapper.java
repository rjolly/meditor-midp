package jscl.math;

import jscl.math.function.*;
import jscl.text.*;

public final class NumericWrapper extends Arithmetic {
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

	public Arithmetic add(Arithmetic arithmetic) {
		return null;
	}

	public Arithmetic subtract(Arithmetic arithmetic) {
		return null;
	}

	public Arithmetic multiply(Arithmetic arithmetic) {
		return null;
	}

	public Arithmetic divide(Arithmetic arithmetic) throws ArithmeticException {
		return null;
	}

	public Arithmetic gcd(Arithmetic arithmetic) {
		return null;
	}

	public Arithmetic gcd() {
		return null;
	}

	public Arithmetic abs() {
		return null;
	}

	public Arithmetic negate() {
		return null;
	}

	public int signum() {
		return 0;
	}

	public int degree() {
		return 0;
	}

	public Arithmetic antiderivative(Variable variable) throws NotIntegrableException {
		return null;
	}

	public Arithmetic derivative(Variable variable) {
		return null;
	}

	public Arithmetic substitute(Variable variable, Arithmetic arithmetic) {
		return null;
	}

	public Arithmetic expand() {
		return null;
	}

	public Arithmetic factorize() {
		return null;
	}

	public Arithmetic elementary() {
		return null;
	}

	public Arithmetic simplify() {
		return null;
	}

	public Arithmetic numeric() {
		return this;
	}

	public Arithmetic valueof(Arithmetic arithmetic) {
		throw new ArithmeticException();
	}

	public Arithmetic[] sumValue() {
		return null;
	}

	public Arithmetic[] productValue() throws NotProductException {
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

	public Arithmetic sgn() {
		return null;
	}

	public Arithmetic log() {
		return null;
	}

	public Arithmetic exp() {
		return null;
	}

	public Arithmetic pow(Arithmetic arithmetic) {
		return null;
	}

	public Arithmetic sqrt() {
		return null;
	}

	public static Arithmetic root(int subscript, Arithmetic parameter[]) {
		return null;
	}

	public Arithmetic acos() {
		return null;
	}

	public Arithmetic asin() {
		return null;
	}

	public Arithmetic atan() {
		return null;
	}

	public Arithmetic cos() {
		return null;
	}

	public Arithmetic sin() {
		return null;
	}

	public Arithmetic tan() {
		return null;
	}

	public Arithmetic acosh() {
		return null;
	}

	public Arithmetic asinh() {
		return null;
	}

	public Arithmetic atanh() {
		return null;
	}

	public Arithmetic cosh() {
		return null;
	}

	public Arithmetic sinh() {
		return null;
	}

	public Arithmetic tanh() {
		return null;
	}

	public int compareTo(Object comparable) {
		if(comparable instanceof NumericWrapper) {
			return content.compareTo(((NumericWrapper)comparable).content);
		} else {
			return compareTo(valueof((Arithmetic)comparable));
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

	protected Arithmetic newinstance() {
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
