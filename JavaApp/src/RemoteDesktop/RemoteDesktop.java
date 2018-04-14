package RemoteDesktop;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

/**
 *
 * @author amit
 */
public class RemoteDesktop{
    JFrame window;
    JTextField ip, port;
    JLabel ip1, port1;
    static JLabel conn;
    static JTextArea info;
    static JButton start, reset;
    InetAddress localhost = null;
    int portNum;

    RemoteDesktop(){
        window= new JFrame("Remote Desktop");        
        start = new JButton();
        reset = new JButton();
        ip = new JTextField();
        port = new JTextField();        
        ip1 = new JLabel();
        port1 = new JLabel();
        conn = new JLabel();
        info = new JTextArea();

        start.setText("Connect");
        reset.setText("Reset");    
        ip.setEditable(false);
        port.setEditable(false);
        info.setEditable(false);

        start.setBounds(60, 200, 100, 30);
        reset.setBounds(180, 200, 80, 30);
        ip.setBounds(125, 60, 110, 30);
        port.setBounds(125, 100, 110, 30);
        ip1.setBounds(30, 60, 85, 20);
        port1.setBounds(30, 100, 100, 20);
        conn.setBounds(30, 150, 300, 20);
        info.setBounds(20, 300, 300, 50);

        ip1.setText("IP Address:");
        port1.setText("Port Number:");
        conn.setText(" Connection Status: not connected");
        info.setText(" Press Connect to connect to Android ...");
      
        portNum = getFreePort();
        port.setText("" + portNum);
        
        reset.setEnabled(false);
        window.add(ip);
        window.add(port);
        window.add(ip1);
        window.add(port1);
        window.add(ip);
        window.add(conn);
        window.add(info);
        window.add(start);
        window.add(reset);

       // reset.setVisible(false);
        window.setLayout(null);
        window.setResizable(false);
        window.setSize(350, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        if(getIp() == 1){
            ip.setText((localhost.getHostAddress()).trim());
        }
        else
            info.setText(" Connect your PC to Android phone \n via hotspot and \n Press Connect to connect to Android ...");
        
       start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(getIp() == 1){
                    ip.setText((localhost.getHostAddress()).trim());
                    info.setText(" Waiting for connection ...");
                    new Server(localhost, portNum);
                }
                else
                    info.setText(" Connect your PC to Android phone \n via hotspot and \n Press Connect to connect to Android ...");
            }
        });
       reset.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                Server.connectionClosed();
                Android.closeConnectionToAndroid();
                if(getIp() == 1){
                    ip.setText((localhost.getHostAddress()).trim());
                    info.setText(" Waiting for connection ...");
                    new Server(localhost, portNum);
                }
                else
                    info.setText(" Connect your PC to Android phone \n via hotspot and \n Press Connect to connect to Android ...");
            
            }
        });
    }
    
   /* void startS(){
        new Server(this.localhost, this.portNum);

    }*/
    int getIp(){
        try{
            this.localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException ex){
            return 0;
        }
        //System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
        return 1;
    }
    
    private boolean isPortAvailable(int port){
        boolean portAvailable = true;
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
        } catch(Exception e){
            portAvailable = false;
        } finally{
            if (serverSocket != null){
                try{
                    serverSocket.close();
                } catch(Exception e){
                    e.printStackTrace();
                };
            }
        }
        return portAvailable;
    }
    
    public int getFreePort(){
        int port = 3000;
        while (true){
            if (isPortAvailable(port) == true){
                break;
            } else{
                port++;
            }
        }
        return port;
    }
   
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new RemoteDesktop();
            }
        });
    }
}