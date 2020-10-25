/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package myjava.util;

/**
 * An object that maps keys to values.  A myMap cannot contain duplicate keys;
 * each key can myMap to at most one value.<p>
 *
 * This interface takes the place of the <tt>Dictionary</tt> class, which was
 * a totally abstract class rather than an interface.<p>
 *
 * The <tt>myMap</tt> interface provides three <i>myCollection views</i>, which
 * allow a myMap's contents to be viewed as a mySet of keys, myCollection of values,
 * or mySet of key-value mappings.  The <i>order</i> of a myMap is defined as
 * the order in which the iterators on the myMap's myCollection views return their
 * elements.  Some myMap implementations, like the <tt>myTreeMap</tt> class, make
 * specific guarantees as to their order; others, like the <tt>HashMap</tt>
 * class, do not.<p>
 *
 * Note: great care must be exercised if mutable objects are used as myMap keys.
 * The behavior of a myMap is not specified if the value of an object is changed
 * in a manner that affects equals comparisons while the object is a
 * key in the myMap.  A special case of this prohibition is that it is not
 * permissible for a myMap to contain itself as a key.  While it is permissible
 * for a myMap to contain itself as a value, extreme caution is advised: the
 * equals and hashCode methods are no longer well defined on a such a myMap.<p>
 *
 * All general-purpose myMap implementation classes should provide two
 * "standard" constructors: a void (no arguments) constructor which creates an
 * empty myMap, and a constructor with a single argument of type <tt>myMap</tt>,
 * which creates a new myMap with the same key-value mappings as its argument.
 * In effect, the latter constructor allows the user to copy any myMap,
 * producing an equivalent myMap of the desired class.  There is no way to
 * enforce this recommendation (as interfaces cannot contain constructors) but
 * all of the general-purpose myMap implementations in the SDK comply.
 *
 * @author  Josh Bloch
 * @version 1.33, 02/06/02
 * @see HashMap
 * @see myTreeMap
 * @see Hashtable
 * @see mySortedMap
 * @see myCollection
 * @see mySet
 * @since 1.2
 */
public interface myMap {
    // Query Operations

    /**
     * Returns the number of key-value mappings in this myMap.  If the
     * myMap contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this myMap.
     */
    int size();

    /**
     * Returns <tt>true</tt> if this myMap contains no key-value mappings.
     *
     * @return <tt>true</tt> if this myMap contains no key-value mappings.
     */
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this myMap contains a mapping for the specified
     * key.
     *
     * @param key key whose presence in this myMap is to be tested.
     * @return <tt>true</tt> if this myMap contains a mapping for the specified
     * key.
     * 
     * @throws ClassCastException if the key is of an inappropriate type for
     * 		  this myMap.
     * @throws NullPointerException if the key is <tt>null</tt> and this myMap
     *            does not not permit <tt>null</tt> keys.
     */
    boolean containsKey(Object key);

    /**
     * Returns <tt>true</tt> if this myMap maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this myMap contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the myMap size for most
     * implementations of the <tt>myMap</tt> interface.
     *
     * @param value value whose presence in this myMap is to be tested.
     * @return <tt>true</tt> if this myMap maps one or more keys to the
     *         specified value.
     */
    boolean containsValue(Object value);

    /**
     * Returns the value to which this myMap maps the specified key.  Returns
     * <tt>null</tt> if the myMap contains no mapping for this key.  A return
     * value of <tt>null</tt> does not <i>necessarily</i> indicate that the
     * myMap contains no mapping for the key; it's also possible that the myMap
     * explicitly maps the key to <tt>null</tt>.  The <tt>containsKey</tt>
     * operation may be used to distinguish these two cases.
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this myMap maps the specified key, or
     *	       <tt>null</tt> if the myMap contains no mapping for this key.
     * 
     * @throws ClassCastException if the key is of an inappropriate type for
     * 		  this myMap.
     * @throws NullPointerException key is <tt>null</tt> and this myMap does not
     *		  not permit <tt>null</tt> keys.
     * 
     * @see #containsKey(Object)
     */
    Object get(Object key);

    // Modification Operations

    /**
     * Associates the specified value with the specified key in this myMap
     * (optional operation).  If the myMap previously contained a mapping for
     * this key, the old value is replaced.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or <tt>null</tt>
     *	       if there was no mapping for key.  A <tt>null</tt> return can
     *	       also indicate that the myMap previously associated <tt>null</tt>
     *	       with the specified key, if the implementation supports
     *	       <tt>null</tt> values.
     * 
     * @throws UnsupportedOperationException if the <tt>put</tt> operation is
     *	          not supported by this myMap.
     * @throws ClassCastException if the class of the specified key or value
     * 	          prevents it from being stored in this myMap.
     * @throws IllegalArgumentException if some aspect of this key or value
     *	          prevents it from being stored in this myMap.
     * @throws NullPointerException this myMap does not permit <tt>null</tt>
     *            keys or values, and the specified key or value is
     *            <tt>null</tt>.
     */
    Object put(Object key, Object value);

    /**
     * Removes the mapping for this key from this myMap if present (optional
     * operation).
     *
     * @param key key whose mapping is to be removed from the myMap.
     * @return previous value associated with specified key, or <tt>null</tt>
     *	       if there was no mapping for key.  A <tt>null</tt> return can
     *	       also indicate that the myMap previously associated <tt>null</tt>
     *	       with the specified key, if the implementation supports
     *	       <tt>null</tt> values.
     * @throws UnsupportedOperationException if the <tt>remove</tt> method is
     *         not supported by this myMap.
     */
    Object remove(Object key);


    // Bulk Operations

    /**
     * Copies all of the mappings from the specified myMap to this myMap
     * (optional operation).  These mappings will replace any mappings that
     * this myMap had for any of the keys currently in the specified myMap.
     *
     * @param t Mappings to be stored in this myMap.
     * 
     * @throws UnsupportedOperationException if the <tt>putAll</tt> method is
     * 		  not supported by this myMap.
     * 
     * @throws ClassCastException if the class of a key or value in the
     * 	          specified myMap prevents it from being stored in this myMap.
     * 
     * @throws IllegalArgumentException some aspect of a key or value in the
     *	          specified myMap prevents it from being stored in this myMap.
     * 
     * @throws NullPointerException this myMap does not permit <tt>null</tt>
     *            keys or values, and the specified key or value is
     *            <tt>null</tt>.
     */
    void putAll(myMap t);

    /**
     * Removes all mappings from this myMap (optional operation).
     *
     * @throws UnsupportedOperationException clear is not supported by this
     * 		  myMap.
     */
    void clear();


    // Views

    /**
     * Returns a mySet view of the keys contained in this myMap.  The mySet is
     * backed by the myMap, so changes to the myMap are reflected in the mySet, and
     * vice-versa.  If the myMap is modified while an iteration over the mySet is
     * in progress, the results of the iteration are undefined.  The mySet
     * supports element removal, which removes the corresponding mapping from
     * the myMap, via the <tt>myIterator.remove</tt>, <tt>mySet.remove</tt>,
     * <tt>removeAll</tt> <tt>retainAll</tt>, and <tt>clear</tt> operations.
     * It does not support the add or <tt>addAll</tt> operations.
     *
     * @return a mySet view of the keys contained in this myMap.
     */
    public mySet keySet();

    /**
     * Returns a myCollection view of the values contained in this myMap.  The
     * myCollection is backed by the myMap, so changes to the myMap are reflected in
     * the myCollection, and vice-versa.  If the myMap is modified while an
     * iteration over the myCollection is in progress, the results of the
     * iteration are undefined.  The myCollection supports element removal,
     * which removes the corresponding mapping from the myMap, via the
     * <tt>myIterator.remove</tt>, <tt>myCollection.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt> and <tt>clear</tt> operations.
     * It does not support the add or <tt>addAll</tt> operations.
     *
     * @return a myCollection view of the values contained in this myMap.
     */
    public myCollection values();

    /**
     * Returns a mySet view of the mappings contained in this myMap.  Each element
     * in the returned mySet is a <tt>myMap.Entry</tt>.  The mySet is backed by the
     * myMap, so changes to the myMap are reflected in the mySet, and vice-versa.
     * If the myMap is modified while an iteration over the mySet is in progress,
     * the results of the iteration are undefined.  The mySet supports element
     * removal, which removes the corresponding mapping from the myMap, via the
     * <tt>myIterator.remove</tt>, <tt>mySet.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not support
     * the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a mySet view of the mappings contained in this myMap.
     */
    public mySet entrySet();

    /**
     * A myMap entry (key-value pair).  The <tt>myMap.entrySet</tt> method returns
     * a myCollection-view of the myMap, whose elements are of this class.  The
     * <i>only</i> way to obtain a reference to a myMap entry is from the
     * myIterator of this myCollection-view.  These <tt>myMap.Entry</tt> objects are
     * valid <i>only</i> for the duration of the iteration; more formally,
     * the behavior of a myMap entry is undefined if the backing myMap has been
     * modified after the entry was returned by the myIterator, except through
     * the myIterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a myMap entry returned by the myIterator.
     *
     * @see myMap#entrySet()
     * @since 1.2
     */
    public interface Entry {
    	/**
	 * Returns the key corresponding to this entry.
	 *
	 * @return the key corresponding to this entry.
	 */
	Object getKey();

    	/**
	 * Returns the value corresponding to this entry.  If the mapping
	 * has been removed from the backing myMap (by the myIterator's
	 * <tt>remove</tt> operation), the results of this call are undefined.
	 *
	 * @return the value corresponding to this entry.
	 */
	Object getValue();

    	/**
	 * Replaces the value corresponding to this entry with the specified
	 * value (optional operation).  (Writes through to the myMap.)  The
	 * behavior of this call is undefined if the mapping has already been
	 * removed from the myMap (by the myIterator's <tt>remove</tt> operation).
	 *
	 * @param value new value to be stored in this entry.
	 * @return old value corresponding to the entry.
         * 
	 * @throws UnsupportedOperationException if the <tt>put</tt> operation
	 *	      is not supported by the backing myMap.
	 * @throws ClassCastException if the class of the specified value
	 * 	      prevents it from being stored in the backing myMap.
	 * @throws    IllegalArgumentException if some aspect of this value
	 *	      prevents it from being stored in the backing myMap.
	 * @throws NullPointerException the backing myMap does not permit
	 *	      <tt>null</tt> values, and the specified value is
	 *	      <tt>null</tt>.
         */
	Object setValue(Object value);

	/**
	 * Compares the specified object with this entry for equality.
	 * Returns <tt>true</tt> if the given object is also a myMap entry and
	 * the two entries represent the same mapping.  More formally, two
	 * entries <tt>e1</tt> and <tt>e2</tt> represent the same mapping
	 * if<pre>
         *     (e1.getKey()==null ?
         *      e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &&
         *     (e1.getValue()==null ?
         *      e2.getValue()==null : e1.getValue().equals(e2.getValue()))
         * </pre>
	 * This ensures that the <tt>equals</tt> method works properly across
	 * different implementations of the <tt>myMap.Entry</tt> interface.
	 *
	 * @param o object to be compared for equality with this myMap entry.
	 * @return <tt>true</tt> if the specified object is equal to this myMap
	 *         entry.
         */
	boolean equals(Object o);

	/**
	 * Returns the hash code value for this myMap entry.  The hash code
	 * of a myMap entry <tt>e</tt> is defined to be: <pre>
	 *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
	 *     (e.getValue()==null ? 0 : e.getValue().hashCode())
         * </pre>
	 * This ensures that <tt>e1.equals(e2)</tt> implies that
	 * <tt>e1.hashCode()==e2.hashCode()</tt> for any two Entries
	 * <tt>e1</tt> and <tt>e2</tt>, as required by the general
	 * contract of <tt>Object.hashCode</tt>.
	 *
	 * @return the hash code value for this myMap entry.
	 * @see Object#hashCode()
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	int hashCode();
    }

    // Comparison and hashing

    /**
     * Compares the specified object with this myMap for equality.  Returns
     * <tt>true</tt> if the given object is also a myMap and the two Maps
     * represent the same mappings.  More formally, two maps <tt>t1</tt> and
     * <tt>t2</tt> represent the same mappings if
     * <tt>t1.entrySet().equals(t2.entrySet())</tt>.  This ensures that the
     * <tt>equals</tt> method works properly across different implementations
     * of the <tt>myMap</tt> interface.
     *
     * @param o object to be compared for equality with this myMap.
     * @return <tt>true</tt> if the specified object is equal to this myMap.
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this myMap.  The hash code of a myMap
     * is defined to be the sum of the hashCodes of each entry in the myMap's
     * entrySet view.  This ensures that <tt>t1.equals(t2)</tt> implies
     * that <tt>t1.hashCode()==t2.hashCode()</tt> for any two maps
     * <tt>t1</tt> and <tt>t2</tt>, as required by the general
     * contract of Object.hashCode.
     *
     * @return the hash code value for this myMap.
     * @see myMap.Entry#hashCode()
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    int hashCode();
}
