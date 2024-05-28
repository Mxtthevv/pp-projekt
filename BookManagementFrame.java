import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BookManagementFrame extends JFrame {
    private List<Book> books;
    private JTable bookTable;
    private BookTableModel bookTableModel;
    private JTextField searchField;
    private TableRowSorter<BookTableModel> sorter;

    public BookManagementFrame() {
        books = new ArrayList<>();
        books.add(new Book("Pan Tadeusz", "Adam Mickiewicz", 1834, "Epopeja narodowa"));
        books.add(new Book("Ferdydurke", "Witold Gombrowicz", 1937, "Powieść"));
        books.add(new Book("Quo Vadis", "Henryk Sienkiewicz", 1896, "Powieść historyczna"));
        books.add(new Book("Lalka", "Bolesław Prus", 1889, "Powieść społeczno-obyczajowa"));
        books.add(new Book("Nad Niemnem", "Eliza Orzeszkowa", 1888, "Powieść realistyczna"));
        books.add(new Book("Popiół i diament", "Jerzy Andrzejewski", 1948, "Powieść"));
        books.add(new Book("Chłopi", "Władysław Reymont", 1904, "Powieść realistyczna"));
        books.add(new Book("Balladyna", "Juliusz Słowacki", 1839, "Dramat"));
        books.add(new Book("Wesele", "Stanisław Wyspiański", 1901, "Dramat"));
        books.add(new Book("Pamiętnik z Powstania Warszawskiego", "Miron Białoszewski", 1950, "Dziennik"));
        books.add(new Book("Noc księżycowa", "Maria Kuncewiczowa", 1936, "Powieść"));
        books.add(new Book("Dziady", "Adam Mickiewicz", 1822, "Dramat"));
        books.add(new Book("Pożegnanie z Marią", "Tadeusz Borowski", 1947, "Opowiadania"));
        books.add(new Book("Ciemności kryją ziemię", "Jerzy Andrzejewski", 1957, "Powieść"));
        books.add(new Book("Inny świat", "Gustaw Herling-Grudziński", 1951, "Wspomnienia"));
        books.add(new Book("Sanatorium pod Klepsydrą", "Bruno Schulz", 1937, "Opowiadanie"));
        books.add(new Book("Sklepy cynamonowe", "Bruno Schulz", 1934, "Opowiadanie"));
        books.add(new Book("Matka Joanna od Aniołów", "Jarosław Iwaszkiewicz", 1946, "Opowiadanie"));
        books.add(new Book("Boso, ale w ostrogach", "Stanisław Grzesiuk", 1959, "Powieść autobiograficzna"));
        books.add(new Book("Przedwiośnie", "Stefan Żeromski", 1924, "Powieść"));
        books.add(new Book("Solaris", "Stanisław Lem", 1961, "Powieść science-fiction"));
        books.add(new Book("Pan Wołodyjowski", "Henryk Sienkiewicz", 1888, "Powieść historyczna"));
        books.add(new Book("Wyzwolenie", "Stanisław Wyspiański", 1903, "Dramat"));
        books.add(new Book("Dolina Issy", "Czesław Miłosz", 1955, "Powieść autobiograficzna"));
        books.add(new Book("Rodzina Połanieckich", "Henryk Sienkiewicz", 1894, "Powieść"));

        bookTableModel = new BookTableModel(books);
        bookTable = new JTable(bookTableModel);
        sorter = new TableRowSorter<>(bookTableModel);
        bookTable.setRowSorter(sorter);
        
        bookTable.setFillsViewportHeight(true);
        bookTable.setPreferredScrollableViewportSize(new Dimension(750, 300));
        bookTable.setRowHeight(25);

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Szukaj");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                bookTableModel.search(query);
            }
        });

        JButton addButton = new JButton("Dodaj książkę");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBookDialog dialog = new AddBookDialog(bookTableModel);
                dialog.setVisible(true);
            }
        });

        JButton removeButton = new JButton("Usuń książkę");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow != -1) {
                    int modelRow = bookTable.convertRowIndexToModel(selectedRow);
                    bookTableModel.removeBook(modelRow);
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.add(new JLabel("Wyszukaj książkę:"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(addButton);
        panel.add(removeButton);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setLayout(new BorderLayout(10, 10));
        add(new JScrollPane(bookTable), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    public BookTableModel getBookTableModel() {
        return bookTableModel;
    }
}
