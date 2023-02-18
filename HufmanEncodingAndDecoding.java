import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


// Class to compress binary data
class Compressor {
    public Vector<Byte> Compress(String str) {
        byte buffer = 0;
        // create a vector of bytes
        Vector<Byte> bytes = new Vector<>();

        int str_length = str.length();
        for (int i = 0; i < str_length; i++) {
            buffer = (byte) (buffer * 2);
            if (str.charAt(i) == '1') {
                buffer = (byte) (buffer + 1);
            }

            if (i % 8 == 7 || i == str_length - 1) {
                bytes.add(buffer);
                buffer = 0;
            }
        }

        return bytes;
    }
}
// Class for Huffman Tree Node

class Gere {

    Character chr;
    Integer freq;
    Gere left = null, right = null;

    Gere(Character ch, Integer freq) {
        this.chr = ch;
        this.freq = freq;
    }

    public Gere(Character ch, Integer freq, Gere left, Gere right) {
        this.chr = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

class Main {

    public static void WriteToFile(Vector<Byte> bytes, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            for (byte b : bytes) {
                fileOutputStream.write(b);
            }
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
       
        System.out.println("            | ENTER 1 TO RUN  |\n            | ENTER 0 TO EXIT |");
        System.out.println("            ___________________\n\n");

        Scanner swt = new Scanner(System.in);
        System.out.print("Enter Your Choice : ");
        switch (swt.nextInt()) {
            case 1:
                System.out.println("\n\n------------------------------------------");
                Scanner in = new Scanner(System.in);
                System.out.print("\n\nEnter Text: ");
                String text = in.nextLine();
                System.out.println("\n\n------------------------------------------");

                Huffman(text);
            case 0:
                break;
        }
    }

    // Peimayesh Derakht va Save`e Code dar Map
    public static void encode(Gere root, String str,
            Map < Character, String > hufmanCode) {
        if (root == null) {
            return;
        }

        // Gere`e bedoone Farzand
        if (YekGere(root)) {
            hufmanCode.put(root.chr, str.length() > 0 ? str : "1");
        }

        encode(root.left, str + '0', hufmanCode);
        encode(root.right, str + '1', hufmanCode);
    }

    // Peimayesh Derakht va decode`e string`e encode shode
    // Ba Estefadeh Az code haye encode shode va decode Tebghe Table.
    public static int decode(Gere root, int index, StringBuilder sb) {
        if (root == null) {
            return index;
        }

        // Gere`e bedoone Farzand
        if (YekGere(root)) {
            System.out.print(root.chr);
            return index;
        }

        index++;

        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decode(root, index, sb);
        return index;
    }

    // Tabe`e check ke aya Derakht faghat Yek Gere Darad ya Kheir
    public static boolean YekGere(Gere root) {
        return root.left == null && root.right == null;
    }

    // Sakht Derakht Hufman va decode`e Text.
    public static void Huffman(String text) {

        System.out.println("\n\nThe original string is: " + text);

        // Start Derakht
        if (text == null || text.length() == 0) {
            return;
        }

        // Shomaresh freq har charecter Va Zakhire dar Map
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Shakht Saf`e olaviat (PriorityQueue) ke balatarin olaviat ba charecter ba shomare kamtar.

        PriorityQueue<Gere> pq;
        pq = new PriorityQueue<>(new Comparator<Gere>() {
                public int compare(Gere o1, Gere o2) {
                    return o1.freq - o2.freq;
                }
            });


        // Sakht gere baraye har charecter va Ezafe be PriorityQueue.
        for (var entry : freq.entrySet()) {
            pq.add(new Gere(entry.getKey(), entry.getValue()));
        }

        // Anjam ta Zamani ke BISHTAR AZ YEK gere dar saf bashad.
        while (pq.size() != 1) {

            Gere left = pq.poll();
            Gere right = pq.poll();

            // gere left va right ba ham jam mishavand va yek gere adr sath balatar sakhte mishavad.
            // va loop
            int sum = left.freq + right.freq;
            pq.add(new Gere(null, sum, left, right));
        }

        // Sakht pointer baraye Rishe(root)
        Gere root = pq.peek();

        // Peimayesh Derakht va Save`e dar Map
        Map<Character, String> hufmanCode = new HashMap<>();
        encode(root, "", hufmanCode);

        // Print code`e Hufman
        System.out.println("\nHufman Codes are: " + hufmanCode);


        // Sakht va Print string`e encode shode
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(hufmanCode.get(c));
        }

        System.out.println("\nThe encoded string is: " + sb);
        // Compress and write sb to file
        try {
            Compressor compressor = new Compressor();
            Vector<Byte> sb_compressed = compressor.Compress(sb.toString());
            WriteToFile(sb_compressed, "src\\huffman.txt");
        }catch (Exception e){
            System.out.println("Error in writing to file");
        }

        System.out.print("\n The decoded string is: ");

        // Special case :

        if (YekGere(root)) {
            // Baraye voroodi Mesle : a, aa, aaa, ...
            while (root.freq-- > 0) {
                System.out.print(root.chr);
            }
        } else {
            // Peimayesh dobare derakht va decode`e string`e encode shode
            int index = -1;
            while (index < sb.length() - 1) {
                index = decode(root, index, sb);
            }
        }
        System.out.println();
    }

}