package application;

import dao.DaoFactory;
import dao.SellerDao;

import entities.Department;
import entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Program {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("=== TEST 2: Seller findByDepartment ===");
        List<Seller> list = sellerDao.findByDepartment(new Department(2, "Eletronics"));

        for(Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 3: Seller findAll ===");
        List<Seller> listAll = sellerDao.findAll();

        for(Seller obj : listAll) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 4: Seller insert ===");
        Seller newSeller = new Seller(null, 4000.0, LocalDate.parse("01/01/2002", formatter), "greg.gmail.com", "Greg", new Department(2, "Eletronics"));
        sellerDao.insert(newSeller);
        System.out.println("Inserted Seller: " + newSeller.getId());

    }
}
