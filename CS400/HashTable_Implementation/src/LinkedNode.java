// --== CS400 File Header Information ==--
// Name: Shi Kaiwen
// Email: kshi42@wisc.edu
// Notes to Grader: <optional extra notes>
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
