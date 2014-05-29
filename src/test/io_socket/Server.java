package test.io_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			// ����������,������3081�˿�
			ServerSocket server = new ServerSocket(3081);
			while (true) {
				
				System.out.println("wait....");
				
				Thread.currentThread().wait();
				
				// �����������˿ڣ�һ�������ݷ��͹�������ô�ͽ����ݷ�װ��socket����
				// accept()�ᷢ�����������û�����ݷ��͹�������ô��ʱ�����߳�����״̬���������¼���ִ��
				System.out.println("accepting....");
				socket = server.accept();
				
				System.out.println("�������ͻ��ˣ�" + socket.getRemoteSocketAddress());
				
				// ��socket�еõ���ȡ�����������пͻ��˷��͹���������
				InputStream in = socket.getInputStream();
				// InputStreamReader���ֽ���ת��Ϊ�ַ���
				br = new BufferedReader(new InputStreamReader(in));
				// �ж�ȡ�ͻ�������
				String info = br.readLine();
				System.out.println(info);

				OutputStream out = socket.getOutputStream();
				pw = new PrintWriter(out);
				pw.println("������˵���ұ�����");
				pw.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				pw.close();
				br.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}