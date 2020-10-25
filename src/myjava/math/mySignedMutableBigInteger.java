/*
 * @(#)mySignedMutableBigInteger.myjava	1.6 00/02/02
 *
 * Copyright 1996-2000 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

package myjava.math;

/**
 * A class used to represent multiprecision integers that makes efficient
 * use of allocated space by allowing a number to occupy only part of
 * an array so that the arrays do not have to be reallocated as often.
 * When performing an operation with many iterations the array used to
 * hold a number is only increased when necessary and does not have to
 * be the same size as the number it represents. A mutable number allows
 * calculations to occur on the same number without having to create
 * a new number for every step of the calculation as occurs with
 * BigIntegers.
 *
 * Note that SignedMutableBigIntegers only support signed addition and
 * subtraction. All other operations occur as with MutableBigIntegers.
 * 
 * @see     myBigInteger
 * @version 1.6, 02/02/00
 * @author  Michael McCloskey
 * @since   1.3
 */

class mySignedMutableBigInteger extends myMutableBigInteger {

   /**
     * The sign of this myMutableBigInteger.
     */
    int sign = 1;

    // Constructors

    /**
     * The default constructor. An empty myMutableBigInteger is created with
     * a one word capacity.
     */
    mySignedMutableBigInteger() {
        super();
    }

    /**
     * Construct a new myMutableBigInteger with a magnitude specified by
     * the int val.
     */
    mySignedMutableBigInteger(int val) {
        super(val);
    }

    /**
     * Construct a new myMutableBigInteger with a magnitude equal to the
     * specified myMutableBigInteger.
     */
    mySignedMutableBigInteger(myMutableBigInteger val) {
        super(val);
    }

   // Arithmetic Operations

   /**
     * Signed addition built upon unsigned add and subtract.
     */
    void signedAdd(mySignedMutableBigInteger addend) {
        if (sign == addend.sign)
            add(addend);
        else
            sign = sign * subtract(addend);

    }

   /**
     * Signed addition built upon unsigned add and subtract.
     */
    void signedAdd(myMutableBigInteger addend) {
        if (sign == 1)
            add(addend);
        else
            sign = sign * subtract(addend);
        
    }

   /**
     * Signed subtraction built upon unsigned add and subtract.
     */
    void signedSubtract(mySignedMutableBigInteger addend) {
        if (sign == addend.sign)
            sign = sign * subtract(addend);
        else
            add(addend);
        
    }

   /**
     * Signed subtraction built upon unsigned add and subtract.
     */
    void signedSubtract(myMutableBigInteger addend) {
        if (sign == 1)
            sign = sign * subtract(addend);
        else
            add(addend);
        if (intLen == 0)
             sign = 1;
    }

    /**
     * Print out the first intLen ints of this myMutableBigInteger's value
     * array starting at offset.
     */
    public String toString() {
        myBigInteger b = new myBigInteger(this, sign);
        return
            b.toString();
    }

}
