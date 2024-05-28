import javax.swing.*;

public class BookManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new BookManagementFrame();
            frame.setTitle("System Zarządzania Książkami");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
