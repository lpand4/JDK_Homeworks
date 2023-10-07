package server.client;

import server.server.Server;
import server.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final int DELTA_X = 400;
    public static final int DELTA_Y = 300;
    public static int position = 0;

    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    private Client client;

    public ClientGUI(ServerWindow server){
        this.client = new Client(this, server.getServer());

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocationClient(server, position);

        createPanel();
        position++;

        setVisible(true);
    }


    private void connectToServer() {
        if (client.connectToServer(tfLogin.getText())){
            hideHeaderPanel(false);
        }
    }

    @Override
    public void showMessage(String text) {
        appendLog(text);
    }

    public void disconnectFromServer() {
        hideHeaderPanel(true);
        client.disconnectFromServer();
    }

    private void hideHeaderPanel(boolean visible){headerPanel.setVisible(visible);}

    public void sendMessage(){
        client.sendMessage(tfMessage.getText());
        tfMessage.setText("");
    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    sendMessage();;
                }
            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e){
        super.processWindowEvent(e);
        if (e.getID()==WindowEvent.WINDOW_CLOSING){
            disconnectFromServer();
        }
    }
    
    private void setLocationClient(ServerWindow server, int position){
        switch (position){
            case 0:
                setLocation(server.getX() - DELTA_X, server.getY() - DELTA_Y);
                break;
            case 1:
                setLocation(server.getX(), server.getY() - DELTA_Y);
                break;
            case 2:
                setLocation(server.getX() + DELTA_X, server.getY() - DELTA_Y);
                break;
            case 3:
                setLocation(server.getX() - DELTA_X, server.getY());
                break;
            case 4:
                setLocation(server.getX() + DELTA_X, server.getY());
                break;
            case 5:
                setLocation(server.getX() - DELTA_X, server.getY() + DELTA_Y);
                break;
            case 6:
                setLocation(server.getX(), server.getY() + DELTA_Y);
                break;
            case 7:
                setLocation(server.getX() + DELTA_X, server.getY() + DELTA_Y);
                break;
            default:
                setLocation(server.getX() - DELTA_X, server.getY() + DELTA_Y);
        }
    }
}
