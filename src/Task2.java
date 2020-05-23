import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader
                (".\\src\\transactions.csv")));

        HashMap<String, Integer> hashMap = new HashMap<>();

        PrintWriter out = new PrintWriter(
                new FileWriter(".\\src\\out.xlsx"));

        HashMap<String, String> hashMap1 = new HashMap<>();

        PrintWriter out1 = new PrintWriter(
                new FileWriter(".\\src\\allBasket.txt"));
        // считаем сколько раз купили продукт
        while (sc.hasNext()) {

            String[] product = sc.nextLine().split(";");

            if (hashMap.containsKey(product[0])) {
                hashMap.put(product[0], hashMap.get(product[0]) + 1);
            } else {
                hashMap.put(product[0], 1);
            }

            // какие продукты входят в корзину №3
            if (hashMap1.containsKey(product[1])) {
                hashMap1.put(product[1], hashMap1.get(product[1]) + " " + product[0]);
            } else {
                hashMap1.put(product[1], product[0]);
            }
        }

        // список сколько раз купили продукт (всего, среди всех покупок) в корзине  №3
        for (Map.Entry<String, String > a : hashMap1.entrySet()) {
            String[] str = a.getValue().split(" ");
            for (int i = 0; i < str.length; i++) {
                out1.print(hashMap.get(str[i]) + " ");
            }
            out1.print("\n");

        }

        Map<String, Integer> map = hashMap;
        List list = new ArrayList(map.entrySet());

        // сортировка для облегченного просмотра
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });

        // записываем в документ, потом составляем график из данных
        for (Object a : list) {
            String[] str = a.toString().split("=");
            out.println(str[0] + str[1]);

        }
        out.close();
        out1.flush();

    }
}
