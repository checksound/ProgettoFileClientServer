import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int PORT = 80;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server in ascolto sulla porta " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Nuovo client connesso: " + clientSocket);
            ServerRunner srv = new ServerRunner(clientSocket);
            srv.start();
        }
    }
}
