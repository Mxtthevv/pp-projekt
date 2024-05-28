import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BookTableModel extends AbstractTableModel {
    private List<Book> books;
    private List<Book> filteredBooks;
    private String[] columnNames = {"Tytuł", "Autor", "Rok", "Gatunek"};

    public BookTableModel(List<Book> books) {
        this.books = books;
        this.filteredBooks = new ArrayList<>(books);
    }

    @Override
    public int getRowCount() {
        return filteredBooks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = filteredBooks.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getAuthor();
            case 2:
                return book.getYear();
            case 3:
                return book.getGenre();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addBook(Book book) {
        books.add(book);
        filteredBooks.add(book);
        fireTableRowsInserted(filteredBooks.size() - 1, filteredBooks.size() - 1);
    }

    public void removeBook(int rowIndex) {
        Book book = filteredBooks.get(rowIndex);
        books.remove(book);
        filteredBooks.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void search(String query) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        filteredBooks.clear();
        for (Book book : books) {
            if (pattern.matcher(book.getTitle()).find() ||
                pattern.matcher(book.getAuthor()).find() ||
                pattern.matcher(String.valueOf(book.getYear())).find() ||
                pattern.matcher(book.getGenre()).find()) {
                filteredBooks.add(book);
            }
        }
        fireTableDataChanged();
    }
}
