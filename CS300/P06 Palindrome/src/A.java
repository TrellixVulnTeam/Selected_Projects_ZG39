public class A
{
    public static void main(String[] args) {
        double[] grades = new double[100000];
        int size = 2;
        int n = 100000;
        String[] data = new String[2];
        data[1] = "1";
        String target = "1";
        int left = 2;
        int right = 3;
        System.out.println(System.currentTimeMillis());
        find(data, target, left, right);
        System.out.println(System.currentTimeMillis());
    }

    public void someMethod() {
        LinkedNode<String> previous;
        LinkedNode<String> current;
    
    /* MISSING CODE */
    
    // current refers now to the node whose data equals "D" in the list
    // previous refers to its previous node
    // if current is null, this means that no match with "D" was found in the list

    
  }
      
    public static double quadratic(int n) {
        if (n <= 0)
            return -n + Math.sqrt(n * n - 4);
        return (n - Math.sqrt(n * n + 4)) / 2.0;
    }
    
    public static int find(String[] data, String target, int left, int right) {
    if (left > right) return -1;
    if (left == right) {
        if (data[left].equals(target)) return left;
        return -1;
    }

    // partition list and search
    int center = (left + right) / 2;
    int index1 = find(data, target, left, center);
    if (index1 != -1)  return index1;
    int index2 = find(data, target, center + 1, right);
    if (index2 != -1)  return index2;
    return -1;
    }
}