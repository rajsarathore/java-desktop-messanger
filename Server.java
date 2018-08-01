 import java.io.*;
import java.net.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;

public class Server
{
     
      static ServerSocket server=null;
      static Socket socket=null;
      static ClientThread th[]=new ClientThread[10];
      public static void main(String args[])
      {
            int port=1234;
            System.out.println("Server started...");
            System.out.println("  [Press Ctrl C to terminate ]");
            try
            {
                  server=new ServerSocket (port);
            }
            catch(IOException e)
            {
                  System.out.println("Exception for Input/Output");
            }
            while(true)
            {
                  try
                  {
                        socket=server.accept();
                        for(int i=0;i<=9;i++)
                        {
                              if(th[i]==null)
                              {    
                                    (th[i]=new ClientThread(socket,th)).start();
                                    break;
                              }
                        }
                        }
                        catch(IOException e)
                        {
                              System.out.println("Exception for Input/Output");
                        }
                  }
            }
      }

      class ClientThread extends Thread  implements ActionListener
      {
             JFrame jf;
             JTextField af ;
             JTextArea  ar;
             String msg=null;
             Boolean b;
            Socket socket=null;
            ClientThread th[];
            public ClientThread(Socket socket,ClientThread[] th)
            {    
                  this.socket=socket;
                  this.th=th;
                  jf=new JFrame();
                  af=new JTextField();
                  ar = new JTextArea();
                  ar.setEditable(false);
                  af.addActionListener(this);
                  jf.add(af,BorderLayout.SOUTH);
                  jf.add(ar,BorderLayout.CENTER);
                  jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
                  jf.setSize(400,500);
                  jf.setVisible(true);
                  jf.add(new JScrollPane(ar));
            }
            public void run()
            {
                  
                  String username;
                  try
                  {
                     
                        ar.append("What is your Name?Enter it-");
                         b=true;
                         while(b);
                         username=msg;
                         
                         ar.append("\n"+username + ":Welcome to chat room.");
                         ar.append("\nTo leave chat room type $$");
                        
                        for (int i = 0; i <= 9; i++)
                              if (th[i] != null && th[i] != this)
                                    th[i].ar.append("\n------------A new user arrived in chat Room:" + username);
                              while (true)
                              {
                                   b=true;
                                  while(b);
                                    if (msg.startsWith("$$"))
                                          break;
                                    for (int i = 0; i <= 9; i++)
                                          if (th[i] != null && th[i]!=this){
                                                th[i].ar.append("\n<" + username + ">" + msg) ;
                                          }
                              }
                              for (int k = 0; k <= 9; k++)
                                    if (th[k] != null && th[k] != this)
                                          th[k].ar.append("\n------A user Leaving chat Room:" + username + "--------");
                              jf.setVisible(false);
                              for (int j = 0; j <= 9; j++)
                                    if (th[j] == this)
                                          th[j] = null;
                              
                  }catch(Exception e)
                  {
                        System.out.println("Exception for Input/Output");
                  }
                  }
                  public void actionPerformed(ActionEvent e)
                  {
                        try{
                      if(e.getSource()==af){
                          msg=af.getText();
                          ar.setForeground(Color.red);
                         ar.append("\n\t\t< ME >" + msg);
                          af.setText("");
                          b=false;
                      }}catch(Exception err){}
                       }
            }
