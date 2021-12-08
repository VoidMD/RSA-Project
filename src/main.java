import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) {
        String FilePath;
        Scanner input = new Scanner(System.in);
        Scanner FileRead = null;
        FileWriter writer = null;
        File out = null;
        int e= 0 ,i = 0;
        long n= 0;
        boolean flag = true;

        do {
            try {
                switch (getMenuChoice()) {
                    case 1:
                        ArrayList<String> Blocks = new ArrayList<>();
                        String tmp = "";
                        System.out.println("please enter file path :");

                        FilePath = input.nextLine();

                        FileRead = new Scanner(new FileInputStream((FilePath+".txt")));
                        writer = new FileWriter(out = new File("output.rsa"));

                        e = FileRead.nextInt();
                        n = FileRead.nextLong();
                        FileRead.nextLine();

                        // to split every letter in one word ipsum --> i p s u m
                        while (FileRead.hasNextLine()){
                            String word = FileRead.nextLine();
                            for (i = 0 ; i<word.length();i++) {
                                System.out.println(word.charAt(i));
                                tmp = tmp + String.valueOf(letterConv().indexOf(word.charAt(i)));
                                /*
                                if(String.valueOf(letterConv().indexOf(word.charAt(i))).length()<2) {
                                    if (Blocks.get(i-1).length()<4)
                                    Blocks.add(i-1,String.valueOf(Encryption("0" + letterConv().indexOf(word.charAt(i)),e,n)));
                                }else{
                                    Blocks.add(String.valueOf(Encryption(String.valueOf(letterConv().indexOf(word.charAt(i))),e,n)));

                                }

                                 */
                            }
                            i=0;
                        }

                        for (i = 0;i<tmp.length();i+=4){
                            System.out.println(tmp.substring(i,i+4));
                            Blocks.add(tmp.substring(i,i+4));
                        }

                        for (i = 0 ; i < Blocks.size();i++){
                            Encryption(Blocks.get(i),13,2537);
                        }

                        writer.write(Blocks.toString());
                      //  System.out.println(e);
                       // System.out.println(n);
                       // System.out.println("output.rsa file generated");
                        writer.close();



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


    }

    public static Long Encryption(String M ,long e ,long n){
        long m = Integer.parseInt(M);

        return (m^e)%n;
    }

    public void Decryption(){

    }


    public static ArrayList<Character> letterConv(){
        ArrayList<Character> letters = new ArrayList<>();
        for (int i = 65; i <= 90; i++){
            letters.add((char) i);
        }
        for (int i = 97; i <= 122; i++){
            letters.add((char) i);
        }
        for (int i = 48; i <= 57; i++) {
            letters.add((char) i);
        }
        letters.add('.');
        letters.add('?');
        letters.add('!');
        letters.add(',');
        letters.add(';');
        letters.add(':');
        letters.add('-');
        letters.add('(');
        letters.add(')');
        letters.add('[');
        letters.add(']');
        letters.add('{');
        letters.add('}');
        letters.add('\'');
        letters.add('\"');
        letters.add(' ');
        letters.add('\n');
        return letters;
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
