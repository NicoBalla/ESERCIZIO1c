import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) throws Exception {
		try {
			 
			int severPort=8698;
			String clientMessage = "";
			
			// Listen to port
			ServerSocket server = new ServerSocket(severPort);
			System.out.println("Server: in ascolto sula porta " + severPort );

			Socket serverClientSocket = server.accept();
			
			DataInputStream inStream = new DataInputStream(serverClientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClientSocket.getOutputStream());
			
			while(!clientMessage.equals("end")) {
		     	clientMessage = inStream.readUTF();
		      	String cMg = clientMessage;
			int totalSpecialCharacters = 0, totalDigits = 0, totalVowels = 0, totalConsonants = 0;
		      	
				for (int i = 0; i < cMg.length(); i++) {
				 
				 // Alphabets family
				 // Controllo se il carattere è compreso tra "a" e "z" o tra "A" e "Z"
				 if ( (cMg.charAt(i) >= 97 && cMg.charAt(i) <= 122) || (cMg.charAt(i) >= 65 && cMg.charAt(i) <= 90) ) {
				 // Converto il carattere in lower case
					 cMg = cMg.toLowerCase();
				 // Vowels
				 // Controllo se il carattere è  a, e, i, o, u
				 if (cMg.charAt(i) == 97 || cMg.charAt(i) == 101 || cMg.charAt(i) == 105 || cMg.charAt(i) == 111 || cMg.charAt(i) == 117) {
				 totalVowels++;
				 }
				 // Consonanti
				 else {
				 totalConsonants++;
				 }
				 }
				 // Controllo se il carattere è compreso tra 0 e 9
				 else if (cMg.charAt(i) >= 48 && cMg.charAt(i) <= 57) {
				 totalDigits++;
				 }
				 // Controllo se ci sono caratteri speciali
				 else {
				 totalSpecialCharacters++;
				 }
				 }
				System.out.println("Server: Numero totale di vocali nella stringa data: " + totalVowels);
				System.out.println("Server: Numero totale di consonanti nella stringa data: " + totalConsonants );
				System.out.println("Server: Numero totale di numeri nella stringa data: " + totalDigits );
				System.out.println("Server: Numero totale di caratteri nella stringa data: " + totalSpecialCharacters );
			    outStream.writeUTF("Echo from server : "         + clientMessage);
		        outStream.flush();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}