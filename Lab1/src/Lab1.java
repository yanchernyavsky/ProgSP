import java.io.*;
import java.util.Arrays;

public class Lab1 {

    String date;
    int numberOFplayers;
    boolean winnercup;

    Lab1() throws IOException {
        String bolv;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\nВведите дату проведения: ");
        this.date = input.readLine();
        System.out.print("\nВведите количесво учасников: ");
        this.numberOFplayers = Integer.parseInt(input.readLine());

        while (true) {
            System.out.print("\nНаличие приза (Да/Нет): ");
            bolv = input.readLine().toUpperCase();
            System.out.print(bolv);
            if ("ДА".equals(bolv)) {
                this.winnercup = true;
                break;
            }
            if ("НЕТ".equals(bolv)) {
                this.winnercup = false;
                break;
            }
            System.out.print("\nОшибка! Повторите ввод");
        }
    }

    //записываем информацию в файл document.doc
    public void InputInFile() throws IOException {
        File file = new File("document.doc");
        //file.deleteOnExit(); //файл удалится после завершения работы виртуальной машины Java
        //поток для записи в файл
        FileWriter writer;
        writer = new FileWriter(file, false);
        writer.append("\nДата соревнований " + this.date + "; Количество учасников: " + this.numberOFplayers + "; ");
        if (this.winnercup) {
            writer.append("есть приз.\n");
        } else {
            writer.append("нет приза.\n");
        }
        writer.flush();
        writer.close();
    }

    //статический метод вывода информации из файла
    public static void OutputOfFile() throws IOException {
        File file = new File("document.doc");
        //поток для вывода информации
        FileReader reader;
        char buffer[];
        int numb;
        buffer = new char[1];
        reader = new FileReader(file);
        do {
            numb = reader.read(buffer);
            System.out.print(buffer[0]);
        } while (numb == 1);
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        int AllPlayers=0;
        int CountOfCups=0;
        Lab1[] races;
        races = new Lab1[3];
        for (int i = 0; i < 3; i++) {
            races[i] = new Lab1();
            AllPlayers += races[i].numberOFplayers;
            if (races[i].winnercup){
                CountOfCups++;
            }
        }
        for (int i = 0; i < 3; i++) {
            races[i].InputInFile();
        }
        OutputOfFile();
        System.out.println("\nОбщее количество участников: " + AllPlayers + "; Соревнований с наличием приза: " + CountOfCups +". ");
    }

}
