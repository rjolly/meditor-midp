/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package myjava.util;

/**
 * This class provides a skeletal implementation of the <tt>mySet</tt>
 * interface to minimize the effort required to implement this
 * interface. <p>
 *
 * The process of implementing a mySet by extending this class is identical
 * to that of implementing a myCollection by extending myAbstractCollection,
 * except that all of the methods and constructors in subclasses of this
 * class must obey the additional constraints imposed by the <tt>mySet</tt>
 * interface (for instance, the add method must not permit addition of
 * multiple intances of an object to a mySet).<p>
 *
 * Note that this class does not override any of the implementations from
 * the <tt>myAbstractCollection</tt> class.  It merely adds implementations
 * for <tt>equals</tt> and <tt>hashCode</tt>.
 *
 * @author  Josh Bloch
 * @version 1.15, 02/06/02
 * @see myCollection
 * @see myAbstractCollection
 * @see mySet
 * @since 1.2
 */

public abstract class myAbstractSet extends myAbstractCollection implements mySet {
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected myAbstractSet() {
    }

    // Comparison and hashing

    /**
     * Compares the specified object with this mySet for equality.  Returns
     * <tt>true</tt> if the given object is also a mySet, the two sets have
     * the same size, and every member of the given mySet is contained in
     * this mySet.  This ensures that the <tt>equals</tt> method works
     * properly across different implementations of the <tt>mySet</tt>
     * interface.<p>
     *
     * This implementation first checks if the specified object is this
     * mySet; if so it returns <tt>true</tt>.  Then, it checks if the
     * specified object is a mySet whose size is identical to the size of
     * this mySet; if not, it it returns false.  If so, it returns
     * <tt>containsAll((myCollection) o)</tt>.
     *
     * @param o Object to be compared for equality with this mySet.
     * @return <tt>true</tt> if the specified object is equal to this mySet.
     */
    public boolean equals(Object o) {
	if (o == this)
	    return true;

	if (!(o instanceof mySet))
	    return false;
	myCollection c = (myCollection) o;
	if (c.size() != size())
	    return false;
	return containsAll(c);
    }

    /**
     * Returns the hash code value for this mySet.  The hash code of a mySet is
     * defined to be the sum of the hash codes of the elements in the mySet.
     * This ensures that <tt>s1.equals(s2)</tt> implies that
     * <tt>s1.hashCode()==s2.hashCode()</tt> for any two sets <tt>s1</tt>
     * and <tt>s2</tt>, as required by the general contract of
     * Object.hashCode.<p>
     *
     * This implementation enumerates over the mySet, calling the
     * <tt>hashCode</tt> method on each element in the myCollection, and
     * adding up the results.
     *
     * @return the hash code value for this mySet.
     */
    public int hashCode() {
	int h = 0;
	myIterator i = myIterator();
	while (i.hasNext()) {
	    Object obj = i.next();
            if (obj != null)
                h += obj.hashCode();
        }
	return h;
    }

    /**
     * Removes from this mySet all of its elements that are contained in
     * the specified myCollection (optional operation).<p>
     *
     * This implementation determines which is the smaller of this mySet
     * and the specified myCollection, by invoking the <tt>size</tt>
     * method on each.  If this mySet has fewer elements, then the
     * implementation iterates over this mySet, checking each element
     * returned by the myIterator in turn to see if it is contained in
     * the specified myCollection.  If it is so contained, it is removed
     * from this mySet with the myIterator's <tt>remove</tt> method.  If
     * the specified myCollection has fewer elements, then the
     * implementation iterates over the specified myCollection, removing
     * from this mySet each element returned by the myIterator, using this
     * mySet's <tt>remove</tt> method.<p>
     *
     * Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> if the myIterator returned by the
     * <tt>myIterator</tt> method does not implement the <tt>remove</tt> method.
     *
     * @param c elements to be removed from this mySet.
     * @return <tt>true</tt> if this mySet changed as a result of the call.
     *
     * @throws    UnsupportedOperationException removeAll is not supported
     *            by this mySet.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean removeAll(myCollection c) {
        boolean modified = false;

        if (size() > c.size()) {
            for (myIterator i = c.myIterator(); i.hasNext(); )
                modified |= remove(i.next());
        } else {
            for (myIterator i = myIterator(); i.hasNext(); ) {
                if(c.contains(i.next())) {
                    i.remove();
                    modified = true;
                }
            }
        }
        return modified;
    }

}

