/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package myjava.util;

/**
 * This class provides a skeletal implementation of the <tt>myCollection</tt>
 * interface, to minimize the effort required to implement this interface. <p>
 *
 * To implement an unmodifiable myCollection, the programmer needs only to
 * extend this class and provide implementations for the <tt>myIterator</tt> and
 * <tt>size</tt> methods.  (The myIterator returned by the <tt>myIterator</tt>
 * method must implement <tt>hasNext</tt> and <tt>next</tt>.)<p>
 *
 * To implement a modifiable myCollection, the programmer must additionally
 * override this class's <tt>add</tt> method (which otherwise throws an
 * <tt>UnsupportedOperationException</tt>), and the myIterator returned by the
 * <tt>myIterator</tt> method must additionally implement its <tt>remove</tt>
 * method.<p>
 *
 * The programmer should generally provide a void (no argument) and
 * <tt>myCollection</tt> constructor, as per the recommendation in the
 * <tt>myCollection</tt> interface specification.<p>
 *
 * The documentation for each non-abstract methods in this class describes its
 * implementation in detail.  Each of these methods may be overridden if
 * the myCollection being implemented admits a more efficient implementation.
 *
 * @author  Josh Bloch
 * @version 1.17, 02/06/02
 * @see myCollection
 * @since 1.2
 */

public abstract class myAbstractCollection implements myCollection {
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected myAbstractCollection() {
    }

    // Query Operations

    /**
     * Returns an myIterator over the elements contained in this myCollection.
     *
     * @return an myIterator over the elements contained in this myCollection.
     */
    public abstract myIterator myIterator();

    /**
     * Returns the number of elements in this myCollection.  If the myCollection
     * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this myCollection.
     */
    public abstract int size();

    /**
     * Returns <tt>true</tt> if this myCollection contains no elements.<p>
     *
     * This implementation returns <tt>size() == 0</tt>.
     *
     * @return <tt>true</tt> if this myCollection contains no elements.
     */
    public boolean isEmpty() {
	return size() == 0;
    }

    /**
     * Returns <tt>true</tt> if this myCollection contains the specified
     * element.  More formally, returns <tt>true</tt> if and only if this
     * myCollection contains at least one element <tt>e</tt> such that
     * <tt>(o==null ? e==null : o.equals(e))</tt>.<p>
     *
     * This implementation iterates over the elements in the myCollection,
     * checking each element in turn for equality with the specified element.
     *
     * @param o object to be checked for containment in this myCollection.
     * @return <tt>true</tt> if this myCollection contains the specified element.
     */
    public boolean contains(Object o) {
	myIterator e = myIterator();
	if (o==null) {
	    while (e.hasNext())
		if (e.next()==null)
		    return true;
	} else {
	    while (e.hasNext())
		if (o.equals(e.next()))
		    return true;
	}
	return false;
    }

    /**
     * Returns an array containing all of the elements in this myCollection.  If
     * the myCollection makes any guarantees as to what order its elements are
     * returned by its myIterator, this method must return the elements in the
     * same order.  The returned array will be "safe" in that no references to
     * it are maintained by the myCollection.  (In other words, this method must
     * allocate a new array even if the myCollection is backed by an Array).
     * The caller is thus free to modify the returned array.<p>
     *
     * This implementation allocates the array to be returned, and iterates
     * over the elements in the myCollection, storing each object reference in
     * the next consecutive element of the array, starting with element 0.
     *
     * @return an array containing all of the elements in this myCollection.
     */
    public Object[] toArray() {
	Object[] result = new Object[size()];
	myIterator e = myIterator();
	for (int i=0; e.hasNext(); i++)
	    result[i] = e.next();
	return result;
    }

    /**
     * Returns an array with a runtime type is that of the specified array and
     * that contains all of the elements in this myCollection.  If the
     * myCollection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this myCollection.<p>
     *
     * If the myCollection fits in the specified array with room to spare (i.e.,
     * the array has more elements than the myCollection), the element in the
     * array immediately following the end of the myCollection is mySet to
     * <tt>null</tt>.  This is useful in determining the length of the
     * myCollection <i>only</i> if the caller knows that the myCollection does
     * not contain any <tt>null</tt> elements.)<p>
     *
     * If this myCollection makes any guarantees as to what order its elements
     * are returned by its myIterator, this method must return the elements in
     * the same order. <p>
     *
     * This implementation checks if the array is large enough to contain the
     * myCollection; if not, it allocates a new array of the correct size and
     * type (using reflection).  Then, it iterates over the myCollection,
     * storing each object reference in the next consecutive element of the
     * array, starting with element 0.  If the array is larger than the
     * myCollection, a <tt>null</tt> is stored in the first location after the
     * end of the myCollection.
     *
     * @param  a the array into which the elements of the myCollection are to
     * 	       be stored, if it is big enough; otherwise, a new array of the
     * 	       same runtime type is allocated for this purpose.
     * @return an array containing the elements of the myCollection.
     * 
     * @throws NullPointerException if the specified array is <tt>null</tt>.
     * 
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in this
     *         myCollection.
     */
    public Object[] toArray(Object a[]) {
        int size = size();
//        if (a.length < size)
//            a = (Object[])myjava.lang.reflect.Array.newInstance(
//                                  a.getClass().getComponentType(), size);

        myIterator it=myIterator();
        for (int i=0; i<size; i++)
            a[i] = it.next();

        if (a.length > size)
            a[size] = null;

        return a;
    }

    // Modification Operations

    /**
     * Ensures that this myCollection contains the specified element (optional
     * operation).  Returns <tt>true</tt> if the myCollection changed as a
     * result of the call.  (Returns <tt>false</tt> if this myCollection does
     * not permit duplicates and already contains the specified element.)
     * Collections that support this operation may place limitations on what
     * elements may be added to the myCollection.  In particular, some
     * collections will refuse to add <tt>null</tt> elements, and others will
     * impose restrictions on the type of elements that may be added.
     * myCollection classes should clearly specify in their documentation any
     * restrictions on what elements may be added.<p>
     *
     * This implementation always throws an
     * <tt>UnsupportedOperationException</tt>.
     *
     * @param o element whose presence in this myCollection is to be ensured.
     * @return <tt>true</tt> if the myCollection changed as a result of the call.
     * 
     * @throws UnsupportedOperationException if the <tt>add</tt> method is not
     *		  supported by this myCollection.
     * 
     * @throws NullPointerException if this myCollection does not permit
     * 		  <tt>null</tt> elements, and the specified element is
     * 		  <tt>null</tt>.
     * 
     * @throws ClassCastException if the class of the specified element
     * 		  prevents it from being added to this myCollection.
     * 
     * @throws IllegalArgumentException if some aspect of this element
     *            prevents it from being added to this myCollection.
     */
    public boolean add(Object o) {
//	throw new UnsupportedOperationException();
	throw new RuntimeException();
    }

    /**
     * Removes a single instance of the specified element from this
     * myCollection, if it is present (optional operation).  More formally,
     * removes an element <tt>e</tt> such that <tt>(o==null ? e==null :
     * o.equals(e))</tt>, if the myCollection contains one or more such
     * elements.  Returns <tt>true</tt> if the myCollection contained the
     * specified element (or equivalently, if the myCollection changed as a
     * result of the call).<p>
     *
     * This implementation iterates over the myCollection looking for the
     * specified element.  If it finds the element, it removes the element
     * from the myCollection using the myIterator's remove method.<p>
     *
     * Note that this implementation throws an
     * <tt>UnsupportedOperationException</tt> if the myIterator returned by this
     * myCollection's myIterator method does not implement the <tt>remove</tt>
     * method.
     *
     * @param o element to be removed from this myCollection, if present.
     * @return <tt>true</tt> if the myCollection contained the specified
     *         element.
     * 
     * @throws UnsupportedOperationException if the <tt>remove</tt> method is
     * 		  not supported by this myCollection.
     */
    public boolean remove(Object o) {
	myIterator e = myIterator();
	if (o==null) {
	    while (e.hasNext()) {
		if (e.next()==null) {
		    e.remove();
		    return true;
		}
	    }
	} else {
	    while (e.hasNext()) {
		if (o.equals(e.next())) {
		    e.remove();
		    return true;
		}
	    }
	}
	return false;
    }


    // Bulk Operations

    /**
     * Returns <tt>true</tt> if this myCollection contains all of the elements
     * in the specified myCollection. <p>
     *
     * This implementation iterates over the specified myCollection, checking
     * each element returned by the myIterator in turn to see if it's
     * contained in this myCollection.  If all elements are so contained
     * <tt>true</tt> is returned, otherwise <tt>false</tt>.
     *
     * @param c myCollection to be checked for containment in this myCollection.
     * @return <tt>true</tt> if this myCollection contains all of the elements
     * 	       in the specified myCollection.
     * 
     * @see #contains(Object)
     */
    public boolean containsAll(myCollection c) {
	myIterator e = c.myIterator();
	while (e.hasNext())
	    if(!contains(e.next()))
		return false;

	return true;
    }

    /**
     * Adds all of the elements in the specified myCollection to this myCollection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified myCollection is modified while the operation is in
     * progress.  (This implies that the behavior of this call is undefined if
     * the specified myCollection is this myCollection, and this myCollection is
     * nonempty.) <p>
     *
     * This implementation iterates over the specified myCollection, and adds
     * each object returned by the myIterator to this myCollection, in turn.<p>
     *
     * Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> unless <tt>add</tt> is
     * overridden.
     *
     * @param c myCollection whose elements are to be added to this myCollection.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     * call.
     * @throws UnsupportedOperationException if the <tt>addAll</tt> method is
     * 		  not supported by this myCollection.
     * 
     * @see #add(Object)
     */
    public boolean addAll(myCollection c) {
	boolean modified = false;
	myIterator e = c.myIterator();
	while (e.hasNext()) {
	    if(add(e.next()))
		modified = true;
	}
	return modified;
    }

    /**
     * Removes from this myCollection all of its elements that are contained in
     * the specified myCollection (optional operation). <p>
     *
     * This implementation iterates over this myCollection, checking each
     * element returned by the myIterator in turn to see if it's contained
     * in the specified myCollection.  If it's so contained, it's removed from
     * this myCollection with the myIterator's <tt>remove</tt> method.<p>
     *
     * Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> if the myIterator returned by the
     * <tt>myIterator</tt> method does not implement the <tt>remove</tt> method.
     *
     * @param c elements to be removed from this myCollection.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     * call.
     * 
     * @throws    UnsupportedOperationException removeAll is not supported
     * 		  by this myCollection.
     * 
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean removeAll(myCollection c) {
	boolean modified = false;
	myIterator e = myIterator();
	while (e.hasNext()) {
	    if(c.contains(e.next())) {
		e.remove();
		modified = true;
	    }
	}
	return modified;
    }

    /**
     * Retains only the elements in this myCollection that are contained in the
     * specified myCollection (optional operation).  In other words, removes
     * from this myCollection all of its elements that are not contained in the
     * specified myCollection. <p>
     *
     * This implementation iterates over this myCollection, checking each
     * element returned by the myIterator in turn to see if it's contained
     * in the specified myCollection.  If it's not so contained, it's removed
     * from this myCollection with the myIterator's <tt>remove</tt> method.<p>
     *
     * Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> if the myIterator returned by the
     * <tt>myIterator</tt> method does not implement the remove method.
     *
     * @param c elements to be retained in this myCollection.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     *         call.
     * 
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> method
     * 		  is not supported by this myCollection.
     * 
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean retainAll(myCollection c) {
	boolean modified = false;
	myIterator e = myIterator();
	while (e.hasNext()) {
	    if(!c.contains(e.next())) {
		e.remove();
		modified = true;
	    }
	}
	return modified;
    }

    /**
     * Removes all of the elements from this myCollection (optional operation).
     * The myCollection will be empty after this call returns (unless it throws
     * an exception).<p>
     *
     * This implementation iterates over this myCollection, removing each
     * element using the <tt>myIterator.remove</tt> operation.  Most
     * implementations will probably choose to override this method for
     * efficiency.<p>
     *
     * Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> if the myIterator returned by this
     * myCollection's <tt>myIterator</tt> method does not implement the
     * <tt>remove</tt> method.
     *
     * @throws UnsupportedOperationException if the <tt>remove</tt> method is
     * 		  not supported by this myCollection.
     */
    public void clear() {
	myIterator e = myIterator();
	while (e.hasNext()) {
	    e.next();
	    e.remove();
	}
    }


    //  String conversion

    /**
     * Returns a string representation of this myCollection.  The string
     * representation consists of a list of the myCollection's elements in the
     * order they are returned by its myIterator, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space).  Elements are converted to strings as
     * by <tt>String.valueOf(Object)</tt>.<p>
     *
     * This implementation creates an empty string buffer, appends a left
     * square bracket, and iterates over the myCollection appending the string
     * representation of each element in turn.  After appending each element
     * except the last, the string <tt>", "</tt> is appended.  Finally a right
     * bracket is appended.  A string is obtained from the string buffer, and
     * returned.
     * 
     * @return a string representation of this myCollection.
     */
    public String toString() {
	StringBuffer buf = new StringBuffer();
	myIterator e = myIterator();
	buf.append("[");
	int maxIndex = size() - 1;
	for (int i = 0; i <= maxIndex; i++) {
	    buf.append(String.valueOf(e.next()));
	    if (i < maxIndex)
		buf.append(", ");
	}
	buf.append("]");
	return buf.toString();
    }
}
