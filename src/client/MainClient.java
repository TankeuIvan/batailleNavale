package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {

	public MainClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Socket s = new Socket("localhost", 1234);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()) );
		Scanner scan = new Scanner(System.in);
		OutputStream os = s.getOutputStream();
		while(true) {
		String nom = scan.nextLine();
		String reponseS = br.readLine().toString();
		System.out.println(reponseS);
		//scan.close();
		}
		
	}

}
