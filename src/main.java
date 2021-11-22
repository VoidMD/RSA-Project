import java.util.Scanner;
import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        String FilePath;
        Scanner input = new Scanner(System.in);
        Scanner FileRead = null;

            System.out.println("please enter file path :");

                FilePath = input.nextLine();

                FileRead = new Scanner(new FileInputStream(new File(FilePath)));

                while (FileRead.hasNext()){
                    System.out.println(FileRead.next());
                }


    }

    public void Encryption(){

    }

    public void Decryption(){

    }

}
