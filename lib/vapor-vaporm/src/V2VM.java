import cs132.util.ProblemException;
import cs132.vapor.ast.VBuiltIn.Op;
import cs132.vapor.ast.VaporProgram;
import cs132.vapor.parser.VaporParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class V2VM
{
  public static void main(String[] args)
  {
    try
    {
      InputStream inputStream = System.in;
      PrintStream errorStream = System.err;
      VaporProgram vp = parseVapor(inputStream, errorStream);
      DataSegment ds = new DataSegment(vp.dataSegments);
      // print out data segments
      System.out.print(ds.dump());
      Functions fs = new Functions(vp.functions, false);
      System.out.print(fs.dump() + "\n");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static VaporProgram parseVapor(InputStream in, PrintStream err)
    throws IOException
  {
    Op[] ops = {
      Op.Add, Op.Sub, Op.MulS, Op.Eq, Op.Lt, Op.LtS,
      Op.PrintIntS, Op.HeapAllocZ, Op.Error,
    };
    boolean allowLocals = true;
    String[] registers = null;
    boolean allowStack = false;

    VaporProgram program;
    try {
      program = VaporParser.run(new InputStreamReader(in), 1, 1,
        java.util.Arrays.asList(ops),
        allowLocals, registers, allowStack);
    }
    catch (ProblemException ex) {
      err.println(ex.getMessage());
      return null;
    }
    return program;
  }
}
