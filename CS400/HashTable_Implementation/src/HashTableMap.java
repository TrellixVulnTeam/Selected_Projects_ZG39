// --== CS400 File Header Information ==--
// Name: Shi Kaiwen
// Email: kshi42@wisc.edu
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * The class imitates a Hash Table Map.
 * @author Shi Kaiwen
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    private int capacity; // Total capacity of hash table
    private int size; // num. of items in hash table
    //use a private array instance field to store key-value pairs
    private LinkedList<LinkedNode<KeyType, ValueType>>[] keyValPairs;

    /**
     * Generalized Hash Table constructor 
     * @param capacity Total capacity of Hash Map
     */
    public HashTableMap(int capacity) {
        size = 0;
        this.capacity = capacity;
        keyValPairs = new LinkedList[capacity];

    }
    
    /**
     * Default constructor initializes Hash Map and sets 
     * default Hash Map capacity to 10
     */
    public HashTableMap() {
        size = 0;
        this.capacity = 10;
        keyValPairs = new LinkedList[10];
    }

    /**
     * grow by doubling capacity and rehashing, whenever its load capacity 
     * becomes greater than or equal to 85%.
     * 
     * when load capacity < 85%: store new values in hash table at the index 
     * corresponding to the absolute value of key's hashCode() modulus the 
     * HashTableMap's current capacity.
     * 
     * @return only true when the "put" operation is successful.
     */
    @Override
    public boolean put(KeyType key, ValueType value) {
        LinkedNode<KeyType, ValueType> nodeToPutIn = new LinkedNode<KeyType, ValueType>(key, value);
        int hashKeyVal = Math.abs(key.hashCode()) % capacity;
        
        // Case 1: when the newly passed key is null or is already in your hash table 
        // return false then
        if (containsKey(key)) {
            return false;
        } else {            
            //Case 2 & 3: when there will be successful "put" operation
            // Case 2: when empty: 
            if (keyValPairs[hashKeyVal] == null) {
                keyValPairs[hashKeyVal] = new LinkedList();
                keyValPairs[hashKeyVal].add(new LinkedNode(key, value));
                
                size += 1;
            } else {
                // Case 3: all other cases.
                keyValPairs[hashKeyVal].add(nodeToPutIn);

                size += 1;
            }

            //finally, run rehashing() helper method to check if load capacity >= 85%
            rehashing();
            return true;
        } 
    }

    /**
     * private helper method for put().
     * check if load capacity >= 85%: 
     * yes then go rehashing (in private helper method)
     * @return only true when rehashed.
     */
    private boolean rehashing() {
        LinkedList<LinkedNode<KeyType, ValueType>>[] copy = new LinkedList[keyValPairs.length];
        if ((size * 1.0) / (capacity * 1.0) >= 0.85) {
            //make copy of current Hash Map:
            for (int i = 0; i < copy.length; i++) {
                copy[i] = keyValPairs[i];
            }

            //clear keyValPairs, double capacity
            capacity *= 2;
            clear();

            //copy back all values from previous copy
            for (int i = 0; i < copy.length; i++) {
                if (copy[i] != null) {
                    for (int j = 0; j < copy[i].size(); j++) {
                        put(copy[i].get(j).getKey(), copy[i].get(j).getValue());
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * get the key if it is stored in Hash Map.
     * Similar thinking pattern to what we did in containsKey() method write below.
     * Will see similarities in terms of coding
     * 
     * @return the desired key to get
     * @throws NoSuchElementException when no matched key found in hash table
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        int hashKeyVal = Math.abs(key.hashCode()) % capacity;
       
        if (keyValPairs[hashKeyVal] == null) {
            throw new NoSuchElementException("ERROR! KEY NOT IN HASH TABLE!");
        }

        //check matched hash key: if there, get it and return
        //if not, throw NoSuchElementException
        if (containsKey(key)) {
            for (int i = 0; i < keyValPairs[hashKeyVal].size(); i++) {
                if (keyValPairs[hashKeyVal].get(i).getKey().equals(key)) {
                    return keyValPairs[hashKeyVal].get(i).getValue();
                }
            }
        } else {
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException("ERROR! KEY NOT IN HASH TABLE!");  
    }

    /**
     * @return number of key-value pairs stored in this collection
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * check if certain key is in the hash table
     * @return only true when found certain key stored in hash table
     */
    @Override
    public boolean containsKey(KeyType key) {
        Boolean containsKey = false;
        int hashKeyVal = Math.abs(key.hashCode()) % capacity;
       
        //check matched hash key
        if (keyValPairs[hashKeyVal] != null) {
            for (int i = 0; i < keyValPairs[hashKeyVal].size(); i++) {
                if (keyValPairs[hashKeyVal].get(i).getKey().equals(key)) {
                    containsKey = true;
                }
            }
        }
        return containsKey;
    }

    /**
     * return null if no key is removed.
     * @return reference to the value associated with the key that is being removed
     */
    @Override
    public ValueType remove(KeyType key) {
        int hashKeyVal = Math.abs(key.hashCode()) % capacity;
        if (!containsKey(key) || keyValPairs[hashKeyVal] == null) {
            return null;
        } else {
            //traverse Hash Map
            for (int i = 0; i < keyValPairs[hashKeyVal].size(); i++) {
                if (keyValPairs[hashKeyVal].get(i).getKey().equals(key)) {
                    //if found, make a copy of removed ValueType, remove it and decrement size
                    //and finally return
                    ValueType removingKey = keyValPairs[hashKeyVal].get(i).getValue();
                    keyValPairs[hashKeyVal].remove(i);
                    size -= 1;
                    return removingKey;
                }
            }
        }
        return null;
    }

    /**
     * removes all key-value pairs from this collection
     * @return removed key-value pairs
     */
    @Override
    public void clear() {
        keyValPairs = new LinkedList[capacity];
        this.size = 0;
    }
}