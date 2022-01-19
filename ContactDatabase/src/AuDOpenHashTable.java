import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class AuDOpenHashTable extends AuDHashTable{


    private LinkedList<Contact>[] table;

    public AuDOpenHashTable(int capacity) {
        super(capacity);
        table = new LinkedList[capacity];
    }

    @Override
    public void insert(Contact c) {
        int pos = hash(c.getEmail());

        if (table[pos] == null){
            table[pos] = new LinkedList<Contact>();
        }
        table[pos].addLast(c);
    }

    @Override
    public void remove(Contact c) {
        int i = hash(c.getEmail());

        if (table[i].contains(c)) {
            table[i].remove(c);
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public Contact getContact(String email) {

        int i = hash(email);
        if(table[i] != null) {

            Iterator<Contact> it = table[i].iterator();
            while (it.hasNext()) {
                Contact address = it.next();
                if (address.getEmail().equals(email)) {
                    return address;
                }
            }
        }

        throw new NoSuchElementException();
    }

}
