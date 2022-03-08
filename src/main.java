import java.math.BigInteger;
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
                        System.out.println("Please enter the full file path (/Users/.../input.txt): ");

                        FilePath = input.nextLine();

                        FileRead = new Scanner(new FileInputStream((FilePath.substring(FilePath.lastIndexOf("/")+1,FilePath.indexOf("."))+".txt")));
                        writer = new FileWriter(out = new File((FilePath.substring(FilePath.lastIndexOf("/")+1,FilePath.indexOf("."))+".rsa")));

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

                                // to add 0 if the string value of the index has length 1 A = 0 ----> A = "00"
                                //tmp string that has the letter after converting to digits
                                if(String.valueOf(letterConv().indexOf(word.charAt(i))).length()<2) {
                                    tmp = tmp + "0" + letterConv().indexOf(word.charAt(i));
                                }else{
                                    tmp = tmp + letterConv().indexOf(word.charAt(i));
                                }

                            }
                            i=0;
                        }

                        //to set the block size according to n
                        String blockSize = "";
                        while ((blockSize).length()<String.valueOf(n).length()){
                                blockSize = blockSize + letterConv().indexOf('\n');

                        }

                        // adding block to array list
                        for (i = 0;i<tmp.length();i+=blockSize.length()){
                            EncBlocks.add(tmp.substring(i,i+blockSize.length()));
                        }

                        //encrypting each block and make sure that block size after encrypting = number of digits in n
                        for (i = 0 ; i < EncBlocks.size();i++){
                            if (String.valueOf(Encryption(Long.parseLong(EncBlocks.get(i)), e, n)).length()%blockSize.length()!=0){
                                String s1 ="";
                                for(int j = 0; j<blockSize.length()-(String.valueOf(Decryption(Long.parseLong(EncBlocks.get(i)), e, n)).length()%blockSize.length());j++){
                                    s1+="0";
                                }
                                writer.write( s1+Encryption(Long.parseLong(EncBlocks.get(i)), e, n));

                            }else {
                                writer.write(String.valueOf(Encryption(Long.parseLong(EncBlocks.get(i)), e, n)));
                            }

                        }

                        System.out.println("Generating the file ...");
                       System.out.println((FilePath.substring(FilePath.lastIndexOf("/")+1,FilePath.indexOf("."))+".rsa") + " file has been generated");
                        writer.close();

                        System.exit(0);

                        break;

                    case 2:
                        ArrayList<String> DecBlocks = new ArrayList<>();
                        String DecTmp = "";

                        System.out.println("###################################Decryption###################################");

                        //Decryption
                        System.out.println("Please enter the full file path (/Users/.../input.txt): ");

                        FilePath = input.nextLine();

                        FileRead = new Scanner(new FileInputStream((FilePath.substring(FilePath.lastIndexOf("/")+1,FilePath.indexOf("."))+".rsa")));
                        writer = new FileWriter(out = new File((FilePath.substring(FilePath.lastIndexOf("/")+1,FilePath.indexOf("."))+".dec")));

                        System.out.println("File has been found successfully");

                        System.out.println("Enter the value of d :");
                        long d = input.nextLong();
                        System.out.println("Enter the value of n :");
                        n = input.nextLong();

                        //read the file and store it in string tmp
                        tmp = "";
                        while (FileRead.hasNextLine()){
                            tmp = tmp + FileRead.nextLine();
                        }

                        //to set the block size according to n
                        blockSize = "";
                        while ((blockSize).length()<String.valueOf(n).length()){
                            blockSize = blockSize + (letterConv().indexOf('\n')+1);

                        }

                        //padding to the string tmp
                        while (tmp.length()%blockSize.length()!=0){
                            tmp= "0"+tmp.substring(0);
                        }

                        //dividing the string tmp into blocks and store it in arraylist
                        for (i = 0;i<tmp.length();i+=blockSize.length()){
                            DecBlocks.add(tmp.substring(i,i+blockSize.length()));
                        }

                        //decrypt the block stored in DecBlocks arraylist and make sure that each block = number of digits in n
                        for (i = 0 ; i < DecBlocks.size();i++){
                            if(String.valueOf(Decryption(Long.parseLong(DecBlocks.get(i)), d, n)).length()%blockSize.length()!=0){
                               for (int j = 0 ; j < blockSize.length()-(String.valueOf(Decryption(Long.parseLong(DecBlocks.get(i)), d, n)).length()%blockSize.length());j++ ) {
                                   DecTmp = DecTmp + "0";
                               }}
                           DecTmp = DecTmp + Decryption(Long.parseLong(DecBlocks.get(i)), d, n);

                        }

                        //dividing the string DecTmp to blocks of size 2 to convert it to letters
                        for (i = 0;i<DecTmp.length();i+=2){
                            writer.write(letterConv().get(Integer.parseInt(DecTmp.substring(i,i+2))));
                        }



                        System.out.println("d = "+d);
                        System.out.println("n = "+n);
                        System.out.println("Generating the file ...");
                        System.out.println((FilePath.substring(FilePath.lastIndexOf("/")+1,FilePath.indexOf("."))+".dec") + " file has been generated");

                        writer.close();
                        System.exit(0);


                        break;

                    case 3:
                        //exit the program
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

//modular exponentiation method
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

//method that store each letter in arraylist that can be used to convert letters to digits or digits to letters
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

// to display the menu
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
