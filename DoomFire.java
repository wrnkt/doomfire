import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.lang.Thread;

class DoomFire
{
    static final int WIDTH = 80;
    static final int HEIGHT = 40;

    static final String colorMapOne = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'.";

    public static void updateFire(int[] frameBuffer)
    {
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 1; y < HEIGHT; y++)
            {
                spreadFire(frameBuffer, xyToBufferIndex(x, y));
            }
        }
    }

    public static void spreadFire(int[] frameBuffer, int srcIndex)
    {
        int destIndex = srcIndex = WIDTH;
        frameBuffer[destIndex] = frameBuffer[srcIndex] - 1;
    }

    public static int xyToBufferIndex(int x, int y)
    {
        int bufferIndex;

        if ((x < 0 || x > WIDTH) || (y < 0 || y > HEIGHT))
            bufferIndex = -1;
        else
            bufferIndex = (y * WIDTH) + x;

        return bufferIndex;
    }

    public static void printLoop(int[] frameBuffer, String colorMap, int msDelay)
    {

        for(int i = 0; i < 100; i++)
        {
            try
            {
                updateFire(frameBuffer);
                printFrame(frameBuffer, colorMap);
                Thread.sleep(msDelay);
                // clearScreen();
            }
            catch(InterruptedException e)
            {
                log("ERROR", "Failed to print.");
            }
        }
    }

    public static void printFrame(int[] frameBuffer, String colorMap)
    {
        clearScreen();
        for (int i = 0; i < frameBuffer.length; i++)
        {
            // log("INFO", "the value of the framebuffer is: " + frameBuffer[i]);
            if (i % WIDTH == 0)
                System.out.println();
            if (frameBuffer[i] >= colorMap.length())
                System.out.print(colorMap.charAt(colorMap.length() - 1));
            else
                System.out.print(colorMap.charAt(frameBuffer[i]));
        }
    }

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
    }

    // LOGGING FUNCTIONS

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
            print(String.format("[%s] %s", type, content));
        }
    }

    public static void print(String s)
    {
        System.out.println(s);
    }
        
    public static void testFrameWithColorMap(int[] frameBuffer, String colorMap)
    {
        for (int i = 0; i < frameBuffer.length; i++)
        {
            frameBuffer[i] = i % colorMap.length();
        }
    }
    public static void main(String[] args)
    {

        log("INFO", String.format("length of colorMapOne: %d", colorMapOne.length()));
        log("INFO", "content of colorMapOne: " + colorMapOne);

        int[] testFrame = new int[WIDTH*HEIGHT];
        // Arrays.fill(testFrame, 0);
        testFrameWithColorMap(testFrame, colorMapOne);

        log("INFO", "frame length: " + testFrame.length);
        // printFrame(testFrame, colorMapOne);
        printLoop(testFrame, colorMapOne, 100);
    }
}
