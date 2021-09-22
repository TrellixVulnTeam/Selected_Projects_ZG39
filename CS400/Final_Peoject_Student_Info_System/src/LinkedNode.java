// --== CS400 File Header Information ==--
// Name: Shi Kaiwen
// Email: kshi42@wisc.edu
// Notes to Grader: <optional extra notes>

/**
 * This is a part of the backend of final project
 * @param <KeyType>
 * @param <ValueType>
 * @author Shi Kaiwen
 */
public class LinkedNode<KeyType, ValueType> {
    
    private KeyType key;
    private ValueType value;

    public LinkedNode(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return this.key;
    }

    public ValueType getValue() {
        return this.value;
    }

}
