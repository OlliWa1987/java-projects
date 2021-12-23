public class CaesarChiffre {

    public static final char SEPARATOR = (char) 32;

    public static int getIndexOfMaximumEntry(int[] array) {
        int max = array[0];
        int indMax = 0;
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
                indMax = i;
            }
        }

        return indMax;
    }

    public static int[] getHistogram(String array) {
        int[] histogram = new int[128];
        String str = array.toLowerCase();
        for (int i = 1; i < str.length(); i++) {
            char character = str.charAt(i);
            int ascii = character;
            histogram[ascii] += 1;
        }

        return histogram;
    }

    public static char getSignificantLetter(String array) {
        int[] histogram = getHistogram(array);
        histogram[SEPARATOR] = 0;
        char significantLetter = (char) getIndexOfMaximumEntry(histogram);
        int quantity = histogram[significantLetter];
        double sum = 0;
        for (int i = 0; i < histogram.length; i++) {
            sum += histogram[i];
        }

        double quota = (double) quantity / sum * 100;
        System.out.println("Most significant letter: " + significantLetter);
        System.out.println("Quantity: " + quantity + " times (" + quota + " % of whole text).");            //(String.format("%.2f", quota) + ...)
        System.out.println();

        return significantLetter;
    }

    public static int getShift(String encryptedText, String languagePattern) {
        char sigOfPattern = getSignificantLetter(languagePattern);
        char sigOfChiffre = getSignificantLetter(encryptedText);
        int shift = (int) sigOfChiffre - (int) sigOfPattern;
        if (shift < 0) {
            shift = shift * (-1);
        }

        System.out.println("Most significant letter in the pattern text: " + sigOfPattern);
        System.out.println("Most significant letter in the encryptedText: " + sigOfChiffre);
        System.out.println("Resulting shift: " + shift);
        System.out.println();

        return shift;
    }

    public static String decode(String encryptedText, String languagePattern) {
        int shift = getShift(encryptedText, languagePattern);
        char[] lettersEncryptedText = encryptedText.toCharArray();
        for (int i = 0; i < lettersEncryptedText.length; i++) {
            if ((int) lettersEncryptedText[i] == (int) SEPARATOR) {
                lettersEncryptedText[i] = SEPARATOR;
            } else if ((int) lettersEncryptedText[i] == 46) {
                lettersEncryptedText[i] = (char) 46;
            } else if ((int) lettersEncryptedText[i] == 33) {
                lettersEncryptedText[i] = (char) 33;
            } else if ((int) lettersEncryptedText[i] + shift < 0) {
                lettersEncryptedText[i] = (char) ((int) lettersEncryptedText[i] - shift + 128);
            } else {
                lettersEncryptedText[i] = (char) ((int) lettersEncryptedText[i] - shift);
            }
        }

        String decoded = String.valueOf(lettersEncryptedText);
        return decoded;
    }

    public static void main(String[] args) {

        final String GERMAN_LANGUAGE_PATTERN = "Werden zwei Glasstaebe mit einem Wolltuch gerieben, dann kann man feststellen, dass sich die beiden Staebe gegenseitig abstossen. Wird das gleiche Experiment mit zwei Kunststoffstaeben wiederholt, dann bleibt das Ergebnis gleich, auch diese beiden Staebe stossen sich gegenseitig ab. Im Gegensatz dazu ziehen sich ein Glas und ein Kunststoffstab gegenseitig an. Diese mit den Gesetzen der Mechanik nicht zu erklaerende Erscheinung fuehrt man auf Ladungen zurueck. Da sowohl Anziehung als auch Abstossung auftritt, muessen zwei verschiedene Arten von Ladungen existieren. Man unterscheidet daher positive und negative Ladungen.";
        final String ENCRYPTED_MESSAGE = "xjmw lzy! iz mfxy ijs htij ljpsfhpy zsi inw xt wzmr zsi jmwj jw|twgjs. nhm rzxx rnw mnjw ojijx xjrjxyjw jnsjs sjzjs yj}y fzxijspjs zsi qfslxfr |jwijs inj nijjs psfuu.";

        String decodedText = decode(ENCRYPTED_MESSAGE, GERMAN_LANGUAGE_PATTERN);
        System.out.println("Unreadable, encrypted input text:\n" + ENCRYPTED_MESSAGE);
        System.out.println();
        System.out.println("Readable, decoded input text:\n" + decodedText);

        /* sehr gut! du hast den code geknackt und dir ruhm und ehre erworben. ich muss mir hier jedes semester einen neuen text ausdenken und langsam werden die ideen knapp.
        key = shift = 5
         */

    }
}
