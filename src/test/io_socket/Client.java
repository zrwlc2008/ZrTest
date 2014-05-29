package test.io_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			
			while(true){
				
				// 创建socket对象，并指明服务器的IP地址和端口号
				socket = new Socket("localhost", 3081);
				
				// 得到socket发送数据的输出流
				OutputStream out = socket.getOutputStream();
				// 将字节流包装成字符流
				pw = new PrintWriter(out);
				
				
				System.out.print("已连接上服务器，请输入内容：");
				//从控制台输入发送内容
				BufferedReader systemBR = new BufferedReader(new InputStreamReader(System.in));
				String str = systemBR.readLine();
				// 向服务器发送数据
				pw.println(str);
				// 刷新流，确保数据能写到服务器
				pw.flush();

				System.out.println("waiting answer....");
				
				InputStream in = socket.getInputStream();

				br = new BufferedReader(new InputStreamReader(in));
				String info = br.readLine();
				System.out.println(info);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}