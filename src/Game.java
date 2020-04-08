import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JFrame {
    private final int size = 150;
    private int cnt = 0;

    //устанавливаем рандомно индексы картинок ячейкам
    public int[][] assignImages(int len) {
        //количество различных картинок
        int n = len * len / 2;
        int[][] imgs = new int[len][len];
        Random rnd = new Random();
        int[] arr = new int[25];
        for (int i = 1; i <= 24; i++) {
            arr[i] = i;
        }
        int ind = 0;
        for (int i = 0; i < n; i++) {
            while (true) {
                arr[ind] = 0;
                ind = rnd.nextInt(arr.length - 1);
                if (arr[ind] != 0) break;
            }
            Pair pair1 = new Pair().getRandomPair(imgs);
            imgs[pair1.getX()][pair1.getY()] = ind;
            Pair pair2 = new Pair().getRandomPair(imgs);
            imgs[pair2.getX()][pair2.getY()] = ind;
        }
        return imgs;
    }

    public Game(int len) throws HeadlessException {
        ArrayList<ImageIcon> listOfImages = new ArrayList<ImageIcon>();
        for (int i = 0; i <= 24; i++) {
            listOfImages.add(new ImageIcon("res/" + i + ".jpg"));
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (len == 4) {
            setTitle("Easy level");
        } else if (len == 6) {
            setTitle("Hard level");
        }
        setLocation(0, 0);
        setSize(size * len + 100, size * len + 100);
        setResizable(false);
        int[][] imgs = assignImages(len);
        JPanel panel = new JPanel(new GridLayout(len, len));
        JButton[][] buttons = new JButton[len][len];
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setIcon(listOfImages.get(0));
                JButton btn = buttons[i][j];
                int finalI = i;
                int finalJ = j;
                Pair pair = new Pair();
                btn.addActionListener(a -> {
                    btn.setIcon(listOfImages.get(imgs[finalI][finalJ]));
                    pair.setX(finalI);
                    pair.setY(finalJ);
                    pairs.add(pair);
                    cnt++;
                    if ((pairs.size() == 2)&&(pairs.get(0).equals(pairs.get(1)))){
                        cnt --;
                        pairs.remove(1);
                    }
                    if (pairs.size() == 2) {
                        if ((imgs[pairs.get(0).getX()][pairs.get(0).getY()]) == (imgs[pairs.get(1).getX()][pairs.get(1).getY()])) {
                            buttons[pairs.get(0).getX()][pairs.get(0).getY()].setEnabled(false);
                            buttons[pairs.get(1).getX()][pairs.get(1).getY()].setEnabled(false);
                            pairs.remove(1);
                            pairs.remove(0);
                        } else {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            buttons[pairs.get(0).getX()][pairs.get(0).getY()].setIcon(listOfImages.get(0));
                            buttons[pairs.get(1).getX()][pairs.get(1).getY()].setIcon(listOfImages.get(0));
                            pairs.remove(1);
                            pairs.remove(0);
                            cnt -= 2;
                        }
                    }
                    if (cnt == (len * len)) {
                        JFrame alert = new JFrame("You win!");
                        alert.setLocation(500, 400);
                        alert.setSize(300, 100);
                        JPanel alertPanel = new JPanel(new FlowLayout());
                        JButton newGame = new JButton("Start new game");
                        newGame.addActionListener(actionEvent -> {
                            dispose();
                            new Window();
                            alert.dispose();
                        });
                        JButton close = new JButton("Exit");
                        close.addActionListener(actionEvent -> {
                            dispose();
                            alert.dispose();
                        });
                        alertPanel.add(newGame);
                        alertPanel.add(close);
                        alert.add(alertPanel);
                        alert.setResizable(false);
                        alert.setVisible(true);
                    }
                });
                panel.add(buttons[i][j]);
            }
        }


        add(panel);
        setVisible(true);
    }
}
