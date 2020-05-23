import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader
                (".\\src\\transactions.csv")));

        HashMap<String, Integer> hashMap = new HashMap<>();

        PrintWriter out = new PrintWriter(
                new FileWriter(".\\src\\out.xlsx"));

        while (sc.hasNext()) {

            String[] product = sc.nextLine().split(";");

            if (hashMap.containsKey(product[0])) {
                hashMap.put(product[0], hashMap.get(product[0]) + 1);
            } else {
                hashMap.put(product[0], 1);
            }
        }


        Map<String, Integer> map = hashMap;
        List list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });

        for (Object a : list) {
            String[] str = a.toString().split("=");
            out.println(str[0] + str[1]);

        }
        out.close();
    }
}
