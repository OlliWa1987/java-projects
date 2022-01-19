import java.util.NoSuchElementException;

public class AuDClosedHashTable extends AuDHashTable {

    private Contact[] table;
    private boolean[] deleted;
    private int counter;

    public AuDClosedHashTable(int capacity) {
        super(capacity);
        this.counter = 0;
        this.table = new Contact[capacity];
        this.deleted = new boolean[capacity];

        /*for (int i = 0; i <= capacity - 1; i++) {
            deleted[i] = false;
        }*/

    }

    @Override
    public void insert(Contact c) {

        if (!isFull()) {

            int i = 0;
            int idx = hash(c.getEmail(), i);

            while (table[idx] != null) {
                i++;
                idx = hash(c.getEmail(), i);
            }

            this.table[idx] = c;
            this.deleted[idx] = false;
            this.counter = counter + 1;

        } else {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public void remove(Contact c) {

        int i = getIndexOf(c.getEmail());
        table[i] = null;
        this.deleted[i] = !this.deleted[i];
        this.counter = counter - 1;

    }

    @Override
    public Contact getContact(String email) {

        int pos = getIndexOf(email);

        return table[pos];
    }

    public boolean isFull(){

        if (counter == capacity) {
            return true;
        } else {
            return false;
        }
    }

    protected int hash(String s, int i) {

        int result;
        int k = capacity;

        if (i % 2 == 0){
            result = Math.floorMod((hash(s) - (i/2) - 1), k);
        } else {
            result = Math.floorMod((hash(s) + (i/2)), k);
        }

        return result;
    }


    private int getIndexOf(String email) throws NoSuchElementException {

        int z = 0;
        int i = hash(email, z);

        while (table[i] == null || (!email.equals(table[i].getEmail()))){
            i = hash(email, z++);

            if (z == capacity + 1){
                throw new NoSuchElementException();
            }
        }

        return i;
    }
}
