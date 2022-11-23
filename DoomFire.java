import java.lang.Thread;

import java.util.Arrays;
import java.util.List;

class DoomFire
{

    static final String colorMapOne = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'.";

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

    /*
    public static void printFrame(int[] frameBuffer)
    {
        for(Cell[] row : board)
        {
            for(Cell c : row)
            {
                if(c.isAlive())
                    System.out.print(aliveChar);
                else
                    System.out.print(deadChar);
            }
            System.out.print("\n");
        }
    }
    */

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
    }

    public static void log(String type, String content)
    {
        List<String> validLogs = Arrays.asList("ERROR","INFO","LOG");

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
        final int SCREEN_WIDTH = 150;
        final int SCREEN_HEIGHT = 100;

        // int[] screen = new int[SCREEN_WIDTH *];

        // printLoop(500);

        log("INFO", String.format("length of colorMapOne: %d", colorMapOne.length()));
    }
}
