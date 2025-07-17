package application;

import dao.DaoFactory;
import dao.DepartmentDao;

import entities.Department;
;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: department findById ===");
        Department department = departmentDao.findById(1);
        System.out.println(department);


        System.out.println("=== TEST 3: Department findAll ===");
        List<Department> listAll = departmentDao.findAll();

        for(Department obj : listAll) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 4: Department insert ===");
        Department newDepartment = new Department(null, "Ti");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted Seller: " + newDepartment.getId());

        System.out.println("=== TEST 5: Department update ===");
        department = departmentDao.findById(1);
        department.setName("Sports");
        departmentDao.update(department);
        System.out.println("Update Seller: " + department.getId());


        System.out.println("=== TEST 6: Department delete ===");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Deleted Seller: " + department.getId());

        sc.close();

    }
}
