public class ContactDatabase {

    public static void main(String[] args) {

        Contact c1 = new Contact("tom@mail.com");
        c1.setName("Thomas DeLonge");
        c1.setNickname("Tom");
        c1.setTelephone("012345");

        Contact c2 = new Contact("mark@mail.com");
        c2.setName("Mark Hoppus");
        c2.setNickname("Mark");
        c2.setTelephone("543210");


        Contact c3 = new Contact("travis@mail.com");
        c3.setName("Travis Barker");
        c3.setNickname("Drum Hero");
        c3.setTelephone("101010");

        Contact c4 = new Contact("blink@mail.com");
        c4.setName("Blink 182");
        c4.setNickname("Blink");
        c4.setTelephone("182");

        Contact replacement = new Contact("matt@mail.com");
        replacement.setName("Matt Skibba");
        replacement.setNickname("Skib");
        replacement.setTelephone("000");

        AuDClosedHashTable testClosed = new AuDClosedHashTable(4);

        testClosed.insert(c1);
        System.out.println(testClosed.getContact("tom@mail.com"));
        testClosed.insert(c2);
        System.out.println(testClosed.getContact("mark@mail.com"));
        testClosed.insert(c3);
        System.out.println(testClosed.getContact("travis@mail.com"));
        testClosed.insert(c4);
        System.out.println(testClosed.getContact("blink@mail.com"));
        testClosed.remove(c1);
        testClosed.insert(replacement);
        System.out.println(testClosed.getContact("matt@mail.com"));

        AuDOpenHashTable testOpen = new AuDOpenHashTable(15);
        testOpen.insert(c1);
        testOpen.insert(c2);
        testOpen.insert(c3);
        testOpen.insert(c4);
        testOpen.insert(replacement);
        System.out.println(testOpen.getContact("tom@mail.com"));
        System.out.println(testOpen.getContact("mark@mail.com"));
        System.out.println(testOpen.getContact("travis@mail.com"));
        System.out.println(testOpen.getContact("matt@mail.com"));
        testOpen.remove(replacement);
        //System.out.println(testOpen.getContact("matt@mail.com"));
    }

}
