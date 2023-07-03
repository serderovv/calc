import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final HashMap<Character, Integer> alphabet = new HashMap();
    static {
        alphabet.put('I', 1);
        alphabet.put('V', 5);
        alphabet.put('X', 10);
        alphabet.put('L', 50);
        alphabet.put('C', 100);
        alphabet.put('D', 500);
        alphabet.put('M', 1000);
    }

    private static final HashMap<Integer, Character> integers = new HashMap();
    static {
        integers.put(1,'I');
        integers.put(5,'V');
        integers.put(10,'X');
        integers.put(50,'L');
        integers.put(100,'C');
        integers.put(500, 'D');
        integers.put(1000,'M');
    }


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }
    public static String calc(String input) throws Exception {
        String[] all = input.split(" ");
        if (all.length==3){
            try {
                Integer i1 = Integer.parseInt(all[0]);
                Integer i2 = Integer.parseInt(all[2]);
                return String.valueOf((res(i1,i2,all[1])));
            } catch (NumberFormatException e){
                int i1 = romanToInt(all[0]);
                int i2 = romanToInt(all[2]);
                int result = res(i1,i2,all[1]);
                if (result<0){
                    throw new Exception();
                } else {
                    return intToRoman(result);
                }
            }
        } else {
            throw new Exception();
        }
    }
    private static int res(int i1, int i2, String c) throws Exception {
        switch (c){
            case "/":
                return i1/i2;
            case "*":
                return i1*i2;
            case "-":
                return i1-i2;
            case "+":
                return i1+i2;
            default:
                throw new Exception();
        }
    }

    private static int romanToInt(String in){

        String input = in;

        int result = 0;
        int after = 0;
        int now = 0;
        int size = input.length();
        for (int i = size - 1; i >= 0; i--) {
            now = alphabet.get(input.charAt(i));
            if (i != 0) {
                after = alphabet.get(input.charAt(i - 1));
                if (now > after) {
                    result += now - after;
                    i--;
                } else {
                    result += now;
                }
            } else {
                result += now;
            }
        }
        return (result);
    }

    private static String intToRoman(int num) {
        int index=6;
        int buf = 4;
        StringBuffer str = new StringBuffer("");
        int[] divider = new int[]{1,5,10,50,100,500,1000};
        while (num>0){
            if (num>=divider[index]){
                str.append(integers.get(divider[index]));
                num-=divider[index];
            } else if (num+divider[buf]>=divider[index]){
                str.append(integers.get(divider[buf]));
                str.append(integers.get(divider[index]));
                num=num+divider[buf]-divider[index];
                index--;
                buf -=2;
            } else{
                index--;
                buf = buf==index?buf-2:buf;
            }
        }
        return str.toString();
    }
}