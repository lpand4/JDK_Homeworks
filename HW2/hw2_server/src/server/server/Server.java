package server.server;


import server.client.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Мозг сервера
 */
public class Server {
    private List<Client> clientList;
    private boolean isWorking;
    private ServerView serverView;
    private Repository repository;

    public Server(ServerView serverView, Repository repository) {
        clientList = new ArrayList<>();
        this.serverView = serverView;
        this.repository = repository;
    }

    /**
     * Подключение клиента к серверу(чату)
     * @param client новый клиент
     * @return успешность подключения
     */
    public boolean connectUser(Client client){
        if (!isWorking){
            return false;
        }
        clientList.add(client);
        return true;
    }

    /**
     * Отключение клиента от сервера(чата)
     * @param client клиент, которого нужно отключить
     */
    public void disconnectUser(Client client){
        clientList.remove(client);
        if (client != null){
            client.disconnectFromServer();
        }
    }

    /**
     * Отключение всех клиентов
     */
    public void disconnectAllUsers(){
        int size = clientList.size();
        for (int i = size - 1; i >= 0; i--) {
            disconnectUser(clientList.get(i));
        }
    }

    /**
     * Запрос на получение данных прошлой переписки
     * @return история переписки
     */
    public String getLog() {
        return repository.readLog();
    }


    /**
     * Отправка сообщения
     * @param text текст сообщения
     */
    public void sendMessage(String text){
        if(!isWorking){
            return;
        }
        serverView.appendLog(text);
        answerAll(text);
        repository.saveLog(text);
    }

    /**
     * Отправка письма всем клиентам в чат
     * @param text сообщение
     */
    private void answerAll(String text){
        for (Client client: clientList){
            client.serverAnswer(text);
        }
    }

    /**
     * Статус работы сервера
     * @return работает\не работает
     */
    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
