import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        String FilePath;
        Scanner input = new Scanner(System.in);
        Scanner FileRead = null;
        int e= 0;
        long n= 0;

            System.out.println("please enter file path :");

                FilePath = input.nextLine();

                FileRead = new Scanner(new FileInputStream(new File(FilePath+".txt")));

                    e = FileRead.nextInt();
                    n = FileRead.nextLong();
                    FileRead.nextLine();

                    while (FileRead.hasNext()){
                        System.out.println(FileRead.nextLine());
                    }


                System.out.println(e);
                System.out.println(n);


    }

    public void Encryption(){

    }

    public void Decryption(){

    }

}
