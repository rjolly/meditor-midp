package jscl.util;

import myjava.lang.*;
import myjava.util.*;

public class ArrayComparator implements myComparator {
	public static final myComparator myComparator=new ArrayComparator();

	private ArrayComparator() {}

	public int compare(Object o1, Object o2) {
		myComparable co1[]=(myComparable[])o1;
		myComparable co2[]=(myComparable[])o2;
		if(co1.length<co2.length) return -1;
		else if(co1.length>co2.length) return 1;
		for(int i=co1.length-1;i>=0;i--) {
			if(co1[i].compareTo(co2[i])<0) return -1;
			else if(co1[i].compareTo(co2[i])>0) return 1;
		}
		return 0;
	}
}
