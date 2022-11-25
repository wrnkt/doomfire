import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class FramePrinter
{

    public static void updateFire(Frame f)
    {
        for (int x = 0; x < f.WIDTH; x++)
        {
            for (int y = 1; y < (f.getFrameBuffer().length / f.WIDTH); y++)
            {
                spreadFire(f, xyToBufferIndex(f, x, y));
            }
        }
    }

    public static void spreadFire(Frame f, int srcIndex)
    {
        int destIndex = srcIndex - f.WIDTH;
        if (f.getFrameBuffer()[destIndex] > 0) // set frameBuffer values' lower limit
          f.getFrameBuffer()[destIndex] = f.getFrameBuffer()[srcIndex] - 1;
    }

    public static int xyToBufferIndex(Frame f, int x, int y)
    {
        int bufferIndex;

        if ((x < 0 || x > f.WIDTH) || (y < 0 || y > f.HEIGHT))
            bufferIndex = -1;
        else
            bufferIndex = (y * f.WIDTH) + x;

        return bufferIndex;
    }

    public static void printLoop(ArrayList<Frame> frameList, int msDelay)
    {

        for(Frame f : frameList)
        {
            try
            {
                f.printFrame();
                // updateFire(frameBuffer, WIDTH);
                // printFrame(frameBuffer, colorMap);
                Thread.sleep(msDelay);
                // clearScreen();
            }
            catch(InterruptedException e)
            {
                log("ERROR", "Failed to print.");
            }
        }
    }

    public static void log(String type, String content)
    {
        List<String> validLogs = Arrays.asList("ERROR","INFO","LOG", "TEST");

        if(!validLogs.contains(type))
        {
            log("ERROR", String.format("log type %s not provided.", type));
            return;
        }
        else
        {
            System.out.println(String.format("[%s] %s", type, content));
        }
    }

    public static void main(String[] args)
    {
        // log("LOG", "test test test");
        ArrayList<Frame> frameList1 = new ArrayList<>();
        frameList1.add(new Frame("dark"));
        frameList1.add(new Frame("light"));
        frameList1.add(new Frame("dark"));
        frameList1.add(new Frame("light"));
        frameList1.add(new Frame("dark"));
        frameList1.add(new Frame("light"));
        frameList1.add(new Frame("dark"));
        frameList1.add(new Frame("light"));

        printLoop(frameList1, 300);

    }
}
