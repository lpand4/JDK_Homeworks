import java.awt.*;

public class Main {

    public static void main(String[] args) {
        ClientGUI clientGUI1 = new  ClientGUI();


        Point clientGUIpoint = clientGUI1.getLocation();
        Point serverPoint = new Point(clientGUIpoint.x + clientGUI1.getWidth(), clientGUIpoint.y);

        ServerWindow serverWindow = new  ServerWindow();
        serverWindow.setLocation(serverPoint);
        clientGUI1.setServerWindow(serverWindow);

    }
}
