import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * @author SantiagoManrique
 * Cliente para el servidor de ChatBot.Inicialmente requiere la dir IP del servidor, el cual
 * estara escuchando en el puerto 9999. Despues te saludara y quedara a la espera de que el cliente escriba
 * tras lo cual devolvera una respuesta dependiendo de lo escrito.
 */

public class ChatBotClient {

	public static void main(String[] args) {
		
		//pedir Ip
		String serverAddress = System.console().readLine("Introduce la IP del Servidor del ChatBot.\n");
		System.out.println("Bienvenido al Servidor de ChatBot SM.\n"
				+ "Este sistema tiene configurado 5 posibles respuestas para lo que deberas formular las siguientes preguntas\n"
				+ "1.Buenos dias\n"
				+ "2.Que tiempo hace hoy\n"
				+ "3.Que dia es hoy\n"
				+ "4.Como te llamas\n"
				+ "5.Cual es tu color favorito\n");
		
		while(true) {
			
			//saludar al cliente y solicitar respuesta
			System.out.println("\n");
						
			String question = System.console().readLine("Hola Buenos Dias\n En que puedo ayudarte.\n ");
			
			//creamos el socket
			try {
				Socket socket = new Socket(serverAddress, 9999);
				
				//enviar pregunta al servidor
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println(question);
				
				//recibir la respuesta del servidor
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String answer = input.readLine();
				
				//Imprimir el mensaje
				System.out.println(answer);
				
				//cerrar el socket
				socket.close();
						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}

}
