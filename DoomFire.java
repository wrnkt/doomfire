import java.lang.Thread;

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
                logType("ERROR", "Failed to print.");
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

    public static void logType(String type, String content)
    {
        print(String.format(
                    "[%s]: %s",
                    type,
                    content
                    ));
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

        print(String.format("Length of colorMapOne: %d", colorMapOne.length()));
    }
}
