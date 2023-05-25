import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class ServerRunner extends Thread {
    private Socket clientSocket;
    private final String BASE_DIR = System.getProperty("user.dir") + "/files";
    private File f;

    public ServerRunner(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();
            
            String inputLine = in.readLine();
            System.out.println("Request: " + inputLine);
            Path path = Paths.get(inputLine);            
            
            Path pathDownload = Paths.get(BASE_DIR, path.toString());
            
            System.out.println("Path richiesto: " + pathDownload);
                f = pathDownload.toFile();
                if (f.exists() && !f.isDirectory()) {
                    if (f.getName().endsWith(".jpg") || f.getName().endsWith(".png") || f.getName().endsWith(".gif")) {
                        // send stream of bytes
                    		
                    	byte[] fileBytes = Files.readAllBytes(f.toPath());
                        out.write(fileBytes);
                        out.flush();
                        out.close();
                   } else {
                	   	PrintWriter outWriter = new PrintWriter(out);
                        String fileContent = new String(Files.readAllBytes(f.toPath()));
                        outWriter.println(fileContent); 
                        outWriter.flush();
                        outWriter.close();
                    }
                } else {
                	if(f.isDirectory()) {
                		// do listing
                		PrintWriter outWriter = new PrintWriter(out);
                		                		
                		EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
                		
                		StringWriter writer = new StringWriter();
                		PrintFiles pf = new PrintFiles(writer);
                		Files.walkFileTree(pathDownload, opts, 1, pf);
                		outWriter.print(writer.toString());
                		outWriter.flush();
                		outWriter.close();
                	} else {
                		PrintWriter outWriter = new PrintWriter(out, true);
                		outWriter.println("File '" + pathDownload + "' non trovato");
                		outWriter.close();
                	}
                	
                }

                in.close();
                clientSocket.close();

        } catch (Exception e) {
            System.out.println("Errore: " + e);
            e.printStackTrace();
        }
    }

}
