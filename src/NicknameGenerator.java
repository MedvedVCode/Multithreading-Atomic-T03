import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NicknameGenerator {
    public static void main(String[] args) {
        AtomicInteger countWordsLengthOf3 = new AtomicInteger(0);
        AtomicInteger countWordsLengthOf4 = new AtomicInteger(0);
        AtomicInteger countWordsLengthOf5 = new AtomicInteger(0);

        Random random = new Random();
        int textArrayAmount = 100_000;
        int initialWordLength = 3;
        String[] text = new String[textArrayAmount];

        for (int i = 0; i < text.length; i++) {
            text[i] = generateText("abc", initialWordLength + random.nextInt(initialWordLength));
            char[] charFromTextI = text[i].toCharArray();

            new Thread(() -> {
                int pos = 0;
                boolean isPalindrome = true;
                while (pos < charFromTextI.length / 2 && isPalindrome) {
                    if (charFromTextI[pos] != charFromTextI[charFromTextI.length - pos - 1]) {
                        isPalindrome = false;
                    }
                    pos++;
                }
                if (isPalindrome) {
                    //System.out.println(Arrays.toString(charFromTextI));
                    switch (charFromTextI.length) {
                        case (3):
                            countWordsLengthOf3.incrementAndGet();
                            break;
                        case (4):
                            countWordsLengthOf4.incrementAndGet();
                            break;
                        case (5):
                            countWordsLengthOf5.incrementAndGet();
                            break;
                    }
                }
            }).start();

            new Thread(() -> {
                int pos = 1;
                boolean isSingle = true;
                while (pos < charFromTextI.length && isSingle) {
                    if (charFromTextI[pos - 1] != charFromTextI[pos]) {
                        isSingle = false;
                    }
                    pos++;
                }
                if (isSingle) {
                    //System.out.println(Arrays.toString(charFromTextI));
                    switch (charFromTextI.length) {
                        case (3):
                            countWordsLengthOf3.incrementAndGet();
                            break;
                        case (4):
                            countWordsLengthOf4.incrementAndGet();
                            break;
                        case (5):
                            countWordsLengthOf5.incrementAndGet();
                            break;
                    }
                }
            }).start();

            new Thread(() -> {
                int pos = 1;
                boolean isIncremments = true;
                while (pos < charFromTextI.length && isIncremments) {
                    if (charFromTextI[pos - 1] > charFromTextI[pos]) {
                        isIncremments = false;
                    }
                    pos++;
                }
                if (isIncremments) {
                    //System.out.println(Arrays.toString(charFromTextI));
                    switch (charFromTextI.length) {
                        case (3):
                            countWordsLengthOf3.incrementAndGet();
                            break;
                        case (4):
                            countWordsLengthOf4.incrementAndGet();
                            break;
                        case (5):
                            countWordsLengthOf5.incrementAndGet();
                            break;
                    }
                }
            }).start();
        }
        System.out.printf(" Красивых слов с длиной 3: %d шт \n", countWordsLengthOf3.get());
        System.out.printf(" Красивых слов с длиной 4: %d шт \n", countWordsLengthOf4.get());
        System.out.printf(" Красивых слов с длиной 5: %d шт \n", countWordsLengthOf5.get());
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(letters.charAt(random.nextInt(letters.length())));
        }
        return str.toString();
    }
}
