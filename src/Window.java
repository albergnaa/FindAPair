import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Window extends JFrame {

    public Window() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Choose difficulty level");
        setBackground(Color.lightGray);
        setLocation(500, 500);
        setSize(500, 100);
        setResizable(false);
        JPanel levelsPanel = new JPanel(new FlowLayout());
        JButton btnEasyLevel = new JButton("Easy level 4x4");
        btnEasyLevel.addActionListener(a -> {
            this.dispose();
             new Game(4);
        });
        JButton btnHardLevel = new JButton("Hard level 6x6");
        btnHardLevel.addActionListener(a -> {
            this.dispose();
            new Game(6);
        });
        btnEasyLevel.setPreferredSize(new Dimension(200,50));
        btnHardLevel.setPreferredSize(new Dimension(200,50));
        levelsPanel.add(btnEasyLevel);
        levelsPanel.add(btnHardLevel);
        add(levelsPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
