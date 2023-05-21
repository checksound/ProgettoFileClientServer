import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

public class Client {

	static int PORT = 80;

	private enum TYPE {
		CHARACTER, BYTE
	}

	public static void main(String[] args) {
		Scanner ss = new Scanner(System.in);

		try {
			Socket clientSocket = new Socket("localhost", PORT);
			System.out.println("Connessione al server riuscita");
			InputStream input = clientSocket.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			String serverOut;

			System.out.println("Inserisci il nome del file che cerchi: ");
			String fileName = ss.nextLine().trim();
			out.println(fileName);

			if (fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png")) {

				BufferedInputStream bInput = new BufferedInputStream(input);

				byte[] buffer = new byte[1024];

				ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
				int byteRead;
				while ((byteRead = bInput.read(buffer)) != -1) {
					byteArray.write(buffer, 0, byteRead);
				}
				
				bInput.close();
				byteArray.close();
				
				System.out.println(byteArray);
				
				// doDunpOnFile(fileName, byteArray.toByteArray());
			
			} else {

				while ((serverOut = reader.readLine()) != null) {
					System.out.println(serverOut);
				}

				reader.close();
			}
			out.close();
			clientSocket.close();

		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		}
	}
	
	private static void doDunpOnFile(String fileName, byte[] data) throws IOException {
		
		//Files.list(null)
		FileOutputStream outputStream = new FileOutputStream("./dump/" + fileName);
		outputStream.write(data);
		outputStream.close();
		
	}

}
