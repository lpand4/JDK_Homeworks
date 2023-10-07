package server.server;


public interface Repository {
    void saveLog(String log);
    String readLog();
}
