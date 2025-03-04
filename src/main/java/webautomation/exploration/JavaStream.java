package webautomation.exploration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaStream {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5, 5, 5, 55, 5, 5, 5, 5, 5, 6, 7, 8, 9, 10);
    List<Integer> result = new ArrayList<>();

    // scenario nya adalah kita mengambil semua yang ada angka 5, terus di add ke
    // result
    // Menggunakan tradisional looping
    for (int i = 0; i < numbers.size(); i++) {
      if (numbers.get(i) == 5) {
        result.add(numbers.get(i));
      }
    }

    for (Integer integer : numbers) {
      if (integer == 5) {
        result.add(integer);
      }
    }

    // System.out.println("ini adalah result" + result);

    // Menggunakan stream
    result = numbers.stream().filter(integer1 -> integer1.equals(5)).collect(Collectors.toList());

    System.out.println("ini adalah result" + result);

  }
}