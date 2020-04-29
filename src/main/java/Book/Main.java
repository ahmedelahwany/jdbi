package Book;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:TestBook");
        jdbi.installPlugin(new SqlObjectPlugin());

        try(Handle handle = jdbi.open())
        {
            BookDao bookDao = handle.attach((BookDao.class));
            bookDao.createTable();
            bookDao.insert(new Book("9780785834588", "Dante Aligieri","The Divine Comedy", Book.Format.Hardback, "Book Sales Inc",LocalDate.parse("2016-12-22"), 736, true));
            bookDao.insert(new Book("9781409181637", "Alex Michaelides","The Silent Patient : The Richard and Judy bookclub pick and Sunday Times Bestseller", Book.Format.Paperback, "Orion Publishing Co",LocalDate.parse("2020-1-15"), 352, true));



            bookDao.findAll().stream().forEach(System.out::println);
            System.out.println((bookDao.find("9780785834588").get()));
            bookDao.delete(bookDao.find("9780785834588").get());
            //checking
            bookDao.findAll().stream().forEach(System.out::println);



        }
    }
}