
import java.net.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class main
{
    public static void main(String[] args) throws IOException
    {
        Queue am  = new Queue(50);
        ticketWindow firstCashier = new ticketWindow("firstCashier", 200, am);
        ticketWindow secondCashier = new ticketWindow("secondCashier", 250, am);
        ticketWindow thirdCashier = new ticketWindow("thirdCashier", 280, am);

        firstCashier.start();
        secondCashier.start();
        thirdCashier.start();





        System.in.read();

    }
}
