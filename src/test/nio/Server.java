package test.nio;

import java.io.IOException;  
import java.net.InetAddress;  
import java.net.InetSocketAddress;  
import java.nio.ByteBuffer;  
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.ServerSocketChannel;  
import java.nio.channels.SocketChannel;  
import java.nio.charset.Charset;  
import java.util.Date;  
import java.util.Iterator;  
import java.util.Set;

public class Server{  
    private final Selector selector;  
    private final ServerSocketChannel serverSocketChannel;  
      
    public Server(int port) throws IOException{  
        // ����ѡ����  
        selector = Selector.open();  
        // �򿪼����ŵ�  
        serverSocketChannel = ServerSocketChannel.open();  
        InetSocketAddress adress = new InetSocketAddress(InetAddress.getLocalHost(),port);  
        //�뱾�ض˿ڰ�  
        serverSocketChannel.socket().bind(adress);  
        // ����Ϊ������ģʽ  
        serverSocketChannel.configureBlocking(false);  
        // ע��ѡ����.����ע�������ָ�����ŵ����Խ���Accept����  
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);        
    }  
    
    public void start() {  
        System.out.println("the server is started......");  
        while (true) {  
            try {  
                int nKeys = selector.select();
                System.out.println("nKeys=" + nKeys);
                if (nKeys > 0){  
                    // selectedKeys()�а�����ÿ��׼����ĳһI/O�������ŵ���SelectionKey  
                    Set<SelectionKey> scSet = selector.selectedKeys();  
                    System.out.println("scSet.size()=" + scSet.size());
                    Iterator<SelectionKey> iter = scSet.iterator();  
                    while (iter.hasNext()) {  
                        SelectionKey key = (SelectionKey) iter.next();  
                        
                        iter.remove();  
                        dispatch(key);                    
                    }  
                }else{
                	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }             
        }  
    }  
      
    public void dispatch(SelectionKey key) {  
        // �пͻ�����������ʱ  
        //if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {  
        if (key.isAcceptable()) {  
            try {  
                System.out.println("Key is acceptable");  
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();  
                SocketChannel socket = (SocketChannel) ssc.accept();  
                socket.configureBlocking(false);  
                socket.register(selector, SelectionKey.OP_READ);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        // �ӿͻ��˶�ȡ����  
        //else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {  
        else if (key.isReadable()) {  
            System.out.println("the key is readable");  
            // new Thread(new ReadeHandler(key)).start();  
            try {  
                SocketChannel socket = (SocketChannel) key.channel();  
                ByteBuffer buffer = ByteBuffer.allocate(1024);  
                int bytesRead = socket.read(buffer);  
                if (bytesRead > 0) {  
                    buffer.flip();  
                    // ���ֽ�ת��ΪΪUTF-16���ַ���  
                    String receivedString = Charset.forName("UTF-16").newDecoder().decode(buffer).toString();  
                    // ����̨��ӡ����  
                    System.out.println("���յ�����"  
                            + socket.socket()  
                                    .getRemoteSocketAddress()  
                            + "����Ϣ:" + receivedString);  
                    // ׼�����͵��ı�  
                    String sendString = "���, �Ѿ��յ������Ϣ";  
                    
                    buffer = ByteBuffer.wrap(sendString  
                            .getBytes("UTF-16"));  
                    socket.write(buffer);  
                    // ����Ϊ��һ�ζ�ȡ����д����׼��  
                    key.interestOps(SelectionKey.OP_READ);                
                }  
            } catch (IOException e) {  
                //�ͻ��˶Ͽ����ӣ����Դ�Selector��ȡ��ע��   
                key.cancel();  
                if(key.channel() != null)  
                    try {  
                        key.channel().close();  
                        System.out.println("the client socket is closed!");  
                    } catch (IOException e1) {  
                        e1.printStackTrace();  
                    }  
            }  
        }  
        // �ͻ��˿�дʱ  
        else if (key.isWritable()) {  
            System.out.println("tHe key is writable");  
            //new Thread(new WriteHandler(key)).start();  
            //do something  
        }  
    }     
      
    public static void main(String[] args) throws IOException {  
        Server server = new Server(9911);  
        server.start();  
    }  
}