

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

public class ticketWindow extends Thread
{

    ticketWindow(String name, int time, Queue am)
    {
        super(name);
        perClientTime = time;
        currentClientsAmount = am;
    }
    Queue currentClientsAmount;
    int perClientTime;
    long whole_time = System.currentTimeMillis();
    int clients = 0;
    public void run()
    {
        try
        {
            long tempTime = System.currentTimeMillis();
            while(currentClientsAmount.am > 0)
            {

                    synchronized (currentClientsAmount)
                    {
                        if(currentClientsAmount.am > 0) {
                            currentClientsAmount.am--;
                            clients++;
                        }
                    }
                    Thread.sleep(perClientTime);

            }
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread has been interrupted");
        }
        whole_time -= System.currentTimeMillis();
        System.out.printf(Thread.currentThread().getName()+" выдал талоны " + clients +" пациентам. Время: " + Math.abs(whole_time));
        System.out.printf(", %s закончил работу... \n", Thread.currentThread().getName());
    }
}
