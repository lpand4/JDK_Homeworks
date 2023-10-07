package server.client;

import server.server.Server;


public class Client {
    private String name;
    private ClientView clientView;
    private Server server;
    private boolean isConnected;

    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    public boolean connectToServer(String name){
        this.name = name;
        if(server.connectUser(this)){
            printText("Вы успешно подключились!\n");
            isConnected =true;
            String log = server.getLog();
            if(log != null){
                printText(log);
            }
            return true;
        }else {
            printText("Подключение не удалось");
            return false;
        }
    }

    public void disconnectFromServer(){
        if (isConnected){
            isConnected=false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            printText("Вы были отключены от сервера");
        }
    }

    public void sendMessage(String message){
        if(isConnected){
            if(!message.isEmpty()){
                server.sendMessage(name + ": " + message);
            }
        }else {
            printText("Нет подключения к серверу");
        }
    }

    public void serverAnswer(String answer){
        printText(answer);
    }

    public String getName(){return name;}

    private void printText(String text){
        clientView.showMessage(text);
    }
}
