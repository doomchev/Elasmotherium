package vm;

import ast.ObjectEntity;
import base.Base;
import java.util.HashMap;
import java.util.LinkedList;
import ast.Function;
import base.ElException;

public class VMBase extends Base{
  public static final int STACK_SIZE = 2 << 10;
  public static boolean[] booleanStack = new boolean[STACK_SIZE];
  public static byte[] typeStack = new byte[STACK_SIZE];
  public static long[] i64Stack = new long[STACK_SIZE];
  public static String[] stringStack = new String[STACK_SIZE];
  public static ObjectEntity[] objectStack = new ObjectEntity[STACK_SIZE];
  public static VMFunctionCall[] callStack = new VMFunctionCall[STACK_SIZE];
  public static int stackPointer = -1, callStackPointer = -1;
  public static VMFunctionCall currentCall = new VMFunctionCall(null);
  public static Command currentCommand;
  public static final HashMap<Function, Command> functions = new HashMap<>();
  public static final LinkedList<Command> gotos = new LinkedList<>();
  public static Command[] commands = new Command[STACK_SIZE];
  public static int commandNumber = -1;
  public static Function currentFunction;
  
  public static final byte TYPE_BOOLEAN = 0, TYPE_I64 = 1, TYPE_STRING = 2
      , TYPE_OBJECT = 3;
  

  public static void prepare(boolean run) {
    for(int index = 0; index <= commandNumber; index++)
      System.out.println(commands[index].toString());
    if(run) {
      //currentCommand = Base.main.startingCommand;
      while(currentCommand != null) {
        System.out.println(currentCommand.toString());
        try {
          currentCommand.execute();
        } catch (ElException ex) {
          error("Bytecode execution error", ex.message);
        }

        String stack = "";
        for(int index = 0; index <= stackPointer; index++)
          switch(typeStack[index]) {
            case TYPE_BOOLEAN:
              stack += booleanStack[index] ? "true " : "false ";
              break;
            case TYPE_I64:
              stack += i64Stack[index] + " ";
              break;
            case TYPE_STRING:
              stack += "\"" + stringStack[index] + "\" ";
              break;
            case TYPE_OBJECT:
              stack += objectStack[index].type.toString() + " ";
              break;
          }
        System.out.println("Stack: " + stack);
      }
    }
  }
}
