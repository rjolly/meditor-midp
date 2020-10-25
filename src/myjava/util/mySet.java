/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package myjava.util;

/**
 * A myCollection that contains no duplicate elements.  More formally, sets
 * contain no pair of elements <code>e1</code> and <code>e2</code> such that
 * <code>e1.equals(e2)</code>, and at most one null element.  As implied by
 * its name, this interface models the mathematical <i>mySet</i> abstraction.<p>
 *
 * The <tt>mySet</tt> interface places additional stipulations, beyond those
 * inherited from the <tt>myCollection</tt> interface, on the contracts of all
 * constructors and on the contracts of the <tt>add</tt>, <tt>equals</tt> and
 * <tt>hashCode</tt> methods.  Declarations for other inherited methods are
 * also included here for convenience.  (The specifications accompanying these
 * declarations have been tailored to the <tt>mySet</tt> interface, but they do
 * not contain any additional stipulations.)<p>
 *
 * The additional stipulation on constructors is, not surprisingly,
 * that all constructors must create a mySet that contains no duplicate elements
 * (as defined above).<p>
 *
 * Note: Great care must be exercised if mutable objects are used as mySet
 * elements.  The behavior of a mySet is not specified if the value of an object
 * is changed in a manner that affects equals comparisons while the object is
 * an element in the mySet.  A special case of this prohibition is that it is
 * not permissible for a mySet to contain itself as an element.
 *
 * @author  Josh Bloch
 * @version 1.24, 02/06/02
 * @see myCollection
 * @see List
 * @see SortedSet
 * @see HashSet
 * @see TreeSet
 * @see myAbstractSet
 * @see Collections#singleton(myjava.lang.Object)
 * @see Collections#EMPTY_SET
 * @since 1.2
 */

public interface mySet extends myCollection {
    // Query Operations

    /**
     * Returns the number of elements in this mySet (its cardinality).  If this
     * mySet contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this mySet (its cardinality).
     */
    int size();

    /**
     * Returns <tt>true</tt> if this mySet contains no elements.
     *
     * @return <tt>true</tt> if this mySet contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this mySet contains the specified element.  More
     * formally, returns <tt>true</tt> if and only if this mySet contains an
     * element <code>e</code> such that <code>(o==null ? e==null :
     * o.equals(e))</code>.
     *
     * @param o element whose presence in this mySet is to be tested.
     * @return <tt>true</tt> if this mySet contains the specified element.
     */
    boolean contains(Object o);

    /**
     * Returns an myIterator over the elements in this mySet.  The elements are
     * returned in no particular order (unless this mySet is an instance of some
     * class that provides a guarantee).
     *
     * @return an myIterator over the elements in this mySet.
     */
    myIterator myIterator();

    /**
     * Returns an array containing all of the elements in this mySet.
     * Obeys the general contract of the <tt>myCollection.toArray</tt> method.
     *
     * @return an array containing all of the elements in this mySet.
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this mySet whose
     * runtime type is that of the specified array.  Obeys the general
     * contract of the <tt>myCollection.toArray(Object[])</tt> method.
     *
     * @param a the array into which the elements of this mySet are to
     *		be stored, if it is big enough; otherwise, a new array of the
     * 		same runtime type is allocated for this purpose.
     * @return an array containing the elements of this mySet.
     * @throws    ArrayStoreException the runtime type of a is not a supertype
     * of the runtime type of every element in this mySet.
     */
    Object[] toArray(Object a[]);


    // Modification Operations

    /**
     * Adds the specified element to this mySet if it is not already present
     * (optional operation).  More formally, adds the specified element,
     * <code>o</code>, to this mySet if this mySet contains no element
     * <code>e</code> such that <code>(o==null ? e==null :
     * o.equals(e))</code>.  If this mySet already contains the specified
     * element, the call leaves this mySet unchanged and returns <tt>false</tt>.
     * In combination with the restriction on constructors, this ensures that
     * sets never contain duplicate elements.<p>
     *
     * The stipulation above does not imply that sets must accept all
     * elements; sets may refuse to add any particular element, including
     * <tt>null</tt>, and throwing an exception, as described in the
     * specification for <tt>myCollection.add</tt>.  Individual mySet
     * implementations should clearly document any restrictions on the the
     * elements that they may contain.
     *
     * @param o element to be added to this mySet.
     * @return <tt>true</tt> if this mySet did not already contain the specified
     *         element.
     * 
     * @throws UnsupportedOperationException if the <tt>add</tt> method is not
     * 	       supported by this mySet.
     * @throws ClassCastException if the class of the specified element
     * 	       prevents it from being added to this mySet.
     * @throws IllegalArgumentException if some aspect of this element
     *         prevents it from being added to this mySet.
     */
    boolean add(Object o);


    /**
     * Removes the specified element from this mySet if it is present (optional
     * operation).  More formally, removes an element <code>e</code> such that
     * <code>(o==null ?  e==null : o.equals(e))</code>, if the mySet contains
     * such an element.  Returns <tt>true</tt> if the mySet contained the
     * specified element (or equivalently, if the mySet changed as a result of
     * the call).  (The mySet will not contain the specified element once the
     * call returns.)
     *
     * @param o object to be removed from this mySet, if present.
     * @return true if the mySet contained the specified element.
     * @throws UnsupportedOperationException if the <tt>remove</tt> method is
     *         not supported by this mySet.
     */
    boolean remove(Object o);


    // Bulk Operations

    /**
     * Returns <tt>true</tt> if this mySet contains all of the elements of the
     * specified myCollection.  If the specified myCollection is also a mySet, this
     * method returns <tt>true</tt> if it is a <i>subset</i> of this mySet.
     *
     * @param c myCollection to be checked for containment in this mySet.
     * @return <tt>true</tt> if this mySet contains all of the elements of the
     * 	       specified myCollection.
     */
    boolean containsAll(myCollection c);

    /**
     * Adds all of the elements in the specified myCollection to this mySet if
     * they're not already present (optional operation).  If the specified
     * myCollection is also a mySet, the <tt>addAll</tt> operation effectively
     * modifies this mySet so that its value is the <i>union</i> of the two
     * sets.  The behavior of this operation is unspecified if the specified
     * myCollection is modified while the operation is in progress.
     *
     * @param c myCollection whose elements are to be added to this mySet.
     * @return <tt>true</tt> if this mySet changed as a result of the call.
     * 
     * @throws UnsupportedOperationException if the <tt>addAll</tt> method is
     * 		  not supported by this mySet.
     * @throws ClassCastException if the class of some element of the
     * 		  specified myCollection prevents it from being added to this
     * 		  mySet.
     * @throws IllegalArgumentException if some aspect of some element of the
     *		  specified myCollection prevents it from being added to this
     *		  mySet.
     * @see #add(Object)
     */
    boolean addAll(myCollection c);

    /**
     * Retains only the elements in this mySet that are contained in the
     * specified myCollection (optional operation).  In other words, removes
     * from this mySet all of its elements that are not contained in the
     * specified myCollection.  If the specified myCollection is also a mySet, this
     * operation effectively modifies this mySet so that its value is the
     * <i>intersection</i> of the two sets.
     *
     * @param c myCollection that defines which elements this mySet will retain.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     *         call.
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> method
     * 		  is not supported by this myCollection.
     * 
     * @see #remove(Object)
     */
    boolean retainAll(myCollection c);


    /**
     * Removes from this mySet all of its elements that are contained in the
     * specified myCollection (optional operation).  If the specified
     * myCollection is also a mySet, this operation effectively modifies this
     * mySet so that its value is the <i>asymmetric mySet difference</i> of
     * the two sets.
     *
     * @param c myCollection that defines which elements will be removed from
     *          this mySet.
     * @return <tt>true</tt> if this mySet changed as a result of the call.
     * 
     * @throws UnsupportedOperationException if the <tt>removeAll</tt>
     * 		  method is not supported by this myCollection.
     * 
     * @see #remove(Object) */
    boolean removeAll(myCollection c);

    /**
     * Removes all of the elements from this mySet (optional operation).
     * This mySet will be empty after this call returns (unless it throws an
     * exception).
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> method
     * 		  is not supported by this mySet.
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified object with this mySet for equality.  Returns
     * <tt>true</tt> if the specified object is also a mySet, the two sets
     * have the same size, and every member of the specified mySet is
     * contained in this mySet (or equivalently, every member of this mySet is
     * contained in the specified mySet).  This definition ensures that the
     * equals method works properly across different implementations of the
     * mySet interface.
     *
     * @param o Object to be compared for equality with this mySet.
     * @return <tt>true</tt> if the specified Object is equal to this mySet.
     */
    boolean equals(Object o);

    /**
     * 
     * Returns the hash code value for this mySet.  The hash code of a mySet is
     * defined to be the sum of the hash codes of the elements in the mySet,
     * where the hashcode of a <tt>null</tt> element is defined to be zero.
     * This ensures that <code>s1.equals(s2)</code> implies that
     * <code>s1.hashCode()==s2.hashCode()</code> for any two sets
     * <code>s1</code> and <code>s2</code>, as required by the general
     * contract of the <tt>Object.hashCode</tt> method.
     *
     * @return the hash code value for this mySet.
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @see mySet#equals(Object)
     */
    int hashCode();
}
