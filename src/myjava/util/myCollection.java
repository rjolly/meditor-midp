/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package myjava.util;

/**
 * The root interface in the <i>myCollection hierarchy</i>.  A myCollection
 * represents a group of objects, known as its <i>elements</i>.  Some
 * collections allow duplicate elements and others do not.  Some are ordered
 * and others unordered.  The SDK does not provide any <i>direct</i>
 * implementations of this interface: it provides implementations of more
 * specific subinterfaces like <tt>mySet</tt> and <tt>List</tt>.  This interface
 * is typically used to pass collections around and manipulate them where
 * maximum generality is desired.<p>
 *
 * <i>Bags</i> or <i>multisets</i> (unordered collections that may contain
 * duplicate elements) should implement this interface directly.<p>
 *
 * All general-purpose <tt>myCollection</tt> implementation classes (which
 * typically implement <tt>myCollection</tt> indirectly through one of its
 * subinterfaces) should provide two "standard" constructors: a void (no
 * arguments) constructor, which creates an empty myCollection, and a
 * constructor with a single argument of type <tt>myCollection</tt>, which
 * creates a new myCollection with the same elements as its argument.  In
 * effect, the latter constructor allows the user to copy any myCollection,
 * producing an equivalent myCollection of the desired implementation type.
 * There is no way to enforce this convention (as interfaces cannot contain
 * constructors) but all of the general-purpose <tt>myCollection</tt>
 * implementations in the SDK comply.<p>
 *
 * @author  Josh Bloch
 * @version 1.32, 02/06/02
 * @see	    mySet
 * @see	    List
 * @see	    myMap
 * @see	    SortedSet
 * @see	    mySortedMap
 * @see	    HashSet
 * @see	    TreeSet
 * @see	    ArrayList
 * @see	    LinkedList
 * @see	    Vector
 * @see     Collections
 * @see	    Arrays
 * @see	    myAbstractCollection
 * @since   1.2
 */

public interface myCollection {
    // Query Operations

    /**
     * Returns the number of elements in this myCollection.  If this myCollection
     * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     * 
     * @return the number of elements in this myCollection
     */
    int size();

    /**
     * Returns <tt>true</tt> if this myCollection contains no elements.
     *
     * @return <tt>true</tt> if this myCollection contains no elements
     */
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this myCollection contains the specified
     * element.  More formally, returns <tt>true</tt> if and only if this
     * myCollection contains at least one element <tt>e</tt> such that
     * <tt>(o==null ? e==null : o.equals(e))</tt>.
     *
     * @param o element whose presence in this myCollection is to be tested.
     * @return <tt>true</tt> if this myCollection contains the specified
     *         element
     */
    boolean contains(Object o);

    /**
     * Returns an myIterator over the elements in this myCollection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this myCollection is an instance of some class that provides a
     * guarantee).
     * 
     * @return an <tt>myIterator</tt> over the elements in this myCollection
     */
    myIterator myIterator();

    /**
     * Returns an array containing all of the elements in this myCollection.  If
     * the myCollection makes any guarantees as to what order its elements are
     * returned by its myIterator, this method must return the elements in the
     * same order.<p>
     *
     * The returned array will be "safe" in that no references to it are
     * maintained by this myCollection.  (In other words, this method must
     * allocate a new array even if this myCollection is backed by an array).
     * The caller is thus free to modify the returned array.<p>
     *
     * This method acts as bridge between array-based and myCollection-based
     * APIs.
     *
     * @return an array containing all of the elements in this myCollection
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this myCollection
     * whose runtime type is that of the specified array.  If the myCollection
     * fits in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and the
     * size of this myCollection.<p>
     *
     * If this myCollection fits in the specified array with room to spare
     * (i.e., the array has more elements than this myCollection), the element
     * in the array immediately following the end of the myCollection is mySet to
     * <tt>null</tt>.  This is useful in determining the length of this
     * myCollection <i>only</i> if the caller knows that this myCollection does
     * not contain any <tt>null</tt> elements.)<p>
     *
     * If this myCollection makes any guarantees as to what order its elements
     * are returned by its myIterator, this method must return the elements in
     * the same order.<p>
     *
     * Like the <tt>toArray</tt> method, this method acts as bridge between
     * array-based and myCollection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs<p>
     *
     * Suppose <tt>l</tt> is a <tt>List</tt> known to contain only strings.
     * The following code can be used to dump the list into a newly allocated
     * array of <tt>String</tt>:
     *
     * <pre>
     *     String[] x = (String[]) v.toArray(new String[0]);
     * </pre><p>
     *
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the elements of this myCollection are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing the elements of this myCollection
     * 
     * @throws ArrayStoreException the runtime type of the specified array is
     *         not a supertype of the runtime type of every element in this
     *         myCollection.
     */
    
    Object[] toArray(Object a[]);

    // Modification Operations

    /**
     * Ensures that this myCollection contains the specified element (optional
     * operation).  Returns <tt>true</tt> if this myCollection changed as a
     * result of the call.  (Returns <tt>false</tt> if this myCollection does
     * not permit duplicates and already contains the specified element.)<p>
     *
     * Collections that support this operation may place limitations on what
     * elements may be added to this myCollection.  In particular, some
     * collections will refuse to add <tt>null</tt> elements, and others will
     * impose restrictions on the type of elements that may be added.
     * myCollection classes should clearly specify in their documentation any
     * restrictions on what elements may be added.<p>
     *
     * If a myCollection refuses to add a particular element for any reason
     * other than that it already contains the element, it <i>must</i> throw
     * an exception (rather than returning <tt>false</tt>).  This preserves
     * the invariant that a myCollection always contains the specified element
     * after this call returns.
     *
     * @param o element whose presence in this myCollection is to be ensured.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     *         call
     * 
     * @throws UnsupportedOperationException add is not supported by this
     *         myCollection.
     * @throws ClassCastException class of the specified element prevents it
     *         from being added to this myCollection.
     * @throws IllegalArgumentException some aspect of this element prevents
     *          it from being added to this myCollection.
     */
    boolean add(Object o);

    /**
     * Removes a single instance of the specified element from this
     * myCollection, if it is present (optional operation).  More formally,
     * removes an element <tt>e</tt> such that <tt>(o==null ?  e==null :
     * o.equals(e))</tt>, if this myCollection contains one or more such
     * elements.  Returns true if this myCollection contained the specified
     * element (or equivalently, if this myCollection changed as a result of the
     * call).
     *
     * @param o element to be removed from this myCollection, if present.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     *         call
     * 
     * @throws UnsupportedOperationException remove is not supported by this
     *         myCollection.
     */
    boolean remove(Object o);


    // Bulk Operations

    /**
     * Returns <tt>true</tt> if this myCollection contains all of the elements
     * in the specified myCollection.
     *
     * @param c myCollection to be checked for containment in this myCollection.
     * @return <tt>true</tt> if this myCollection contains all of the elements
     *	       in the specified myCollection
     * @see #contains(Object)
     */
    boolean containsAll(myCollection c);

    /**
     * Adds all of the elements in the specified myCollection to this myCollection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified myCollection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified myCollection is this myCollection, and this myCollection is
     * nonempty.)
     *
     * @param c elements to be inserted into this myCollection.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     *         call
     * 
     * @throws UnsupportedOperationException if this myCollection does not
     *         support the <tt>addAll</tt> method.
     * @throws ClassCastException if the class of an element of the specified
     * 	       myCollection prevents it from being added to this myCollection.
     * @throws IllegalArgumentException some aspect of an element of the
     *	       specified myCollection prevents it from being added to this
     *	       myCollection.
     * 
     * @see #add(Object)
     */
    boolean addAll(myCollection c);

    /**
     * 
     * Removes all this myCollection's elements that are also contained in the
     * specified myCollection (optional operation).  After this call returns,
     * this myCollection will contain no elements in common with the specified
     * myCollection.
     *
     * @param c elements to be removed from this myCollection.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     *         call
     * 
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> method
     * 	       is not supported by this myCollection.
     * 
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(myCollection c);

    /**
     * Retains only the elements in this myCollection that are contained in the
     * specified myCollection (optional operation).  In other words, removes from
     * this myCollection all of its elements that are not contained in the
     * specified myCollection.
     *
     * @param c elements to be retained in this myCollection.
     * @return <tt>true</tt> if this myCollection changed as a result of the
     *         call
     * 
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> method
     * 	       is not supported by this myCollection.
     * 
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean retainAll(myCollection c);

    /**
     * Removes all of the elements from this myCollection (optional operation).
     * This myCollection will be empty after this method returns unless it
     * throws an exception.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> method is
     *         not supported by this myCollection.
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified object with this myCollection for equality. <p>
     *
     * While the <tt>myCollection</tt> interface adds no stipulations to the
     * general contract for the <tt>Object.equals</tt>, programmers who
     * implement the <tt>myCollection</tt> interface "directly" (in other words,
     * create a class that is a <tt>myCollection</tt> but is not a <tt>mySet</tt>
     * or a <tt>List</tt>) must exercise care if they choose to override the
     * <tt>Object.equals</tt>.  It is not necessary to do so, and the simplest
     * course of action is to rely on <tt>Object</tt>'s implementation, but
     * the implementer may wish to implement a "value comparison" in place of
     * the default "reference comparison."  (The <tt>List</tt> and
     * <tt>mySet</tt> interfaces mandate such value comparisons.)<p>
     *
     * The general contract for the <tt>Object.equals</tt> method states that
     * equals must be symmetric (in other words, <tt>a.equals(b)</tt> if and
     * only if <tt>b.equals(a)</tt>).  The contracts for <tt>List.equals</tt>
     * and <tt>mySet.equals</tt> state that lists are only equal to other lists,
     * and sets to other sets.  Thus, a custom <tt>equals</tt> method for a
     * myCollection class that implements neither the <tt>List</tt> nor
     * <tt>mySet</tt> interface must return <tt>false</tt> when this myCollection
     * is compared to any list or mySet.  (By the same logic, it is not possible
     * to write a class that correctly implements both the <tt>mySet</tt> and
     * <tt>List</tt> interfaces.)
     *
     * @param o Object to be compared for equality with this myCollection.
     * @return <tt>true</tt> if the specified object is equal to this
     * myCollection
     * 
     * @see Object#equals(Object)
     * @see mySet#equals(Object)
     * @see List#equals(Object)
     */
    boolean equals(Object o);

    /**
     * 
     * Returns the hash code value for this myCollection.  While the
     * <tt>myCollection</tt> interface adds no stipulations to the general
     * contract for the <tt>Object.hashCode</tt> method, programmers should
     * take note that any class that overrides the <tt>Object.equals</tt>
     * method must also override the <tt>Object.hashCode</tt> method in order
     * to satisfy the general contract for the <tt>Object.hashCode</tt>method.
     * In particular, <tt>c1.equals(c2)</tt> implies that
     * <tt>c1.hashCode()==c2.hashCode()</tt>.
     *
     * @return the hash code value for this myCollection
     * 
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    int hashCode();
}
