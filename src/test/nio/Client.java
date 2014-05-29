package test.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
	// �ŵ�ѡ����
	private Selector selector;
	// �������ͨ�ŵ��ŵ�
	SocketChannel socketChannel;

	public Client(int port) throws IOException {
		selector = Selector.open();
		socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress
				.getLocalHost(), port));
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
		// ������ȡ�߳�
		new Thread(new ClientReadThread()).start();
	}
	
	public void sendMsg() throws IOException{
		
		System.out.print("���뷢�����ݣ�");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String message = br.readLine();
		ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-16"));
		socketChannel.write(writeBuffer);
		
	}

	class ClientReadThread implements Runnable {
		public void run() {
			try {
				
				while (selector.select() > 0) {
					
					// ����ÿ���п���IO����Channel��Ӧ��SelectionKey
					for (SelectionKey sk : selector.selectedKeys()) {
						// �����SelectionKey��Ӧ��Channel���пɶ�������
						if (sk.isReadable()) {
							// ʹ��NIO��ȡChannel�е�����
							SocketChannel sc = (SocketChannel) sk.channel();
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							sc.read(buffer);
							buffer.flip();
							// ���ֽ�ת��ΪΪUTF-16���ַ���
							String receivedString = Charset.forName("UTF-16")
									.newDecoder().decode(buffer).toString();
							// ����̨��ӡ����
							System.out.println("���յ����Է�����"
									+ sc.socket().getRemoteSocketAddress()
									+ "����Ϣ:" + receivedString);
							// Ϊ��һ�ζ�ȡ��׼��
							sk.interestOps(SelectionKey.OP_READ);
						}
						// ɾ�����ڴ����SelectionKey
						//selector.selectedKeys().remove(sk);
					}
				}
				
				sendMsg();
				
				System.out.println("listen to server...");
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Client client = new Client(9911);
		client.sendMsg();
	}
}