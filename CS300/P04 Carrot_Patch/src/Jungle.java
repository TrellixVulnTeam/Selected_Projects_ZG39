import java.util.ArrayList;
public class Jungle {  
  public static void main(String[] args) {
    ArrayList<Animal> jungle = new ArrayList<Animal>();
    jungle.add(new Lion());
    jungle.add(new Zebra());
    jungle.add(new Animal());

    for(int i = 0; i < jungle.size(); i++) {
      jungle.get(i).go();
    }
  }
}

public class Animal{
  public void go() {  action(); }
  public void action() { /* prints("Action! "); */ }
}

public class Lion extends Animal{
  public void action() { /* prints("Hop! "); */}
}

public class Zebra extends Animal{
  public void action() { /* prints ("Run! "); */ }
}