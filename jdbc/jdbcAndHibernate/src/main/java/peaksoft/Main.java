package peaksoft;

import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

public class Main {
    public static void main(String[] args) {

        // реализуйте алгоритм здесь
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Manas","Abdugani uulu",(byte)27);
        service.saveUser("Juma","Bolotov",(byte)21);
        service.saveUser("Jydyz","Tilekova",(byte)24);
        service.saveUser("Aizat","Duysheeva",(byte)20);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
         service.dropUsersTable();
//        service.removeUserById(1L);

    }
}
