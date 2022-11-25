import java.util.Arrays;
import java.util.List;

class Frame
{
    static final int WIDTH = 60;
    static final int HEIGHT = 30;

    private static final String COLOR_MAP_1 = ".'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
    private static final String COLOR_MAP_1_REV = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'.";

    private static final String DEFAULT_COLOR_MAP = COLOR_MAP_1;

    private int[] frameBuffer = new int[WIDTH*HEIGHT];
    private String colorMap;

    public Frame(int[] frameBuffer, String colorMap)
    {
        setColorMap(colorMap);
        setFrameBuffer(frameBuffer);
    }

    public Frame(int[] frameBuffer)
    {
        setColorMap(DEFAULT_COLOR_MAP);
        setFrameBuffer(frameBuffer);
    }

    public Frame()
    {
        this("dark");
    }

    public Frame(String frameConfig)
    {
        setColorMap(DEFAULT_COLOR_MAP);
        // TODO: implement changing default frame setup
        // ex. "light, "dark", "light-bottomrow-dark", "dark-bottomrow-light", etc
        String[] tokens = frameConfig.split("-");

        if(tokens[0].equals("dark"))
        {
            Arrays.fill(this.frameBuffer, (this.colorMap.length() - 1));
        }
        else if (tokens[0].equals("light"))
        {
            Arrays.fill(this.frameBuffer, 0);
        }
        else
        {
            log("ERROR",
                    String.format("unsupported specification \"%s\"", tokens[0]));
        }
    }

    private void setColorMap(String colorMap)
    {
        this.colorMap = colorMap;
    }

    private void setFrameBuffer(int[] frameBuffer)
    {
        this.frameBuffer = frameBuffer;
    }

    public String getColorMap()
    {
        return new String(this.colorMap);
    }

    public int[] getFrameBuffer()
    {
        return frameBuffer.clone();
    }

    public void printFrame()
    {
        clearScreen();

        // log("INFO", "frameBuffer length = " + frameBuffer.length);
        // log("INFO", "colorMap length = " + colorMap.length());

        System.out.println(this.toString());

        System.out.println();
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < frameBuffer.length; i++)
        {
            if (i % WIDTH == 0)
                output.append('\n');
            if (frameBuffer[i] >= colorMap.length())
                output.append(colorMap.charAt(colorMap.length() - 1));
            else
                output.append(colorMap.charAt(frameBuffer[i]));
        }
        
        return output.toString();
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
        Frame testFrame = new Frame("light");
        testFrame.printFrame();
    }
}
