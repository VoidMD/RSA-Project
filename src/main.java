import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        String FilePath;
        Scanner input = new Scanner(System.in);
        Scanner FileRead = null;
        int e= 0 ,i = 0;
        long n= 0;
        boolean flag = true;

        do {
            try {
                switch (getMenuChoice()) {
                    case 1:
                        System.out.println("please enter file path :");

                        FilePath = input.nextLine();

                        FileRead = new Scanner(new FileInputStream(new File(FilePath+".txt")));

                        e = FileRead.nextInt();
                        n = FileRead.nextLong();
                        FileRead.nextLine();

                        // to split every letter in one word ipsum --> i p s u m
                        while (FileRead.hasNext()){
                            String word = FileRead.next();
                            for (i = 0 ; i<word.length();i++) {
                                System.out.println(word.charAt(i));
                            }
                            i=0;
                        }


                        System.out.println(e);
                        System.out.println(n);



                        break;

                    case 2:

                        break;

                    case 3:
                        System.out.println("Terminating ... ");
                        System.exit(0);
                        break;
                }
            }catch(Exception ex){
                System.err.println("Error: Wrong operation! "+ex);
            }
        } while (flag) ;

/*
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

*/
    }

    public void Encryption(){

    }

    public void Decryption(){

    }

    public static  int  getMenuChoice(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do{
            System.out.println("Please select the operation: ");
            System.out.println("1. Encryption");
            System.out.println("2. Decryption");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            if(choice < 1 || choice > 3)
                System.err.println("Error: Wrong operation!");
        }while(choice < 1 || choice > 3);

        return choice;
    }

}
