package RemoteDesktop;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author amit
 */
public class Server{
    public static ServerSocket serverSocket = null;
    public static Socket clientSocket = null;
    public static InputStream is = null;
    public static OutputStream os = null;
    public static ObjectOutputStream objectOutputStream = null;
    public static ObjectInputStream objectInputStream = null;
    
    Server(InetAddress ip, int port){
       try{
                serverSocket = new ServerSocket(port);
                new Thread(){
                    @Override
                    public void run(){
                        															//initiate  the server:
                        try{
                            startServer(port);
                        } catch (Exception e){
                            RemoteDesktop.info.setText("Error starting the server");
                        }
                }}.start();
                
        } catch(Exception e){
            RemoteDesktop.info.setText("Error in initializing server");
        }
    }
    
    void startServer(int port) throws IOException{
        String message;
        
        try{
            clientSocket = serverSocket.accept();
            InetAddress remoteInetAddress = clientSocket.getInetAddress();
            new Android().connectToAndroid(remoteInetAddress, port);

            RemoteDesktop.conn.setText("Connection Status : Connected");
            RemoteDesktop.info.setText("Connected to Android!");
            RemoteDesktop.start.setEnabled(false);
            RemoteDesktop.reset.setEnabled(true);

            MouseKey control = new MouseKey();

            is = clientSocket.getInputStream();
            os = clientSocket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(os);
            objectInputStream = new ObjectInputStream(is);

            while (true){
                    try{
                        message = (String) objectInputStream.readObject();
                        int keyCode;
                        if (message != null){
                            switch (message){
                                case "FIREFOX": control.launch(1);
                                              break;
                                case "GEDIT": control.launch(2);
                                              break;
                                case "TERMINAL": control.CtrlAltT();
                                              break;
                                case "FILE": control.launch(3);
                                              break;
                                case "KEY_PRESS":
                                    keyCode = (int) objectInputStream.readObject();
                                    control.keyPress(keyCode);
                                    break;
                                case "KEY_RELEASE":
                                    keyCode = (int) objectInputStream.readObject();
                                    control.keyRelease(keyCode);
                                    break;
                                case "CTRL_ALT_T":
                                    control.CtrlAltT();
                                    break;
                                case "CTRL_SHIFT_Z":
                                    control.CtrlShiftZ();
                                    break;
								case "ShiftF5":
                                    control.ShiftF5();
                                    break;
                                case "ALT_F4":
                                    control.AltF4();
                                    break;
                                case "TYPE_CHARACTER": 
                                    char ch = ((String) objectInputStream.readObject()).charAt(0);
                                    control.type(ch);
                                    break;
                                case "TYPE_KEY": 
                                    keyCode = (int) objectInputStream.readObject();
                                    control.type(keyCode);
                                    break;
                                case "LEFT_ARROW_KEY":
                                    control.pressLeftArrowKey();
                                    break;
                                case "DOWN_ARROW_KEY":
                                    control.pressDownArrowKey();
                                    break;
                                case "RIGHT_ARROW_KEY":
                                    control.pressRightArrowKey();
                                    break;
                                case "UP_ARROW_KEY":
                                    control.pressUpArrowKey();
                                    break;
                                case "F5":
                                    control.pressF5Key();
                                    break;
                                case "LEFT":
                                    control.LeftClick();
                                    break;
                                case "LEFT_P":
                                    control.LeftP();
                                    break;
                                case "LEFT_R":
                                    control.LeftR();
                                    break;
                                case "RIGHT":
                                    control.RightClick();
                                    break;
                                case "DOUBLE":
                                    control.DoubleClick();
                                    break; 
                                case "MOUSE_MOVE":
                                    int x = (int) objectInputStream.readObject();
                                    int y = (int) objectInputStream.readObject();
                                    Point p = MouseInfo.getPointerInfo().getLocation(); //current  position
                                    float X = p.x;
                                    float Y = p.y;
                                    control.Move((int) (X + x), (int) (Y + y));
                                    break;
                            }
                        } else{
                            connectionClosed();
                            RemoteDesktop.conn.setText("Connection Status : NOT Connected");
                            RemoteDesktop.info.setText(" Press Connect to connect to Android ...");
                            break;
                        }
                    } catch (Exception e){
                        connectionClosed();
                        Android.closeConnectionToAndroid();
                        RemoteDesktop.conn.setText("Connection Status : NOT Connected");
                        RemoteDesktop.info.setText(" Press Connect to connect to Android ...");
                        RemoteDesktop.start.setEnabled(true);
                        RemoteDesktop.reset.setEnabled(false);
                        break;
                    }
                };

        } catch(Exception e){
            RemoteDesktop.info.setText("Error in closing server");
        }
    }
   
    static void connectionClosed(){
        try{
            objectInputStream.close();
            clientSocket.close();
            serverSocket.close();
            is.close();
            os.close();
            objectOutputStream.close();
        } 
        catch(Exception e){
            RemoteDesktop.info.setText("Error in closing server");
        }
    }
}
