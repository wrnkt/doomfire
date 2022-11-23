import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.lang.Thread;

class DoomFire
{
    static final int WIDTH = 200;
    static final int HEIGHT = 160;

    static final String colorMapOne = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'.";

    public static int xyToBufferIndex(int x, int y)
    {
        int bufferIndex;

        if ((x < 0 || x > WIDTH) || (y < 0 || y > HEIGHT))
            bufferIndex = -1;
        else
            bufferIndex = (y * WIDTH) + x;

        return bufferIndex;
    }

    public static void printLoop(int msDelay)
    {

        for(int i = 0; i < 100; i++)
        {
            try
            {
                print(String.format("%d", i));
                Thread.sleep(msDelay);
                clearScreen();
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
            if (i % WIDTH == 0)
                System.out.println();
            System.out.print(colorMap.charAt(frameBuffer[i]));
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
            print(String.format("[%s] %s", type, content));
        }
    }

    public static void print(String s)
    {
        System.out.println(s);
    }
        
    public static void main(String[] args)
    {

        log("INFO", String.format("length of colorMapOne: %d", colorMapOne.length()));
        log("INFO", "content of colorMapOne: " + colorMapOne);

        int[] testFrame = new int[WIDTH*HEIGHT];
        Arrays.fill(testFrame, 0);

        printFrame(testFrame, colorMapOne);
    }
}
