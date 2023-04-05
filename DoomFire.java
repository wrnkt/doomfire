import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.lang.Thread;

class DoomFire
{
    static final String colorMapOne = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'.";
    static final int WIDTH = 60, HEIGHT = 30;

    /**
     * Iterates through indexes in frameBuffer and applies fire logic.
     * @param frameBuffer   An integer array representing the darkness of locations
     *                      in a grid representing the screen.
     * @param width         The number of characters in a row of the screen. 
     */
    public static void updateFire(int[] frameBuffer, int width) {
        for (int x = 0; x < width; x++)
        {
            for (int y = 1; y < (frameBuffer.length / width); y++)
            {
                spreadFire(frameBuffer, xyToBufferIndex(x, y));
            }
        }
    }

    public static void spreadFire(int[] frameBuffer, int srcIndex)
    {
        int destIndex = srcIndex - WIDTH;
        if (frameBuffer[destIndex] > 0) // set frameBuffer values' lower limit
            frameBuffer[destIndex] = frameBuffer[srcIndex] - 1;
    }

    public static int xyToBufferIndex(int x, int y)
    {
        int bufferIndex;

        if ((x < 0 || x > WIDTH) || (y < 0 || y > HEIGHT))
            // NOTE: Should probably throw IndexOutOfBoundsException instead of -1 return.
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
                updateFire(frameBuffer, WIDTH);
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

        log("INFO", "frameBuffer length = " + frameBuffer.length);
        log("INFO", "colorMap length = " + colorMap.length());

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
        System.out.println();
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
            printLn(String.format("[%s] %s", type, content));
        }
    }
    public static void printLn(String s)
    {
        System.out.println(s);
    }
        
    public static void testFrameWithColorMapFill(int[] frameBuffer, String colorMap)
    {
        for (int i = 0; i < frameBuffer.length; i++)
        {
            frameBuffer[i] = i % colorMap.length();
        }
    }

    /**
     * Iterates through indexes in frameBuffer and applies fire logic.
     * @param frameBuffer   An integer array representing the darkness of locations
     *                      in a grid representing the screen.
     * @param width         The number of characters in a row of the screen. 
     * @param value         Brightness/darkness value to fill the bottom row with.
     */
    public static void frameFillBottomRow(int[] frameBuffer, int width, int value)
    {
        // 0 1 2 3
        // 4 5 6 7
        // 8 9 10 11
        for (int i = frameBuffer.length - width; i < frameBuffer.length; i++)
        {
            frameBuffer[i] = value;
        }
    }

    public static void main(String[] args)
    {

        int[] testFrame = new int[WIDTH*HEIGHT];

        // log("INFO", String.format("length of colorMapOne: %d", colorMapOne.length()));
        // log("INFO", "content of colorMapOne: " + colorMapOne);
        // log("INFO", "frame length: " + testFrame.length);
        
        // testFrameWithColorMapFill(testFrame, colorMapOne);
        // printFrame(testFrame, colorMapOne);
        // printLn("");

        Arrays.fill(testFrame, 0);
        frameFillBottomRow(testFrame, WIDTH, 60);
        printLoop(testFrame, colorMapOneRev, 300);
    }
}
