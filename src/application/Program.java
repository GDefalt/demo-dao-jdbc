package application;

import dao.DaoFactory;
import dao.SellerDao;
import dao.impl.SellerDaoJDBC;
import entities.Department;
import entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);


    }
}
