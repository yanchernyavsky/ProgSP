
import java.net.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class main
{
    public static void main(String[] args) throws IOException
    {
        Queue am  = new Queue(250);
        ticketWindow firstCashier = new ticketWindow("Первый кассир", 200, am);
        ticketWindow secondCashier = new ticketWindow("Второй кассир", 250, am);
        ticketWindow thirdCashier = new ticketWindow("Третий кассир", 280, am);

        firstCashier.start();
        secondCashier.start();
        thirdCashier.start();





        System.in.read();

    }
}
