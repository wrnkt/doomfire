import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class FramePrinter
{

    public static Frame getNextFireFrame(Frame fSource)
    {
        int[] newFrameBuffer = new int[fSource.WIDTH * fSource.HEIGHT];
        newFrameBuffer = fSource.getFrameBuffer().clone();

        for (int x = 0; x < fSource.WIDTH; x++)
        {
            for (int y = 1; y < fSource.HEIGHT; y++)
            {
                spreadFireUpRand(fSource, newFrameBuffer, x, y);
            }
        }

        Frame newFrame = new Frame(newFrameBuffer, fSource.getColorMap());
        return newFrame;
    }

    public static void spreadFireUpLeftRightRand(Frame fSource, int[] newFrameBuffer, int x, int y)
    {
        log("LOG", String.format("length of fSource buffer = %d", fSource.getFrameBuffer().length));
        log("LOG", String.format("length of newFrameBuffer = %d", newFrameBuffer.length));
        log("LOG", String.format("x = %d", x));
        log("LOG", String.format("y = %d", y));
        int rand = (int) Math.round(Math.random() * 3.0) & 3;

        int srcIndex = xyToBufferIndex(fSource, x, y);
        int destIndex = srcIndex - fSource.WIDTH - rand + 1;

        // set frameBuffer values' lower limit
        if (fSource.getFrameBuffer()[destIndex] > 0 &&
                (srcIndex > 0 && srcIndex < fSource.getFrameBuffer().length) &&
                (destIndex > 0 && destIndex < newFrameBuffer.length)) 
            newFrameBuffer[destIndex] = fSource.getFrameBuffer()[srcIndex] + (rand & 1);
    }

    public static void spreadFireUpRand(Frame fSource, int[] newFrameBuffer, int x, int y)
    {
        int srcIndex = xyToBufferIndex(fSource, x, y);
        int destIndex = srcIndex - fSource.WIDTH;

        int rand = (int) Math.round(Math.random() * 3.0) & 3;

        // set frameBuffer values' lower limit
        if (fSource.getFrameBuffer()[destIndex] > 0) 
            newFrameBuffer[destIndex] = fSource.getFrameBuffer()[srcIndex] + (rand & 1);
    }

    public static void spreadFireUp(Frame fSource, int[] newFrameBuffer, int x, int y)
    {
        int srcIndex = xyToBufferIndex(fSource, x, y);
        int destIndex = srcIndex - fSource.WIDTH;

        // set frameBuffer values' lower limit
        if (fSource.getFrameBuffer()[destIndex] > 0) 
            newFrameBuffer[destIndex] = fSource.getFrameBuffer()[srcIndex] + 1;
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
                clearScreen();
                f.printFrame();
                Thread.sleep(msDelay);
                // clearScreen();
            }
            catch(InterruptedException e)
            {
                log("ERROR", "Failed to print.");
            }
        }
    }

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
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

        ArrayList<Frame> fireTest = new ArrayList<>();
        Frame seedFrame = new Frame("dark");
        seedFrame.fillBottomRow(0);
        fireTest.add(seedFrame);
        // System.out.println(seedFrame);

        Frame currentFrame = seedFrame;

        for (int i = 0; i < 25; i++)
        {
            Frame nextFrame = getNextFireFrame(currentFrame);
            fireTest.add(nextFrame);
            currentFrame = nextFrame;
        }

        printLoop(fireTest, 50);

    }
}
