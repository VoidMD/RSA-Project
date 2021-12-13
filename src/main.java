import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) {
        String FilePath;
        Scanner input = new Scanner(System.in);
        Scanner FileRead = null;
        FileWriter writer = null;
        File out = null;
        int i = 0;
        long n,e= 0;
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
                        writer = new FileWriter(out = new File("output.txt"));

                        e = FileRead.nextLong();
                        n = FileRead.nextLong();

                        // to split every letter in one word ipsum --> i p s u m
                        // also to convert every letter to a number
                        while (FileRead.hasNextLine()){
                            String word = FileRead.nextLine();
                            for (i = 0 ; i<word.length();i++) {
                                System.out.println(word.charAt(i));

                                // to add 0 if the string value of the index has length 1 A = 0 ----> A = "00"
                                if(String.valueOf(letterConv().indexOf(word.charAt(i))).length()<2) {
                                    tmp = tmp + "0"+letterConv().indexOf(word.charAt(i));
                                }else{
                                    tmp = tmp + letterConv().indexOf(word.charAt(i));
                                }


                            }
                            i=0;
                        }

                        if (tmp.length()%2!=0){
                            tmp=tmp+letterConv().indexOf('X');
                            System.out.println("Added X");
                        }

                        int blockSize=0;
                        if (String.valueOf(n).length()%2!=0){
                            blockSize = String.valueOf(n).length()+1;
                        }else {
                            blockSize=String.valueOf(n).length();
                        }

                        //to encrypt and add each block to Block arraylist
                        for (i = 0;i<tmp.length();i+=String.valueOf(n).length()){
                            System.out.println(tmp.substring(i,i+String.valueOf(n).length()+1));
                            Blocks.add(String.valueOf(Encryption(Long.parseLong(tmp.substring(i,i+String.valueOf(n).length()+1)),e, n)));
                        }


                        for (i = 0 ; i < Blocks.size();i++){
                                writer.write(Blocks.get(i));
                        }

                        // writer.write(Blocks.toString());
                      //  System.out.println(e);
                       // System.out.println(n);
                       // System.out.println("output.rsa file generated");
                        System.out.println(tmp);
                        writer.close();



                        break;

                    case 2:
                        System.out.println(letterConv().indexOf('-'));
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

    public static long Encryption(long M ,long e ,long n){
            
        return modulo(M,e,n);
    }

    public void Decryption(){

    }

    public static long modulo(long a,long b,long c) {
        long x=1;
        long y=a;
        while(b > 0){
            if(b%2 == 1){
                x=(x*y)%c;
            }
            y = (y*y)%c;
            b /= 2;
        }
        return x%c;
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
