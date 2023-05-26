package httpserver;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class NativeHelloWorldServerVT {

    public static String fileRoot = "/Users/mayankc/Work/source/perfComparisons/testdata/";

    public static String getRandomNumber(int min, int max) {
        return Integer.toString((int) ((Math.random() * (max - min)) + min));
    }

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String fileName = NativeHelloWorldServer.fileRoot + NativeHelloWorldServer.getRandomNumber(1, 100000);
            byte[] fileContents = Files.readAllBytes(Paths.get(fileName));
            t.sendResponseHeaders(200, fileContents.length);
            OutputStream os = t.getResponseBody();
            os.write(fileContents);
            os.close();
        }
    }

}
