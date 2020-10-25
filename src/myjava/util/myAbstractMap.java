/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package myjava.util;
import myjava.util.myMap.Entry;

/**
 * This class provides a skeletal implementation of the <tt>myMap</tt>
 * interface, to minimize the effort required to implement this interface. <p>
 *
 * To implement an unmodifiable myMap, the programmer needs only to extend this
 * class and provide an implementation for the <tt>entrySet</tt> method, which
 * returns a mySet-view of the myMap's mappings.  Typically, the returned mySet
 * will, in turn, be implemented atop <tt>myAbstractSet</tt>.  This mySet should
 * not support the <tt>add</tt> or <tt>remove</tt> methods, and its myIterator
 * should not support the <tt>remove</tt> method.<p>
 *
 * To implement a modifiable myMap, the programmer must additionally override
 * this class's <tt>put</tt> method (which otherwise throws an
 * <tt>UnsupportedOperationException</tt>), and the myIterator returned by
 * <tt>entrySet().myIterator()</tt> must additionally implement its
 * <tt>remove</tt> method.<p>
 *
 * The programmer should generally provide a void (no argument) and myMap
 * constructor, as per the recommendation in the <tt>myMap</tt> interface
 * specification.<p>
 *
 * The documentation for each non-abstract methods in this class describes its
 * implementation in detail.  Each of these methods may be overridden if the
 * myMap being implemented admits a more efficient implementation.
 *
 * @author  Josh Bloch
 * @version 1.22, 02/06/02
 * @see myMap
 * @see myCollection
 * @since 1.2
 */

public abstract class myAbstractMap implements myMap {
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected myAbstractMap() {
    }

    // Query Operations

    /**
     * Returns the number of key-value mappings in this myMap.  If the myMap
     * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.<p>
     *
     * This implementation returns <tt>entrySet().size()</tt>.
     *
     * @return the number of key-value mappings in this myMap.
     */
    public int size() {
	return entrySet().size();
    }

    /**
     * Returns <tt>true</tt> if this myMap contains no key-value mappings. <p>
     *
     * This implementation returns <tt>size() == 0</tt>.
     *
     * @return <tt>true</tt> if this myMap contains no key-value mappings.
     */
    public boolean isEmpty() {
	return size() == 0;
    }

    /**
     * Returns <tt>true</tt> if this myMap maps one or more keys to this value.
     * More formally, returns <tt>true</tt> if and only if this myMap contains
     * at least one mapping to a value <tt>v</tt> such that <tt>(value==null ?
     * v==null : value.equals(v))</tt>.  This operation will probably require
     * time linear in the myMap size for most implementations of myMap.<p>
     *
     * This implementation iterates over entrySet() searching for an entry
     * with the specified value.  If such an entry is found, <tt>true</tt> is
     * returned.  If the iteration terminates without finding such an entry,
     * <tt>false</tt> is returned.  Note that this implementation requires
     * linear time in the size of the myMap.
     *
     * @param value value whose presence in this myMap is to be tested.
     * 
     * @return <tt>true</tt> if this myMap maps one or more keys to this value.
     */
    public boolean containsValue(Object value) {
	myIterator i = entrySet().myIterator();
	if (value==null) {
	    while (i.hasNext()) {
		Entry e = (Entry) i.next();
		if (e.getValue()==null)
		    return true;
	    }
	} else {
	    while (i.hasNext()) {
		Entry e = (Entry) i.next();
		if (value.equals(e.getValue()))
		    return true;
	    }
	}
	return false;
    }

    /**
     * Returns <tt>true</tt> if this myMap contains a mapping for the specified
     * key. <p>
     *
     * This implementation iterates over <tt>entrySet()</tt> searching for an
     * entry with the specified key.  If such an entry is found, <tt>true</tt>
     * is returned.  If the iteration terminates without finding such an
     * entry, <tt>false</tt> is returned.  Note that this implementation
     * requires linear time in the size of the myMap; many implementations will
     * override this method.
     *
     * @param key key whose presence in this myMap is to be tested.
     * @return <tt>true</tt> if this myMap contains a mapping for the specified
     *            key.
     * 
     * @throws ClassCastException if the specified key is of an inappropriate
     * 		  type for this myMap.
     * 
     * @throws NullPointerException key is <tt>null</tt> and this myMap does not
     *            not permit <tt>null</tt> keys.
     */
    public boolean containsKey(Object key) {
	myIterator i = entrySet().myIterator();
	if (key==null) {
	    while (i.hasNext()) {
		Entry e = (Entry) i.next();
		if (e.getKey()==null)
		    return true;
	    }
	} else {
	    while (i.hasNext()) {
		Entry e = (Entry) i.next();
		if (key.equals(e.getKey()))
		    return true;
	    }
	}
	return false;
    }

    /**
     * Returns the value to which this myMap maps the specified key.  Returns
     * <tt>null</tt> if the myMap contains no mapping for this key.  A return
     * value of <tt>null</tt> does not <i>necessarily</i> indicate that the
     * myMap contains no mapping for the key; it's also possible that the myMap
     * explicitly maps the key to <tt>null</tt>.  The containsKey operation
     * may be used to distinguish these two cases. <p>
     *
     * This implementation iterates over <tt>entrySet()</tt> searching for an
     * entry with the specified key.  If such an entry is found, the entry's
     * value is returned.  If the iteration terminates without finding such an
     * entry, <tt>null</tt> is returned.  Note that this implementation
     * requires linear time in the size of the myMap; many implementations will
     * override this method.
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this myMap maps the specified key.
     * 
     * @throws ClassCastException if the specified key is of an inappropriate
     * 		  type for this myMap.
     * 
     * @throws NullPointerException if the key is <tt>null</tt> and this myMap
     *		  does not not permit <tt>null</tt> keys.
     * 
     * @see #containsKey(Object)
     */
    public Object get(Object key) {
	myIterator i = entrySet().myIterator();
	if (key==null) {
	    while (i.hasNext()) {
		Entry e = (Entry) i.next();
		if (e.getKey()==null)
		    return e.getValue();
	    }
	} else {
	    while (i.hasNext()) {
		Entry e = (Entry) i.next();
		if (key.equals(e.getKey()))
		    return e.getValue();
	    }
	}
	return null;
    }


    // Modification Operations

    /**
     * Associates the specified value with the specified key in this myMap
     * (optional operation).  If the myMap previously contained a mapping for
     * this key, the old value is replaced.<p>
     *
     * This implementation always throws an
     * <tt>UnsupportedOperationException</tt>.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * 
     * @return previous value associated with specified key, or <tt>null</tt>
     *	       if there was no mapping for key.  (A <tt>null</tt> return can
     *	       also indicate that the myMap previously associated <tt>null</tt>
     *	       with the specified key, if the implementation supports
     *	       <tt>null</tt> values.)
     * 
     * @throws UnsupportedOperationException if the <tt>put</tt> operation is
     *	          not supported by this myMap.
     * 
     * @throws ClassCastException if the class of the specified key or value
     * 	          prevents it from being stored in this myMap.
     * 
     * @throws IllegalArgumentException if some aspect of this key or value *
     *            prevents it from being stored in this myMap.
     * 
     * @throws NullPointerException this myMap does not permit <tt>null</tt>
     *            keys or values, and the specified key or value is
     *            <tt>null</tt>.
     */
    public Object put(Object key, Object value) {
//	throw new UnsupportedOperationException();
	throw new RuntimeException();
    }

    /**
     * Removes the mapping for this key from this myMap if present (optional
     * operation). <p>
     *
     * This implementation iterates over <tt>entrySet()</tt> searching for an
     * entry with the specified key.  If such an entry is found, its value is
     * obtained with its <tt>getValue</tt> operation, the entry is is removed
     * from the myCollection (and the backing myMap) with the myIterator's
     * <tt>remove</tt> operation, and the saved value is returned.  If the
     * iteration terminates without finding such an entry, <tt>null</tt> is
     * returned.  Note that this implementation requires linear time in the
     * size of the myMap; many implementations will override this method.
     *
     * @param key key whose mapping is to be removed from the myMap.
     * @return previous value associated with specified key, or <tt>null</tt>
     *	       if there was no entry for key.  (A <tt>null</tt> return can
     *	       also indicate that the myMap previously associated <tt>null</tt>
     *	       with the specified key, if the implementation supports
     *	       <tt>null</tt> values.)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     * 		  is not supported by this myMap.
     */
    public Object remove(Object key) {
	myIterator i = entrySet().myIterator();
	Entry correctEntry = null;
	if (key==null) {
	    while (correctEntry==null && i.hasNext()) {
		Entry e = (Entry) i.next();
		if (e.getKey()==null)
		    correctEntry = e;
	    }
	} else {
	    while (correctEntry==null && i.hasNext()) {
		Entry e = (Entry) i.next();
		if (key.equals(e.getKey()))
		    correctEntry = e;
	    }
	}

	Object oldValue = null;
	if (correctEntry !=null) {
	    oldValue = correctEntry.getValue();
	    i.remove();
	}
	return oldValue;
    }


    // Bulk Operations

    /**
     * Copies all of the mappings from the specified myMap to this myMap
     * (optional operation).  These mappings will replace any mappings that
     * this myMap had for any of the keys currently in the specified myMap.<p>
     *
     * This implementation iterates over the specified myMap's
     * <tt>entrySet()</tt> myCollection, and calls this myMap's <tt>put</tt>
     * operation once for each entry returned by the iteration.
     *
     * @param t mappings to be stored in this myMap.
     * 
     * @throws UnsupportedOperationException if the <tt>putAll</tt> operation
     * 		  is not supported by this myMap.
     * 
     * @throws ClassCastException if the class of a key or value in the
     * 	          specified myMap prevents it from being stored in this myMap.
     * 
     * @throws IllegalArgumentException if some aspect of a key or value in
     *	          the specified myMap prevents it from being stored in this myMap.
     * 
     * @throws NullPointerException this myMap does not permit <tt>null</tt>
     *            keys or values, and the specified key or value is
     *            <tt>null</tt>.
     */
    public void putAll(myMap t) {
	myIterator i = t.entrySet().myIterator();
	while (i.hasNext()) {
	    Entry e = (Entry) i.next();
	    put(e.getKey(), e.getValue());
	}
    }

    /**
     * Removes all mappings from this myMap (optional operation). <p>
     *
     * This implementation calls <tt>entrySet().clear()</tt>.
     *
     * @throws    UnsupportedOperationException clear is not supported
     * 		  by this myMap.
     */
    public void clear() {
	entrySet().clear();
    }


    // Views

    private transient mySet keySet = null;
    /**
     * Returns a mySet view of the keys contained in this myMap.  The mySet is
     * backed by the myMap, so changes to the myMap are reflected in the mySet,
     * and vice-versa.  (If the myMap is modified while an iteration over
     * the mySet is in progress, the results of the iteration are undefined.)
     * The mySet supports element removal, which removes the corresponding entry
     * from the myMap, via the myIterator.remove, mySet.remove,  removeAll
     * retainAll, and clear operations.  It does not support the add or
     * addAll operations.<p>
     *
     * This implementation returns a mySet that subclasses
     * myAbstractSet.  The subclass's myIterator method returns a "wrapper
     * object" over this myMap's entrySet() myIterator.  The size method delegates
     * to this myMap's size method and the contains method delegates to this
     * myMap's containsKey method.<p>
     *
     * The mySet is created the first time this method is called,
     * and returned in response to all subsequent calls.  No synchronization
     * is performed, so there is a slight chance that multiple calls to this
     * method will not all return the same mySet.
     *
     * @return a mySet view of the keys contained in this myMap.
     */
    public mySet keySet() {
	if (keySet == null) {
	    keySet = new myAbstractSet() {
		public myIterator myIterator() {
		    return new myIterator() {
			private myIterator i = entrySet().myIterator();

			public boolean hasNext() {
			    return i.hasNext();
			}

			public Object next() {
			    return ((Entry)i.next()).getKey();
			}

			public void remove() {
			    i.remove();
			}
                    };
		}

		public int size() {
		    return myAbstractMap.this.size();
		}

		public boolean contains(Object k) {
		    return myAbstractMap.this.containsKey(k);
		}
	    };
	}
	return keySet;
    }

    private transient myCollection values = null;
    /**
     * Returns a myCollection view of the values contained in this myMap.  The
     * myCollection is backed by the myMap, so changes to the myMap are reflected in
     * the myCollection, and vice-versa.  (If the myMap is modified while an
     * iteration over the myCollection is in progress, the results of the
     * iteration are undefined.)  The myCollection supports element removal,
     * which removes the corresponding entry from the myMap, via the
     * <tt>myIterator.remove</tt>, <tt>myCollection.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt> and <tt>clear</tt> operations.
     * It does not support the <tt>add</tt> or <tt>addAll</tt> operations.<p>
     *
     * This implementation returns a myCollection that subclasses abstract
     * myCollection.  The subclass's myIterator method returns a "wrapper object"
     * over this myMap's <tt>entrySet()</tt> myIterator.  The size method
     * delegates to this myMap's size method and the contains method delegates
     * to this myMap's containsValue method.<p>
     *
     * The myCollection is created the first time this method is called, and
     * returned in response to all subsequent calls.  No synchronization is
     * performed, so there is a slight chance that multiple calls to this
     * method will not all return the same myCollection.
     *
     * @return a myCollection view of the values contained in this myMap.
     */
    public myCollection values() {
	if (values == null) {
	    values = new myAbstractCollection() {
		public myIterator myIterator() {
		    return new myIterator() {
			private myIterator i = entrySet().myIterator();

			public boolean hasNext() {
			    return i.hasNext();
			}

			public Object next() {
			    return ((Entry)i.next()).getValue();
			}

			public void remove() {
			    i.remove();
			}
                    };
                }

		public int size() {
		    return myAbstractMap.this.size();
		}

		public boolean contains(Object v) {
		    return myAbstractMap.this.containsValue(v);
		}
	    };
	}
	return values;
    }

    /**
     * Returns a mySet view of the mappings contained in this myMap.  Each element
     * in this mySet is a myMap.Entry.  The mySet is backed by the myMap, so changes
     * to the myMap are reflected in the mySet, and vice-versa.  (If the myMap is
     * modified while an iteration over the mySet is in progress, the results of
     * the iteration are undefined.)  The mySet supports element removal, which
     * removes the corresponding entry from the myMap, via the
     * <tt>myIterator.remove</tt>, <tt>mySet.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not support
     * the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a mySet view of the mappings contained in this myMap.
     */
    public abstract mySet entrySet();


    // Comparison and hashing

    /**
     * Compares the specified object with this myMap for equality.  Returns
     * <tt>true</tt> if the given object is also a myMap and the two maps
     * represent the same mappings.  More formally, two maps <tt>t1</tt> and
     * <tt>t2</tt> represent the same mappings if
     * <tt>t1.keySet().equals(t2.keySet())</tt> and for every key <tt>k</tt>
     * in <tt>t1.keySet()</tt>, <tt> (t1.get(k)==null ? t2.get(k)==null :
     * t1.get(k).equals(t2.get(k))) </tt>.  This ensures that the
     * <tt>equals</tt> method works properly across different implementations
     * of the myMap interface.<p>
     *
     * This implementation first checks if the specified object is this myMap;
     * if so it returns <tt>true</tt>.  Then, it checks if the specified
     * object is a myMap whose size is identical to the size of this mySet; if
     * not, it it returns <tt>false</tt>.  If so, it iterates over this myMap's
     * <tt>entrySet</tt> myCollection, and checks that the specified myMap
     * contains each mapping that this myMap contains.  If the specified myMap
     * fails to contain such a mapping, <tt>false</tt> is returned.  If the
     * iteration completes, <tt>true</tt> is returned.
     *
     * @param o object to be compared for equality with this myMap.
     * @return <tt>true</tt> if the specified object is equal to this myMap.
     */
    public boolean equals(Object o) {
	if (o == this)
	    return true;

	if (!(o instanceof myMap))
	    return false;
	myMap t = (myMap) o;
	if (t.size() != size())
	    return false;

	myIterator i = entrySet().myIterator();
	while (i.hasNext()) {
	    Entry e = (Entry) i.next();
	    Object key = e.getKey();
	    Object value = e.getValue();
	    if (value == null) {
		if (!(t.get(key)==null && t.containsKey(key)))
		    return false;
	    } else {
		if (!value.equals(t.get(key)))
		    return false;
	    }
	}
	return true;
    }

    /**
     * Returns the hash code value for this myMap.  The hash code of a myMap is
     * defined to be the sum of the hash codes of each entry in the myMap's
     * <tt>entrySet()</tt> view.  This ensures that <tt>t1.equals(t2)</tt>
     * implies that <tt>t1.hashCode()==t2.hashCode()</tt> for any two maps
     * <tt>t1</tt> and <tt>t2</tt>, as required by the general contract of
     * Object.hashCode.<p>
     *
     * This implementation iterates over <tt>entrySet()</tt>, calling
     * <tt>hashCode</tt> on each element (entry) in the myCollection, and adding
     * up the results.
     *
     * @return the hash code value for this myMap.
     * @see myMap.Entry#hashCode()
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @see mySet#equals(Object)
     */
    public int hashCode() {
	int h = 0;
	myIterator i = entrySet().myIterator();
	while (i.hasNext())
	    h += i.next().hashCode();
	return h;
    }

    /**
     * Returns a string representation of this myMap.  The string representation
     * consists of a list of key-value mappings in the order returned by the
     * myMap's <tt>entrySet</tt> view's myIterator, enclosed in braces
     * (<tt>"{}"</tt>).  Adjacent mappings are separated by the characters
     * <tt>", "</tt> (comma and space).  Each key-value mapping is rendered as
     * the key followed by an equals sign (<tt>"="</tt>) followed by the
     * associated value.  Keys and values are converted to strings as by
     * <tt>String.valueOf(Object)</tt>.<p>
     *
     * This implementation creates an empty string buffer, appends a left
     * brace, and iterates over the myMap's <tt>entrySet</tt> view, appending
     * the string representation of each <tt>myMap.entry</tt> in turn.  After
     * appending each entry except the last, the string <tt>", "</tt> is
     * appended.  Finally a right brace is appended.  A string is obtained
     * from the stringbuffer, and returned.
     *
     * @return a String representation of this myMap.
     */
    public String toString() {
	int max = size() - 1;
	StringBuffer buf = new StringBuffer();
	myIterator i = entrySet().myIterator();

	buf.append("{");
	for (int j = 0; j <= max; j++) {
	    Entry e = (Entry) (i.next());
	    buf.append(e.getKey() + "=" + e.getValue());
	    if (j < max)
		buf.append(", ");
	}
	buf.append("}");
	return buf.toString();
    }
}
