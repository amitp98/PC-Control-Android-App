package RemoteDesktop;

import static RemoteDesktop.Server.is;
import static RemoteDesktop.Server.os;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author amit
 */
public class Android{
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static InputStream inputStream;
    private static OutputStream outputStream;
    public static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
  
    void connectToAndroid(InetAddress ip, int port){
            try{
                SocketAddress socketAddress = new InetSocketAddress(ip, port);
                clientSocket = new Socket();
                clientSocket.connect(socketAddress, 3000);// 3s timeout
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectInputStream = new ObjectInputStream(inputStream);
            } catch(Exception e){
                e.printStackTrace();
            }
    }
    
    public static void closeConnectionToAndroid(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
            if(clientSocket != null){
                clientSocket.close();
            }
            if(is != null){
                is.close();
            }
            if(os != null){
                os.close();
            }
            if(objectOutputStream != null){
                objectOutputStream.close();
            }
            if(objectInputStream != null){
                objectInputStream.close();
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}