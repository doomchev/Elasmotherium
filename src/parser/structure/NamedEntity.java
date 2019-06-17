package parser.structure;

public abstract class NamedEntity extends Entity {
  public ID name;
  
  @Override
  public ID getNameID() {
    return name;
  }

  @Override
  public String toString() {
    return name.string;
  }
}
