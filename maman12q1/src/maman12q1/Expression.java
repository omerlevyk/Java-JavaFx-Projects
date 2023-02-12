package maman12q1;




public abstract class Expression {
	
	String[] expression;
	
	public static void main(String[] args) {
		String [] ArrayList =  {"2", "3+5", "-4", "8-12", "1+2+3-4", "8+4+4-8"};
		double [] resultArrayList = new double [ArrayList.length];
		//
		System.out.println("cheack equals:\n");
		
		
		for (int i = 0; i < ArrayList.length; i++) {
			resultArrayList[i] = calculate(ArrayList[i]);
			System.out.printf("the result if %s is: %.1f%n", ArrayList[i], resultArrayList[i]);
			for (int j = i+1; j < ArrayList.length; j++) {
				if (i != j) {
					if (calculate(ArrayList[i]) == calculate(ArrayList[j])) {
						System.out.printf("%s is equal to %s%n", ArrayList[i], ArrayList[j]);
					}
				}
			}
		}
	}  
	
	public static double calculate(String exp) {
		
		int oprCounter = 0;
		for (int i = 1; i < exp.length(); i ++) {
			if (!isOperand(exp.charAt(i))) {
				oprCounter++;
			}
		}
		if (0 == oprCounter) {
			return Double.parseDouble(exp);
		}
		
	    double left = value(exp.charAt(0));
	    double right = 0;
	    double res = 0;
	    char opr = 0;
	    boolean isOpr = false;
	    for (int i = 1; i < exp.length(); i ++) {
	    	if (false == isOpr) {
	    		if (isOperand(exp.charAt(i))) {
		    		left *= 10;
		    		left += value(exp.charAt(i));
	    		} else {
	    			opr = exp.charAt(i);
		    		isOpr = true;
	    		}
	    	} else {
	    		if (isOperand(exp.charAt(i))) {
		    		right *= 10;
		    		right += value(exp.charAt(i));
	    		} else { // calculate!
	    			switch("" + opr) {
	    			case "+":
	    				res = left + right;
	    				break;
	    			case "-":
	    				res = left - right;
	    				break;
	    			}
	    			right = 0;
	    			left = 0;
	    		}
	    	}
	    }
		switch("" + opr) {
		case "+":
			res = left + right;
			break;
		case "-":
			res = left - right;
			break;
		}
		
	    return res; 
	}
	static boolean isOperand (char ch) {
		return (ch >= '0' && ch <= '9');
	}
	static double value(char ch) {
    	return (double)(ch - '0');
    }
	
	public  boolean equals(String other) {
		if (!(other instanceof String)) {
			return false;
		}
		return true;
	}
}

class AtomicExpression extends Expression {
	
	public AtomicExpression(String num) {
		
		this.expression = new String[num.length()];
		for (int i = 0; i < num.length(); i++) {
			expression[i] = "" + num.charAt(i);
		}
	}
	public String toString() {
		String tempStr = null;
		for (int i = 0; i < expression.length; i++) {
			tempStr += "" + expression[i];
		}
		return tempStr;
	}
}

abstract class CompoundExpression extends Expression {
	String[] complexexpression;
	Expression exp1;
	Expression exp2;
	public CompoundExpression (Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
		String strEq = exp1.toString() + "+" + exp2.toString();
		this.complexexpression = new String[strEq.length()];
		for (int i = 0; i < strEq.length(); i++) {
			complexexpression[i] = "" + strEq.charAt(i);
		}
	}
}

class AdditionExpression extends CompoundExpression {
	public AdditionExpression(Expression exp1, Expression exp2) {
		super(exp1, exp2);
		complexexpression[exp1.toString().length()] = "+";
	}
	
	public String toString() {
		String tempStr = null;
		int symbolLoc = exp1.toString().length();
		for (int i = 0; i < complexexpression.length; i++) {
			if (symbolLoc == i) {
				tempStr += " " + complexexpression[i] + " ";
			} else {
				tempStr += "" + complexexpression[i];
			}
		}
		return tempStr;
	}
}

class SubtractionExpression extends CompoundExpression {

	public SubtractionExpression(Expression exp1, Expression exp2) {
		super(exp1, exp2);
		complexexpression[exp1.toString().length()] = "-";
	}
	
	public String toString() {
		String tempStr = null;
		int symbolLoc = exp1.toString().length();
		for (int i = 0; i < complexexpression.length; i++) {
			if (symbolLoc == i) {
				tempStr += " " + complexexpression[i] + " ";
			} else {
				tempStr += "" + complexexpression[i];
			}
		}
		return tempStr;
	}
}


