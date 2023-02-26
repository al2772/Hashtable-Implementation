// --== CS400 Project One File Header ==--
// Name: Adam Lewandowski
// CSL Username: alewandowski
// Email: aclewandowski@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * A class that creates a hashtable that deals
 * with has collisions using chaining, resizing
 * and rehashing the hashtable when the load factor
 * exceeds .75
 * 
 * @author Adam Lewandowski
 *
 * @param <KeyValue> - The key being looked for in the hashtable
 * @param <ValueType> - The type of value located at the key
 */
public class HashtableMap<KeyValue, ValueType> implements MapADT {

  @SuppressWarnings("rawtypes")
  private LinkedList<Pair>[] hashtable;

  /*
   * Helper class to create a type that combines both KeyValue and ValueType to make a key-value
   * pair to insert into the array
   */
  private class Pair<KeyValue, ValueType> {
    private KeyValue key;
    private ValueType value;
    private Pair<KeyValue, ValueType> next;

    /**
     * Constructore that correctly sets the 
     * KeyValue to key and ValueType to type
     * 
     * @param key - Key of the pair
     * @param value - Value type of the pair
     */
    public Pair(KeyValue key, ValueType value) {
      this.key = key;
      this.value = value;
    }

    /**
     * 
     * @return - The key of this pair
     */
    public KeyValue getKey() {
      return this.key;
    }

    /**
     * 
     * @return - The value of this pair
     */
    public ValueType getValue() {
      return this.value;
    }

  }

  /**
   * Creates a hashtable with the total
   * capacity given by the parameter
   * 
   * @param capacity - The desired capacity
   * of the hashtable
   */
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    hashtable = new LinkedList[capacity];

  }

  /**
   * Creates a default hashtable with 
   * capacity 20
   */
  @SuppressWarnings("unchecked")
  public HashtableMap() {
    // with default capacity = 20
    hashtable = new LinkedList[20];

  }

  /**
   * Returns the length, or capacity, of the
   * hashtable to be used for testing the put
   * method
   * 
   * @return - The current capacity of the hashtable
   */
  public int tableLength() {
    return this.hashtable.length;
  }

  /**
   * Finds the correct index in the hashtable
   * that is associated with the given key 
   * 
   * @param key - The key that is being used
   * @return - The correct index of the hashtable
   * for the corresponding key
   */
  public int hashCode(Object key) {
    return Math.abs(key.hashCode()) % hashtable.length;
  }

  /**
   * Resizes and rehashes the hashtable when
   * the load factor is over .75 in order to deal
   * with collisions or potential collisions
   */
  @SuppressWarnings("unchecked")
  private void resize() {
    // create a temporary array that stores hashtable
    LinkedList<Pair>[] temp = hashtable;

    // double the length of hashtable
    hashtable = new LinkedList[hashtable.length * 2];

    // put the values in temp into hashtable, rehashing
    // as the're added
    for (int i = 0; i < temp.length; i++) {
      if (temp[i] != null) {
        hashtable[i] = new LinkedList<Pair>();
        for (int j = 0; j < temp[i].size(); j++) {
          hashtable[i].add(temp[i].get(j));
        }

      }
    }
  }


  @SuppressWarnings("unchecked")
  @Override
  /**
   * Correctly places a key-value pair in the 
   * hashtable, calling resize to double the
   * hashtable and rehash when needed
   * 
   * @param key - Key value being used to access hashtable
   * @param value - Value to be put in correct location
   */
  public boolean put(Object key, Object value) {
    // check if key is null
    if (key.equals(null) || containsKey(key)) {
      return false;
    }

    // get index
    int index = hashCode(key);

    // create a new pair
    Pair newPair = new Pair(key, value);

    // find index and add if empty value to correct spot
    if (hashtable[index] == null) {

      // put a new linked list at this index
      LinkedList<Pair> newList = new LinkedList<Pair>();
      hashtable[index] = newList;
      hashtable[index].add(newPair);

      // otherwise add to existing list
    } else {
      hashtable[index].add(newPair);
    }

    // find load factor
    double loadFactor = (double) size() / hashtable.length;
    // if load factor is too large, double
    // hashtable's length and rehash
    if (loadFactor >= .75) {
      resize();
    }

    return true;
  }


  @Override
  public Object get(Object key) throws NoSuchElementException {
    // check if the key is null, if so throw NoSuchElementException
    if (key.equals(null) || containsKey(key) == false) {
      throw new NoSuchElementException("Key does not exist");
    }

    // loop through the array and then the lists
    for (int i = 0; i < hashtable.length; i++) {
      if (hashtable[i] == null) {
        continue;
      }
      for (int j = 0; j < hashtable[i].size(); j++) {
        if (hashtable[i].get(j).getKey().equals(key)) {
          return hashtable[i].get(j).getValue();
        }

      }
    }


    return null;
  }


  @Override
  public int size() {
    // search through array and count the number
    // pairs stored in the array as a whole
    int count = 0;
    for (int i = 0; i < hashtable.length; i++) {
      if (hashtable[i] != null) {
        // get size of linked list and add to count
        count += hashtable[i].size();
      }
    }
    // return the final count after the loop
    return count;
  }


  @Override
  public boolean containsKey(Object key) {
    // loop through array to check if any index is
    // equal to that of the key
    for (int i = 0; i < hashtable.length; i++) {
      if (hashtable[i] != null) {
        for (int j = 0; j < hashtable[i].size(); j++) {
          if (hashtable[i].get(j).getKey().equals(key)) {
            return true;
          }
        }
      }
    }
    return false;
  }


  @Override
  public Object remove(Object key) {
    // check if the key is not in the table
    if (containsKey(key) == false) {
      return null;
    }
    // get index of the key value
    int index = hashCode(key);

    Object result = new Object();

    // loop through array
    for (int i = 0; i < hashtable.length; i++) {
      if (hashtable[i] != null) {
        // loop through the LinkedList at index
        for (int j = 0; j < hashtable[i].size(); j++) {

          // if the pair at the location in the linked list is equal
          // to that of the index, remove it
          if (hashtable[index].get(j).equals(hashtable[i].get(j))) {
            result = hashtable[i].get(j).getKey();
            hashtable[i].remove(j);
            return result;
          }
        }
      }
    }
    return null;
  }


  @Override
  public void clear() {
    // loop through hashtable
    for (int i = 0; i < hashtable.length; i++) {
      // clear the linked list at this index
      if (hashtable[i] != null) {
        hashtable[i].clear();
      }
    }

  }


}
