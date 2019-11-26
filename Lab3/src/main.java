
import java.net.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class main
{
    public static void main(String[] args) throws IOException
    {
        Queue am  = new Queue(100);
        ticketWindow firstCashier = new ticketWindow("Первый врач", 200, am);
        ticketWindow secondCashier = new ticketWindow("Второй врач", 150, am);

        firstCashier.start();
        secondCashier.start();
        System.out.printf("Общее количество талонов: 100\n");
        System.in.read();

    }
}
