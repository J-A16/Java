public class Dog extends Pet
{
  public String call()
  { 
    return "woof!";
  }
  public String toString()
  { 
    return "I'm a dog, pet me!";
  }
  public String call(int k)
  { 
    String s = "Woof";
    for(int i = 1; i < k; ++i)
      s += ", woof";
    return s + "!";
  }
}