import java.math.BigInteger;
import java.sql.SQLOutput;
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
                        //Encryption
                        System.out.println("###################################Encryption###################################");
                        ArrayList<String> EncBlocks = new ArrayList<>();
                        String tmp = "";
                        System.out.println("please enter file path :");

                        FilePath = input.nextLine();

                        FileRead = new Scanner(new FileInputStream((FilePath+".txt")));
                        writer = new FileWriter(out = new File("output.txt"));

                        e = FileRead.nextLong();
                        n = FileRead.nextLong();

                        System.out.println("File has been found successfully");
                        System.out.println("e = "+e);
                        System.out.println("n = "+n);

                        // to split every letter in one word ipsum --> i p s u m
                        // also to convert every letter to a number
                        while (FileRead.hasNextLine()){
                            String word = FileRead.nextLine();
                            for (i = 0 ; i<word.length();i++) {
                                System.out.println(word.charAt(i));

                                // to add 0 if the string value of the index has length 1 A = 0 ----> A = "00"
                                if(String.valueOf(letterConv().indexOf(word.charAt(i))).length()<2) {
                                    tmp = tmp + "0" + letterConv().indexOf(word.charAt(i));
                                }else{
                                    tmp = tmp + letterConv().indexOf(word.charAt(i));
                                }

                            }
                            i=0;
                        }

                        /* if (tmp.length()%2!=0){
                            tmp=tmp+letterConv().indexOf('X');
                            System.out.println("Added X");
                        }
                       */
                        String blockSize = "";
                        while ((blockSize).length()<String.valueOf(n).length()){
                                blockSize = blockSize + letterConv().indexOf('\n');
                               System.out.println(blockSize);

                        }


                        //to encrypt and add each block to Block arraylist
                        for (i = 0;i<tmp.length();i+=blockSize.length()){
                            System.out.println(tmp.substring(i,i+blockSize.length()));
                            EncBlocks.add(tmp.substring(i,i+blockSize.length()));
                        }
                        /*
                        if(String.valueOf(Decryption(Long.parseLong(DecBlocks.get(i)), d, n)).length()%blockSize.length()!=0){
                            for (int j = 0 ; j < blockSize.length()-(String.valueOf(Decryption(Long.parseLong(DecBlocks.get(i)), d, n)).length()%blockSize.length());j++ ) {
                                DecTmp = DecTmp + "0";
                                System.out.println("Added 0");
                            }}
                        */

                        for (i = 0 ; i < EncBlocks.size();i++){
                            if (String.valueOf(Encryption(Long.parseLong(EncBlocks.get(i)), e, n)).length()%blockSize.length()!=0){
                                String s1 ="";
                                for(int j = 0; j<blockSize.length()-(String.valueOf(Decryption(Long.parseLong(EncBlocks.get(i)), e, n)).length()%blockSize.length());j++){
                                    s1+="0";
                                }
                                writer.write( s1+Encryption(Long.parseLong(EncBlocks.get(i)), e, n));
                                System.out.println("Added 0");

                            }else {
                                writer.write(String.valueOf(Encryption(Long.parseLong(EncBlocks.get(i)), e, n)));
                            }

                        }

                        // writer.write(EncBlocks.toString());
                      //  System.out.println(e);
                       // System.out.println(n);
                       // System.out.println("output.rsa file generated");
                        System.out.println(tmp);
                        writer.close();



                        break;

                    case 2:
                        ArrayList<String> DecBlocks = new ArrayList<>();
                        String DecTmp = "";

                        //Decryption
                        System.out.println("please enter file path :");

                        FilePath = input.nextLine();

                        FileRead = new Scanner(new FileInputStream((FilePath+".txt")));
                        writer = new FileWriter(out = new File("output1.txt"));

                        System.out.println("Enter the value of d :");
                        long d = input.nextLong();
                        System.out.println("Enter the value of n :");
                        n = input.nextLong();

                        tmp = "";
                        while (FileRead.hasNextLine()){
                            tmp = tmp + FileRead.nextLine();
                        }
                        System.out.println("tmp: "+tmp);


                        blockSize = "";
                        while ((blockSize).length()<String.valueOf(n).length()){
                            blockSize = blockSize + letterConv().indexOf('\n');
                            System.out.println(blockSize);

                        }

                        while (tmp.length()%blockSize.length()!=0){
                            tmp= "0"+tmp.substring(0);
                            System.out.println("0 added");
                        }

                        for (i = 0;i<tmp.length();i+=blockSize.length()){
                            System.out.println(tmp.substring(i,i+blockSize.length()));
                            DecBlocks.add(tmp.substring(i,i+blockSize.length()));
                        }

                        for (i = 0 ; i < DecBlocks.size();i++){
                            if(String.valueOf(Decryption(Long.parseLong(DecBlocks.get(i)), d, n)).length()%blockSize.length()!=0){
                               for (int j = 0 ; j < blockSize.length()-(String.valueOf(Decryption(Long.parseLong(DecBlocks.get(i)), d, n)).length()%blockSize.length());j++ ) {
                                   DecTmp = DecTmp + "0";
                                   System.out.println("Added 0");
                               }}
                           DecTmp = DecTmp + Decryption(Long.parseLong(DecBlocks.get(i)), d, n);

                            System.out.println(DecTmp);
                        }

                        System.out.println(DecTmp);

                        for (i = 0;i<DecTmp.length();i+=2){
                            writer.write(letterConv().get(Integer.parseInt(DecTmp.substring(i,i+2))));
                            System.out.println(letterConv().get(Integer.parseInt(DecTmp.substring(i,i+2))));
                        }

                        writer.close();

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

    public static long Decryption(long c, long d, long n){

        return modulo(c,d,n);
    }
/*
    public static long modulo(double a,long b,long c) {
        long x=1;
        double y=a;
        while(b > 0){
            if(b%2 == 1){
                x= (long) ((x*y)%c);
            }
            y = (y*y)%c;
            b /= 2;
        }
        return x%c;
    }
*/

    public static long modulo(long a, long b,long n) {

        BigInteger x = new BigInteger(Long.toString(n));
        BigInteger y = new BigInteger("1");
        BigInteger pow = new BigInteger(Long.toString(a % n));
        String s = Long.toBinaryString(b);
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                y = y.multiply(pow).mod(x);
            }
            pow = pow.multiply(pow).mod(x);
        }
        return y.longValue();
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
