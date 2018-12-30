public class Cat extends Pet
{
  public String call()
  {
    return "meow!";
  }
  public String toString()
  {
    return "I'm a cat, go away!";
  }
  public String toString(String b)
  {
    return b + "? ... " +
    "Don't waste my time.";
  }
}