import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ServerWindow extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    final String nameLogFile = "log.txt";

    JButton btnStart, btnStop;
    JTextArea textAreaLog;
    boolean isWorkingServer = false;
    ClientGUI clientGUI;

    public void setClientGUI(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
    }

    public ServerWindow() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Chat server");
        setResizable(false);


        //Create panel with buttons
        JPanel panBottom = new JPanel(new GridLayout(1,2));
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });
        btnStop= new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
            }
        });
        panBottom.add(btnStart);
        panBottom.add(btnStop);

        textAreaLog = new JTextArea();
        JScrollPane scrollLog = new JScrollPane(textAreaLog);



        add(scrollLog);
        add(panBottom, BorderLayout.SOUTH);


        setVisible(true);
    }


    /**
     * При поднятии сервера пытаемся считать логированные данные, если есть - выводим,
     * если файл не найлен, то выводим в консоль информационное письмо.
     */
    void serverUp(){
        try(FileReader fr = new FileReader(nameLogFile)) {
            int c;
            while ((c = fr.read()) != -1){
                textAreaLog.append(String.valueOf((char)c));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {

        }
    }

    /**
     * Сохранение истории писем в файл
     */
    void serverLogToFile(){
        try(FileWriter fw = new FileWriter(nameLogFile)) {
            String logText = textAreaLog.getText();
            fw.write(prepareTextToLog(logText));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Подготовка текста к логированию
     */
    String prepareTextToLog(String logText){
        logText = logText.replaceAll("Server started\n", "")
                .replaceAll("Server is already started\n", "")
                .replaceAll("Server stopped\n", "")
                .replaceAll("Server has already been stopped\n", "");
        return logText;
    }

    /**
     * Поднятие сервера
     */
    void startServer(){
        if(!isWorkingServer){
            textAreaLog.append("Server started\n");
            serverUp();
            isWorkingServer = true;
        }else {
            textAreaLog.append("Server is already started\n");
        }
    }

    /**
     * Остановка сервера
     */
    void stopServer(){
        if(isWorkingServer){
            textAreaLog.setText("Server stopped\n");
            isWorkingServer = false;
            clientGUI.textAreaMsg.setText("Server was stopped! You need to start the server and re-login\n");
            clientGUI.panTop.setVisible(true);
        }else {

            textAreaLog.append("Server has already been stopped\n");
        }
    }

}
