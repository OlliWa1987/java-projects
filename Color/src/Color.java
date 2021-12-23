public class Color {

    private int rgb;
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color GRAY = new Color(128, 128, 128);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);

    public Color(int rgb) {

        this.rgb = rgb;
    }

    public Color(int red, int green, int blue) {

        if (red < 0) {
            System.out.println("Red is out of range (< 0)! Set to 0!");
            red = 0;
        } else if (red > 255) {
            System.out.println("Red is out of range (> 255)! Set to 255!");
            red = 255;
        }

        if (green < 0) {
            System.out.println("Green is out of range (< 0)! Set to 0!");
            green = 0;
        } else if (green > 255) {
            System.out.println("Green is out of range (> 255)! Set to 255!");
            green = 255;
        }

        if (blue < 0) {
            System.out.println("Blue is out of range (< 0)! Set to 0!");
            blue = 0;
        } else if (blue > 255) {
            System.out.println("Blue is out of range (> 255)! Set to 255!");
            blue = 255;
        }

        this.rgb = (red << 16) | (green << 8) | blue;
    }

    public Color() {

        this.rgb = 16777215;
    }

    public int getRgb() {

        return rgb;
    }

    public int getRed() {

        int red = (rgb >> 16);
        return red;
    }

    public int getGreen() {

        int green = (rgb >> 8) & 0b11111111;
        return green;
    }

    public int getBlue() {

        int blue = rgb & 0b11111111;
        return blue;
    }

    public String getHex() {

        return "#" + String.format("%06x", getRgb()).toUpperCase();
    }

    public Color(String rgbHex) {

        this.rgb = Integer.parseInt(rgbHex.substring(1, 7), 16);
    }

    @Override
    public String toString() {

        return getHex();
    }

    public Color complementaryColor() {

        int complementRed = 255 - getRed();
        int complementGreen = 255 - getGreen();
        int complementBlue = 255 - getBlue();
        Color complementaryColor = new Color(complementRed, complementGreen, complementBlue);
        return complementaryColor;
    }

    public Color mixColor(Color color) {

        int mixedRed = (getRed() + color.getRed()) / 2;
        int mixedGreen = (getGreen() + color.getGreen()) / 2;
        int mixedBlue = (getBlue() + color.getBlue()) / 2;
        Color mixColor = new Color(mixedRed, mixedGreen, mixedBlue);
        return mixColor;
    }

    public static void main(String[] args) {

        //System.out.println(c.getRgb());
        //System.out.println(c.getRed());
        //System.out.println(c.getGreen());
        //System.out.println(c.getBlue());
        //System.out.println(c.getHex());
        //System.out.println(c.toString());
        //System.out.println(c.complementaryColor());
        //new ColorVisualizer(GRAY);
        //System.out.println(c.mixColor(WHITE));

        Color a = new Color("#00BFFF");
        Color b = new Color("#F8F8FF");
        Color c = new Color("#FF69B4");
        Color d = new Color("#4B0082");

        new ColorVisualizer(a);
        new ColorVisualizer(a.complementaryColor());
        new ColorVisualizer(a.mixColor(WHITE));
        new ColorVisualizer(a.mixColor(BLACK));

        new ColorVisualizer(b);
        new ColorVisualizer(b.complementaryColor());
        new ColorVisualizer(b.mixColor(WHITE));
        new ColorVisualizer(b.mixColor(BLACK));

        new ColorVisualizer(c);
        new ColorVisualizer(c.complementaryColor());
        new ColorVisualizer(c.mixColor(WHITE));
        new ColorVisualizer(c.mixColor(BLACK));

        new ColorVisualizer(d);
        new ColorVisualizer(d.complementaryColor());
        new ColorVisualizer(d.mixColor(WHITE));
        new ColorVisualizer(d.mixColor(BLACK));

    }

}
