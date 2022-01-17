import java.util.NoSuchElementException;

public class SortedSet implements OrderedSet {

    ListItem head;
    ListItem tail;

    @Override
    public void clear() {

        head = tail = null;

    }

    @Override
    public int size() {
        if (isEmpty()){
            return 0;
        }
        else {
            ListItem helper = head;
            int size = 0;
            while (helper != null){
                size++;
                helper = helper.getNext();
            }
            return size;
        }
    }

    @Override
    public boolean contains(int value) {
        if (isEmpty()){
            return false;
        } else {
            ListItem helper = head;
            while (helper != null){
                if (helper.value == value){
                    return true;
                }
                helper = helper.getNext();
            }
        }
        return false;
    }


    @Override
    public int[] toArray() {

        int length = size();
        int j = 0;
        int[] arrayList = new int[length];

        for (ListItem i = head; i != null; i = i.next) {
            arrayList[j] = i.getValue();
            j++;
        }

        return arrayList;
    }

    @Override
    public int[] toReversedArray() {
        int size = size();
        int[] input = toArray();

        int[] result = new int[size];
        int a = size;
        for (int i = 0; i < size; i++) {
            result[a - 1] = input[i];
            a = a - 1;
        }
        //System.out.println(Arrays.toString(result));
        return result;
    }

    private boolean isEmpty() {

        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void add(int value) {
        //Wenn leer______________
        if (isEmpty()){
            head = new ListItem(value);
            tail = head;
            return;
            //System.out.println("yuhuuu");

            //Wenn Head______________
        } else if (value < head.value){
            ListItem item = new ListItem(value);

            if (size() == 1){
                tail = head;
                head.setPrevious(item);
                item.setNext(head);
                head = item;
                return;
            } else {
                head.setPrevious(item);
                item.setNext(head);
                head = item;
                return;
            }

            //Wenn Tail_____________
        } else if (value > tail.value) {
            ListItem item = new ListItem(value);

            tail.setNext(item);
            item.setPrevious(tail);
            tail = item;
            return;
        }

        //Wenn Mitte____________
        else {
            ListItem helper = head;
            ListItem item = new ListItem(value);

            while (helper != null) {

                if (value < helper.value) {
                    helper.previous.setNext(item);
                    item.setPrevious(helper.previous);
                    helper.setPrevious(item);
                    item.setNext(helper);
                    return;

                }

                helper = helper.getNext();
            }
        }
    }


    @Override
    public void add(int[] values) {
        for (int i = 0; i < values.length; i++) {
            add(values[i]);
        }

    }

    @Override
    public void remove(int value) throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException();

        } else if (value == head.value){
            head = head.next;

        } else if (value == tail.value) {

            tail.previous.next = null;
            tail = tail.getPrevious();

        } else {
            ListItem helper = head;

            while (helper != null){
                if (value == helper.value){
                    helper.previous.next = helper.next;
                    helper.next.previous = helper.previous;
                }
                helper = helper.getNext();
            }
        }
    }

    @Override
    public OrderedSet clone() {
        SortedSet clone = new SortedSet();
        ListItem helper = head;

        while (helper != null) {
            clone.add(helper.value);
            helper = helper.getNext();
        }

        return clone;
    }

    @Override
    public OrderedSet getSetInBetween(int from, int to) {

        SortedSet between = new SortedSet();
        ListItem helper = head;

        while (helper != null) {

            if ((helper.getValue() >= from) && (helper.getValue() <= to)) {
                between.add(helper.value);
                helper = helper.getNext();
            } else {
                helper = helper.getNext();
            }

        }

        return between;
    }

    @Override
    public OrderedSet intersect(OrderedSet set) {

        SortedSet intersect = new SortedSet();
        ListItem helper = head;

        while (helper != null) {

            if (set.contains(helper.getValue())) {
                intersect.add(helper.value);
                helper = helper.getNext();
            } else {
                helper = helper.getNext();
            }

        }

        return intersect;
    }

    @Override
    public OrderedSet unite(OrderedSet set) {

        SortedSet union = new SortedSet();
        int[] setArray = set.toArray();
        union.add(setArray);
        ListItem helper = head;

        while (helper != null) {

            if (!union.contains(helper.getValue())) {
                union.add(helper.value);
                helper = helper.getNext();
            } else {
                helper = helper.getNext();
            }

        }

        return union;
    }

    @Override
    public OrderedSet subtract(OrderedSet set) {

        SortedSet substract = new SortedSet();
        ListItem helper = head;

        while (helper != null) {

            if (!set.contains(helper.getValue())) {
                substract.add(helper.value);
                helper = helper.getNext();
            } else {
                helper = helper.getNext();
            }

        }

        return substract;
    }

    @Override
    public String toString() {
        ListItem helper = head;
        String result = "";

        while (helper != null) {
            result = result + helper.toString();
            helper = helper.getNext();
        }
        return result;
    }


    public class ListItem {

        int value;
        ListItem previous;
        ListItem next;

        public ListItem(int item) {
            this.value = item;
        }

        public String toString() {
            if ((next == null)){
                return ("[" + value + "]");
            }else {
                return ("[" + value + "] --> ");
            }
        }

        @Override
        public boolean equals(Object obj) {

            for (ListItem i = head; i != null; i = i.next) {
                if (value == (int) obj) {
                    return true;
                }
            }
            return false;
        }

        public ListItem getPrevious(){
            return previous;
        }
        public ListItem getNext(){
            return next;
        }
        public void setPrevious(ListItem i){
            this.previous = i;
        }
        public void setNext(ListItem n){
            this.next = n;
        }

        public int getValue(){
            return value;
        }
        public void setValue(int v){
            this.value = v;
        }

    }

    public static void main(String[] args) {

        OrderedSet list1 = new SortedSet();
        OrderedSet list2 = new SortedSet();

        System.out.println(list1.toString());
        System.out.println(list2.toString());

        list1.add(1);
        list1.add(6);
        list1.add(4);
        list1.add(2);
        list1.add(7);
        list1.add(5);
        list1.add(12);

        int[] list2array = new int[]{5, 23, 22, 7, 9};

        list2.add(list2array);

        System.out.println(list1.toString());
        System.out.println(list2.toString());

        list2.remove(22);

        try{
            list2.remove(77);
        } catch (ElementExistsException I){
            System.err.println("Can't remove a non existent element!");
        }

        OrderedSet interval = list1.getSetInBetween(2, 6);
        System.out.println(interval.toString());

        try{
            OrderedSet intersect = list1.intersect(list2);
            System.out.println(intersect.toString());

            OrderedSet substract = list1.intersect(list2);
            System.out.println(substract.toString());

            OrderedSet unite = list1.unite(list2);
            System.out.println(unite.toString());

        } catch (ElementExistsException el){
            System.out.println("Double element");
        } catch (NoSuchElementException nl){
            System.out.println("No such Element");
        }

        SortedSet list3 = new SortedSet();
        list3.add(18);
        list3.add(19);
        list3.add(20);

        list3.unite(list1);
    }
}
