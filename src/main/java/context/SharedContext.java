package context;

public class SharedContext {
    private static int position;

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        SharedContext.position = position;
    }
}
