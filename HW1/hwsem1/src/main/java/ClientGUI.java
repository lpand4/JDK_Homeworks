import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    /*
    Собрать графический интерфейс проекта месседжера (скриншоты можно посмотреть в материалах к уроку)
    Отправлять сообщения из текстового поля сообщения в лог по нажатию кнопки или
    по нажатию клавиши Enter на поле ввода сообщения;
    Продублировать импровизированный лог (историю) чата в файле;
    При запуске клиента чата заполнять поле истории из файла, если он существует.
    Обратите внимание, что чаще всего история сообщений хранится на сервере и
    заполнение истории чата лучше делать при соединении с сервером, а не при открытии окна клиента.
     */


    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;


    JButton btnSendMsg, btnLogin;
    JPanel panTop;
    JTextField textFieldMsg, textIP, textLogin, textPorts;
    JPasswordField textPassword;
    JTextArea textAreaMsg;
    boolean isLogin = false;
    String login, addressIP, ports, logFormat;
    ServerWindow serverWindow;


    /**
     * Привязываем клиента на сервер
     */
    public void setServerWindow(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        serverWindow.setClientGUI(this);
    }


    /**
     * Действия отправки сообщения по кнопке и ENTER
     */
    private Action actionSendMsg = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isLogin) {
                if (serverWindow.isWorkingServer) {
                    String message = login + ": " + textFieldMsg.getText() + "\n";
                    textAreaMsg.append(message);
                    String logMessage = logFormat + message;
                    serverWindow.textAreaLog.append(logMessage);
                    textFieldMsg.setText("");
                    serverWindow.serverLogToFile();
                } else {
                    textAreaMsg.append("First you need to start the server!\n");
                }

            } else {
                textAreaMsg.append("First you need to log in!\n");
            }
        }
    };


    public ClientGUI() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Chat client");
        setResizable(false);
        setLocationRelativeTo(serverWindow);


        //Create bottom panel
        JPanel panBottom = new JPanel(new BorderLayout());

        textFieldMsg = new JTextField();
        panBottom.add(textFieldMsg);

        btnSendMsg = new JButton("SEND");
        panBottom.add(btnSendMsg, BorderLayout.EAST);


        btnSendMsg.addActionListener(actionSendMsg);
        textFieldMsg.addActionListener(actionSendMsg);


        //Create top panel
        btnLogin = new JButton("login");
        panTop = createTopPanel(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isWorkingServer) {
                    textAreaMsg.append("You have successfully logged in!\n");
                    isLogin = true;
                    login = textLogin.getText();
                    addressIP = textIP.getText();
                    ports = textPorts.getText();
                    logFormat = addressIP + ":" + ports + " ";
                    textAreaMsg.append(prepareMessages(serverWindow.textAreaLog.getText()));

                    panTop.setVisible(false);
                } else {
                    textAreaMsg.append("First you need to start the server!\n");
                }
            }
        });


        textAreaMsg = new JTextArea();
        textAreaMsg.setEditable(false);
        JScrollPane scrollMsg = new JScrollPane(textAreaMsg);


        add(panBottom, BorderLayout.SOUTH);
        add(panTop, BorderLayout.NORTH);
        add(scrollMsg);


        setVisible(true);
    }

    /**
     * Создаем панельку сверху и передаем ее обратно
     */
    JPanel createTopPanel(JButton btnLogin) {
        JPanel panTop = new JPanel(new GridLayout(1, 3));

        JPanel panFirstPart = new JPanel(new GridLayout(2, 1));
        textIP = new JTextField("127.0.0.1");
        textLogin = new JTextField("Ivan Ivanovich");
        panFirstPart.add(textIP);
        panFirstPart.add(textLogin);


        JPanel panSecondPart = new JPanel(new GridLayout(2, 1));
        textPorts = new JTextField("8189");
        textPassword = new JPasswordField("12345");
        panSecondPart.add(textPorts);
        panSecondPart.add(textPassword);


        JPanel panThirdPart = new JPanel(new BorderLayout());
        panThirdPart.add(btnLogin, BorderLayout.SOUTH);


        panTop.add(panFirstPart);
        panTop.add(panSecondPart);
        panTop.add(panThirdPart);
        revalidate();


        return panTop;
    }

    /**
     * Подготовка логированных данных перед выгрузкой их в чат
     */
    String prepareMessages(String logs) {
        String messages = logs.replaceAll("Server started\n", "")
                .replaceAll("Server is already started\n", "")
                .replaceAll("Server stopped\n", "")
                .replaceAll("Server has already been stopped\n", "")
                .replaceAll(addressIP + ":" + ports + " ", "");
        return messages;
    }

}
