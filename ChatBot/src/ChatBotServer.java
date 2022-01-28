import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author SantiagoManrique
 *
 */
public class ChatBotServer extends Thread {
	private Socket socket;

	
	public ChatBotServer(Socket socket) {
		super();
		this.socket = socket;
	}

	public void run() {
		try {
		//leer la pregunta del cliente
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		String inputString = input.readLine();
		
		
		//creamos el stream de salida
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		if(inputString.equals("Buenos dias")) {
			out.println("Ya te  dije Buenos dias antes no seas pesado.\n");
			
		}else if(inputString.equals("Que dia es hoy") ) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
			out.println("Hoy es " +dtf.format(LocalDateTime.now())+"\n");
			
		}else if(inputString.equals("Que tiempo hace hoy") ) {
			out.println("Si es Verano calor, si es invierno frio.\n");
			
		}else if(inputString.equals("Como te llamas") ) {
			out.println("Soy Jexi tu asisitente virtual \n");
			
		}else if(inputString.equals("Cual es tu color favorito") ) {			
			out.println("No tengo color favorito, soy un programa\n");
			
		}else {
			out.println("Lo siento, no te he entendido intentalo otra vez.\n");
		}
		//cierro el socket
		socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9999);
		System.out.println("Servidor Iniciado");
		try {
		while(true) {
					
			Socket socket = listener.accept();
			ChatBotServer  hilo = new ChatBotServer(socket);
			hilo.start();
		}
		
		}finally {
			//cierro el listener
			 listener.close();
		}
	}
}

