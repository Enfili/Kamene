package times;

import java.io.*;
import java.util.ArrayList;

public class TestClass implements Serializable {
    ArrayList<PlayerTime> pt = new ArrayList<>();

    public TestClass() {
        pt.add(new PlayerTime("a", 2));
        pt.add(new PlayerTime("s", 3));
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File("test.txt")));
            oos.writeObject(pt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void readTest() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("test.txt"));
            ArrayList<PlayerTime> pp = (ArrayList<PlayerTime>) ois.readObject();
            for (PlayerTime p: pp)
                System.out.println(p.name + " " + p.time);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private class PlayerTime implements Comparable<PlayerTime>, Serializable {
        private final String name;
        private final int time;

        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public int compareTo(PlayerTime o) {
            if (o == null)
                return -1;
            return time - o.time;
        }
    }
}
