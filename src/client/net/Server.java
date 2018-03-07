package client.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import client.Game;
import client.net.Packets.ServerPacket;
import client.world.World;



public class Server  {


	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;
	static int TOT = 10;
	static Users[] user = new Users[TOT];
	
	public static void main(String[] args) throws IOException {
	
		System.out.println("Starting server... ");
		serverSocket  = new ServerSocket(5656);
		System.out.println("Server started!");
		
		while(true) {
			socket = serverSocket.accept();
			for(int i=0; i<TOT;i++) {
				System.out.println("Connection from: "+socket.getInetAddress());
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				
				if( user[i] == null) {
					user[i] = new Users(out,in,user);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
				
			}
		}
		
	}

}

class Users implements Runnable{


	static int TOT = 10;
	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[TOT];
	String name ;

	public Users(DataOutputStream out, DataInputStream in, Users[] user){
		this.out=out;
		this.in=in;
		this.user=user;
	}
	
	@Override
	public void run() {
		
//		try {
//			//name =in.readUTF();
//		} catch (IOException e1) {
//			//e1.printStackTrace();
//		}
		
		
		while(true) {
			try {
				 ServerPacket sp = new ServerPacket(new World("res/worlds/world2.txt"));
				
				out.write(sp.getBytes());
				out.flush();
				
			} catch (IOException e) {
				//e.printStackTrace();
				this.out=null;
				this.in =null;
			}
		}
	}
	
}

