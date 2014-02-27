import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.File;

/**
 * Created by linyu on 2/25/14.
 */
public class Hop {

    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private ArrayList<Integer> index = new ArrayList<Integer>();

    public Collection<Integer> jump(List<Integer> input) {
        int n = input.size();
        int[] step = new int[n];
        int range = 0;
        int last = n - 1;

        for (int i = 0; i <= range && i < n; i++) {
            if (input.get(i) + i > range) {
                range = i + input.get(i);
                for (int j = i; j <= range && j < n; j++) {
                    if (step[j] == 0 && j != 0) {      //step[0] = 0
                        step[j] = step[i] + 1;
                        map.put(j, i);
                    } else {
                        step[j] = Math.min(step[j], step[i] + 1);
                        if (step[i] + 1 < step[j]) {
                            map.put(j, i);
                        }
                    }
                }
            }
        }

        //use back trace map to get index
        if (map.containsKey(n - 1)) {
            while (last != 0) {
                index.add(last);
                last = map.get(last);
            }
            index.add(last);
        }

        Collections.reverse(index);
        return Collections.unmodifiableList(index);
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            File file = new File(args[0]);
            try {
                Scanner scanner = new Scanner(file);
                ArrayList<Integer> input = new ArrayList<Integer>();

                while (scanner.hasNext()) {
                    String s = scanner.nextLine();
                    input.add(Integer.parseInt(s));
                }
                //call jump function

                Collection<Integer> steps = new Hop().jump(input);
                if (steps.isEmpty()) {
                    System.out.println("failure");
                } else {
                    for (Integer step : steps) {
                        System.out.print(step + ", ");
                    }
                    System.out.println("out");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
