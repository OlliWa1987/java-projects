public abstract class AuDHashTable {

    protected int capacity;

    public AuDHashTable(int capacity) {
        this.capacity = capacity;
    }

    abstract public void insert(Contact c);

    abstract public void remove(Contact c);

    abstract public Contact getContact(String email);

    // Diese Methode muss noch implementiert werden
    protected int hash(String s) {
        // Ersetzen Sie den Methodenkoerper durch die richtige Hashfunktion

        int result = 0;

        char[] charArray = new char[s.length()];
        int[] converted = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            charArray[i] = s.charAt(i);
        }

        for (int i = 0; i < converted.length; i++) {
            converted[i] = (int) charArray[i];
        }

        for (int i = 0; i < converted.length; i++) {
            result = result + converted[i];
        }

        return result % capacity;
    }
}
