package org.example.dao;


public class DaoDistributor {

    private static EmployeeDao employeeDao;


    public static EmployeeDao getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDao();
        }
        return employeeDao;
    }
}