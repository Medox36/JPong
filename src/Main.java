public class Main {

    public static void main(String[] args) {
        PongGui pongGui = new PongGui();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pongGui.start();
    }
}