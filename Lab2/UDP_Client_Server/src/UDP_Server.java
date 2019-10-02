import java.io.IOException;
import java.io.*;
import java.net.*;
import java.math.*;
public  class UDP_Server
{
    public static void main(String[] args) throws IOException, InterruptedException, SocketException {
        new UDP_Server();
    }
    double sum1=0,sum2=0;
    DatagramSocket st;
    byte [] buf=new byte[100];
    DatagramPacket dp=new DatagramPacket(buf,100);

    private UDP_Server() throws IOException, InterruptedException, SocketException {
        st=new DatagramSocket(12345);
        listen();
    }

    private void listen() throws IOException, InterruptedException, IOException {
        int a,b,c;
        st.receive(dp);
        String str=new String(dp.getData());
        str=str.substring(0, str.indexOf('\n'));
        System.out.println("Число "+str+" принято как A");
        a=Integer.parseInt(str);
        st.receive(dp);
        str=new String(dp.getData());
        str=str.substring(0, str.indexOf('\n'));
        System.out.println("Число "+str+" принято как B");
        b=Integer.parseInt(str);
        st.receive(dp);
        str=new String(dp.getData());
        str=str.substring(0, str.indexOf('\n'));
        System.out.println("Число "+str+" принято как C");
        c=Integer.parseInt(str);
        Thread t1=new Thread(new Runnable()
        {
            public void run()
            {
                for(int i=a;i<=b;i++)
                {
                    sum1+=(3.0*i)/(2.0*i+1);
                }
            }
        })
                ,t2=new Thread(new Runnable()
        {
            public void run()
            {
                for(int i=b;i<=c;i++)
                {
                    sum2+= Math.pow(i,2)+4.0*i+1.0;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        sendBack(dp);
    }
    private void sendBack(DatagramPacket d) throws IOException
    {
        String str=String.valueOf(sum1+sum2);
        byte [] send=str.getBytes();
        dp=new DatagramPacket(send, send.length, d.getAddress(),d.getPort() );
        st.send(dp);
        st.close();
    }
}
