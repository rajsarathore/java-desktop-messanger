 import java.io.*;
import java.net.*;
public class Client implements Runnable
{
            static Socket socket=null;
            static PrintStream output;
            static BufferedReader input=null;
            static BufferedReader userip=null;
            static boolean flag=false;
            public static void main(String[] args)
            {
                        int port=1234;

                               String host="localhost";

                        try
                        {
                                    socket=new Socket(host,port);
                                    userip=new BufferedReader(new InputStreamReader(System.in));
                                    output=new PrintStream(socket.getOutputStream());
                                    input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        }
                        catch(Exception e)
                        {
                                    System.err.println("Unknown host"+host);
                        }
                        if(socket!=null)
                        {
                                    try
                                    {
                                                new Thread(new Client()).start();
                                                while(!flag)
                                                {
                                                output.println(userip.readLine());
                                                }
                                    output.close();
                                    input.close();
                                    socket.close();
                        }
                        catch(Exception e1)
                        {
                                    System.err.println("IOException"+e1);
                        }
            }
}
public void run()
{
String msg;
try
{
while((msg=input.readLine())!=null)
System.out.println(msg);
flag=true;
}
catch(IOException e)
{
System.err.println("IOException" + e);
}
}
}
