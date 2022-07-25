package times;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;

public class PlayerTimes implements Serializable {
    private static final long serialVersionUID = 1L;
    List<Player> playerTimes = new ArrayList<>();

    public PlayerTimes() {
        playerTimes = loadPlayingTimes();
    }

    public void addPlayer(String name, int time) {
        playerTimes.add(new Player(name, time));
        Collections.sort(playerTimes);
    }

    public void savePlayingTimes() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File("times.txt")));
            oos.writeObject(playerTimes);
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

    public List<Player> loadPlayingTimes() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("times.txt"));
            playerTimes = (List<Player>) ois.readObject();
            return playerTimes;
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

    @Override
    public String toString() {
        Formatter f = new Formatter();
        for (Player p: playerTimes)
            f.format("%s, playing time: %d%n", p.name, p.time);
        return f.toString();
    }

    private class Player implements Comparable<Player>, Serializable {
        private final String name;
        private final int time;

        public Player(String name, int time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public int compareTo(Player o) {
            if (o == null)
                return -1;
            return time - o.time;
        }
    }
}

