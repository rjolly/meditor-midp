/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package myjava.util;

/**
 * A myMap that further guarantees that it will be in ascending key order,
 * sorted according to the <i>natural ordering</i> of its keys (see the
 * <tt>myComparable</tt> interface), or by a myComparator provided at sorted myMap
 * creation time.  This order is reflected when iterating over the sorted
 * myMap's myCollection views (returned by the <tt>entrySet</tt>, <tt>keySet</tt>
 * and <tt>values</tt> methods).  Several additional operations are provided
 * to take advantage of the ordering.  (This interface is the myMap analogue of
 * the <tt>SortedSet</tt> interface.)<p>
 *
 * All keys inserted into a sorted myMap must implement the <tt>myComparable</tt>
 * interface (or be accepted by the specified myComparator).  Furthermore, all
 * such keys must be <i>mutually myComparable</i>: <tt>k1.compareTo(k2)</tt> (or
 * <tt>myComparator.compare(k1, k2)</tt>) must not throw a
 * <tt>ClassCastException</tt> for any elements <tt>k1</tt> and <tt>k2</tt> in
 * the sorted myMap.  Attempts to violate this restriction will cause the
 * offending method or constructor invocation to throw a
 * <tt>ClassCastException</tt>.<p>
 *
 * Note that the ordering maintained by a sorted myMap (whether or not an
 * explicit myComparator is provided) must be <i>consistent with equals</i> if
 * the sorted myMap is to correctly implement the <tt>myMap</tt> interface.  (See
 * the <tt>myComparable</tt> interface or <tt>myComparator</tt> interface for a
 * precise definition of <i>consistent with equals</i>.)  This is so because
 * the <tt>myMap</tt> interface is defined in terms of the <tt>equals</tt>
 * operation, but a sorted myMap performs all key comparisons using its
 * <tt>compareTo</tt> (or <tt>compare</tt>) method, so two keys that are
 * deemed equal by this method are, from the standpoint of the sorted myMap,
 * equal.  The behavior of a tree myMap <i>is</i> well-defined even if its
 * ordering is inconsistent with equals; it just fails to obey the general
 * contract of the <tt>myMap</tt> interface.<p>
 *
 * All general-purpose sorted myMap implementation classes should provide four
 * "standard" constructors: 1) A void (no arguments) constructor, which
 * creates an empty sorted myMap sorted according to the <i>natural order</i> of
 * its keys.  2) A constructor with a single argument of type
 * <tt>myComparator</tt>, which creates an empty sorted myMap sorted according to
 * the specified myComparator.  3) A constructor with a single argument of type
 * <tt>myMap</tt>, which creates a new myMap with the same key-value mappings as
 * its argument, sorted according to the keys' natural ordering.  4) A
 * constructor with a single argument of type sorted myMap, which creates a new
 * sorted myMap with the same key-value mappings and the same ordering as the
 * input sorted myMap.  There is no way to enforce this recommendation (as
 * interfaces cannot contain constructors) but the SDK implementation
 * (myTreeMap) complies.
 *
 * @author  Josh Bloch
 * @version 1.13, 02/06/02
 * @see myMap
 * @see myTreeMap
 * @see SortedSet
 * @see myComparator
 * @see myComparable
 * @see myCollection
 * @see ClassCastException
 * @since 1.2
 */

public interface mySortedMap extends myMap {
    /**
     * Returns the myComparator associated with this sorted myMap, or
     * <tt>null</tt> if it uses its keys' natural ordering.
     *
     * @return the myComparator associated with this sorted myMap, or
     * 	       <tt>null</tt> if it uses its keys' natural ordering.
     */
    myComparator myComparator();

    /**
     * Returns a view of the portion of this sorted myMap whose keys range from
     * <tt>fromKey</tt>, inclusive, to <tt>toKey</tt>, exclusive.  (If
     * <tt>fromKey</tt> and <tt>toKey</tt> are equal, the returned sorted myMap
     * is empty.)  The returned sorted myMap is backed by this sorted myMap, so
     * changes in the returned sorted myMap are reflected in this sorted myMap,
     * and vice-versa.  The returned myMap supports all optional myMap operations
     * that this sorted myMap supports.<p>
     *
     * The myMap returned by this method will throw an
     * <tt>IllegalArgumentException</tt> if the user attempts to insert a key
     * outside the specified range.<p>
     *
     * Note: this method always returns a <i>half-open range</i> (which
     * includes its low endpoint but not its high endpoint).  If you need a
     * <i>closed range</i> (which includes both endpoints), and the key type
     * allows for calculation of the successor a given key, merely request the
     * subrange from <tt>lowEndpoint</tt> to <tt>successor(highEndpoint)</tt>.
     * For example, suppose that <tt>m</tt> is a myMap whose keys are strings.
     * The following idiom obtains a view containing all of the key-value
     * mappings in <tt>m</tt> whose keys are between <tt>low</tt> and
     * <tt>high</tt>, inclusive:
     * 
     * 	    <pre>    myMap sub = m.subMap(low, high+"\0");</pre>
     * 
     * A similarly technique can be used to generate an <i>open range</i>
     * (which contains neither endpoint).  The following idiom obtains a
     * view containing  all of the key-value mappings in <tt>m</tt> whose keys
     * are between <tt>low</tt> and <tt>high</tt>, exclusive:
     * 
     * 	    <pre>    myMap sub = m.subMap(low+"\0", high);</pre>
     *
     * @param fromKey low endpoint (inclusive) of the subMap.
     * @param toKey high endpoint (exclusive) of the subMap.
     * @return a view of the specified range within this sorted myMap.
     * 
     * @throws ClassCastException if <tt>fromKey</tt> and <tt>toKey</tt>
     *         cannot be compared to one another using this myMap's myComparator
     *         (or, if the myMap has no myComparator, using natural ordering).
     *         Implementations may, but are not required to, throw this
     *	       exception if <tt>fromKey</tt> or <tt>toKey</tt>
     *         cannot be compared to keys currently in the myMap.
     * @throws IllegalArgumentException if <tt>fromKey</tt> is greater than
     *         <tt>toKey</tt>; or if this myMap is itself a subMap, headMap,
     *         or tailMap, and <tt>fromKey</tt> or <tt>toKey</tt> are not
     *         within the specified range of the subMap, headMap, or tailMap.
     * @throws NullPointerException if <tt>fromKey</tt> or <tt>toKey</tt> is
     *	       <tt>null</tt> and this sorted myMap does not tolerate
     *	       <tt>null</tt> keys.
     */
    mySortedMap subMap(Object fromKey, Object toKey);

    /**
     * Returns a view of the portion of this sorted myMap whose keys are
     * strictly less than toKey.  The returned sorted myMap is backed by this
     * sorted myMap, so changes in the returned sorted myMap are reflected in this
     * sorted myMap, and vice-versa.  The returned myMap supports all optional myMap
     * operations that this sorted myMap supports.<p>
     *
     * The myMap returned by this method will throw an IllegalArgumentException
     * if the user attempts to insert a key outside the specified range.<p>
     *
     * Note: this method always returns a view that does not contain its
     * (high) endpoint.  If you need a view that does contain this endpoint,
     * and the key type allows for calculation of the successor a given
     * key, merely request a headMap bounded by successor(highEndpoint).
     * For example, suppose that suppose that <tt>m</tt> is a myMap whose keys
     * are strings.  The following idiom obtains a view containing all of the
     * key-value mappings in <tt>m</tt> whose keys are less than or equal to
     * <tt>high</tt>:
     * 
     * 	    <pre>    myMap head = m.headMap(high+"\0");</pre>
     *
     * @param toKey high endpoint (exclusive) of the subMap.
     * @return a view of the specified initial range of this sorted myMap.
     * @throws ClassCastException if <tt>toKey</tt> is not compatible
     *         with this myMap's myComparator (or, if the myMap has no myComparator,
     *         if <tt>toKey</tt> does not implement <tt>myComparable</tt>).
     *         Implementations may, but are not required to, throw this
     *	       exception if <tt>toKey</tt> cannot be compared to keys
     *         currently in the myMap.
     * @throws IllegalArgumentException if this myMap is itself a subMap,
     *         headMap, or tailMap, and <tt>toKey</tt> is not within the
     *         specified range of the subMap, headMap, or tailMap.
     * @throws NullPointerException if <tt>toKey</tt> is <tt>null</tt> and
     *	       this sorted myMap does not tolerate <tt>null</tt> keys.
     */
    mySortedMap headMap(Object toKey);

    /**
     * Returns a view of the portion of this sorted myMap whose keys are greater
     * than or equal to <tt>fromKey</tt>.  The returned sorted myMap is backed
     * by this sorted myMap, so changes in the returned sorted myMap are reflected
     * in this sorted myMap, and vice-versa.  The returned myMap supports all
     * optional myMap operations that this sorted myMap supports.<p>
     *
     * The myMap returned by this method will throw an
     * <tt>IllegalArgumentException</tt> if the user attempts to insert a key
     * outside the specified range.<p>
     *
     * Note: this method always returns a view that contains its (low)
     * endpoint.  If you need a view that does not contain this endpoint, and
     * the element type allows for calculation of the successor a given value,
     * merely request a tailMap bounded by <tt>successor(lowEndpoint)</tt>.
     * For example, suppose that suppose that <tt>m</tt> is a myMap whose keys
     * are strings.  The following idiom obtains a view containing all of the
     * key-value mappings in <tt>m</tt> whose keys are strictly greater than
     * <tt>low</tt>:
     * 
     * 	    <pre>    myMap tail = m.tailMap(low+"\0");</pre>
     *
     * @param fromKey low endpoint (inclusive) of the tailMap.
     * @return a view of the specified final range of this sorted myMap.
     * @throws ClassCastException if <tt>fromKey</tt> is not compatible
     *         with this myMap's myComparator (or, if the myMap has no myComparator,
     *         if <tt>fromKey</tt> does not implement <tt>myComparable</tt>).
     *         Implementations may, but are not required to, throw this
     *	       exception if <tt>fromKey</tt> cannot be compared to keys
     *         currently in the myMap.
     * @throws IllegalArgumentException if this myMap is itself a subMap,
     *         headMap, or tailMap, and <tt>fromKey</tt> is not within the
     *         specified range of the subMap, headMap, or tailMap.
     * @throws NullPointerException if <tt>fromKey</tt> is <tt>null</tt> and
     *	       this sorted myMap does not tolerate <tt>null</tt> keys.
     */
    mySortedMap tailMap(Object fromKey);

    /**
     * Returns the first (lowest) key currently in this sorted myMap.
     *
     * @return the first (lowest) key currently in this sorted myMap.
     * @throws    NoSuchElementException if this myMap is empty.
     */
    Object firstKey();

    /**
     * Returns the last (highest) key currently in this sorted myMap.
     *
     * @return the last (highest) key currently in this sorted myMap.
     * @throws     NoSuchElementException if this myMap is empty.
     */
    Object lastKey();
}
