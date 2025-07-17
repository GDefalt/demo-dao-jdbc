package application;

import dao.DaoFactory;
import dao.SellerDao;

import entities.Department;
import entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(1);
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
        Seller newSeller = new Seller(null, 4000.0, LocalDate.parse("01/06/2006", formatter), "gabriel.gmail.com", "Gabriel", new Department(2, "Eletronics"));
        sellerDao.insert(newSeller);System.out.println("Inserted Seller: " + newSeller.getId());

        System.out.println("=== TEST 5: Seller update ===");
        seller = sellerDao.findById(1);
        seller.setName("Carlos");
        sellerDao.update(seller);
        System.out.println("Update Seller: " + seller.getId());


        System.out.println("=== TEST 6: Seller delete ===");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Deleted Seller: " + seller.getId());

        sc.close();

    }
}
