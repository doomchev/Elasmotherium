package vm;

public class I64Equate extends Command {
  int index;

  public I64Equate(int index) {
    this.index = index;
  }
  
  @Override
  public void execute() {
    i64Stack[index + currentCall.paramPosition] = i64Stack[stackPointer];
    typeStack[index + currentCall.paramPosition] = TYPE_I64;
    stackPointer--;
    currentCommand = nextCommand;
  }
  
  @Override
  public String toString() {
    return super.toString() + " " + index;
  }
}
