
import java.net.*;
import java.io.*;

class TCP_Server {
    static int countclients = 0;//счетчик подключившихся клиентов
    public static void main(String args[]) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            sock = new ServerSocket(1024); //создаем серверный сокет работающий локально по порту 1024
            while (true) { //бесконечный цикл для возможности подключения последовательно нескольних клиентов
                Socket client = sock.accept(); //сработает, когда клиент подключится,
//                для него выделится отдельный сокет client
                countclients++; //количество подключившихся клиентов увеличивается на 1
                System.out.println("=======================================");
                System.out.println("Client " + countclients + " connected");
                is = client.getInputStream(); //получили входной поток для чтения данных
                os = client.getOutputStream();//получили выходной поток для записи данных
                boolean flag = true;
                while (flag) {
                    byte[] bytes = new byte[1024];
                    is.read(bytes); //чтение иформации, посланной клиентом, из вхоного потока в массив bytes[]
                    String str = new String(bytes, "UTF-8"); // переводим тип byte в тип String
                    String[] numbers = str.split(" "); // разбиваем строку на подстроки пробелами
                    String m = ""; //переменнная,в которую будут записываться числа делящиеся на 3
                    bytes = new byte[1024];
                    for (int i = 0; i < numbers.length - 1; i++) {
                        System.out.println("клиент прислал число " + numbers[i]);
                        if (Integer.parseInt(numbers[i]) % 3 == 0) {
                            m += numbers[i] + " "; // записываютмя числа,которые деляется на 3
                        }
                    }
                    bytes = m.getBytes();
                    os.write(bytes); // отправляем клиенту информацию
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            is.close();//закрытие входного потока
            os.close();//закрытие входного потока
            sock.close();//закрытие сокета, выделенного для работы с подключившимся клиентом
            System.out.println("Client " + countclients + " disconnected");
        }
    }}
