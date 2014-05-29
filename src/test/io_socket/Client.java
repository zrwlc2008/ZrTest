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
				
				// ����socket���󣬲�ָ����������IP��ַ�Ͷ˿ں�
				socket = new Socket("localhost", 3081);
				
				// �õ�socket�������ݵ������
				OutputStream out = socket.getOutputStream();
				// ���ֽ�����װ���ַ���
				pw = new PrintWriter(out);
				
				
				System.out.print("�������Ϸ����������������ݣ�");
				//�ӿ���̨���뷢������
				BufferedReader systemBR = new BufferedReader(new InputStreamReader(System.in));
				String str = systemBR.readLine();
				// ���������������
				pw.println(str);
				// ˢ������ȷ��������д��������
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