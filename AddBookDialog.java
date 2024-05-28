import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookDialog extends JDialog {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;
    private JTextField genreField;
    private BookTableModel bookTableModel;

    public AddBookDialog(BookTableModel bookTableModel) {
        this.bookTableModel = bookTableModel;
        setTitle("Dodaj książkę");
        setSize(300, 200);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);

        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();
        genreField = new JTextField();

        add(new JLabel("Tytuł:"));
        add(titleField);

        add(new JLabel("Autor:"));
        add(authorField);

        add(new JLabel("Rok:"));
        add(yearField);

        add(new JLabel("Gatunek:"));
        add(genreField);

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String yearText = yearField.getText();
                String genre = genreField.getText();

                if (!title.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(AddBookDialog.this, "Tytuł może zawierać tylko litery.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!author.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(AddBookDialog.this, "Autor może zawierać tylko litery.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!yearText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(AddBookDialog.this, "Rok może zawierać tylko cyfry.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!genre.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(AddBookDialog.this, "Gatunek może zawierać tylko litery.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int year = Integer.parseInt(yearText);

                bookTableModel.addBook(new Book(title, author, year, genre));
                dispose();
            }
        });

        add(addButton);
    }
}
