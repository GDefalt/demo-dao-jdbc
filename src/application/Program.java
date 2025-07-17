package application;

import entities.Department;
import entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Department obj = new Department(1, "Books");
        Seller seller = new Seller(21, 3000.0,  LocalDate.parse("01/06/2006", formatter), "bob@gmail.com", "Bob", new Department(1, "Books"));
        System.out.println(seller);
    }
}
