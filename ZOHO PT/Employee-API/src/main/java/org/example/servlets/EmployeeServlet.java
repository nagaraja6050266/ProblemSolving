package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.DaoDistributor;
import org.example.dao.EmployeeDao;
import org.example.models.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    private EmployeeDao employeeDao = DaoDistributor.getEmployeeDao();
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = getIdFromReq(request.getRequestURI(), 2);
            if (id == -1) {

                List<Employee> employees = employeeDao.getAllEmployee();
                List<String> responseEmployees = new ArrayList<>();
                for (Employee employee : employees) {
                    responseEmployees.add(employee.toJson());
                }

                if (responseEmployees.isEmpty()) {
                    response.getWriter().write("No Employees found");
                } else {
                    response.getWriter().write(responseEmployees.toString());
                }

            } else {

                Employee employee = employeeDao.getEmployee(id);

                if (employee == null) {
                    response.getWriter().write("Employee id not found");
                } else {
                    response.getWriter().write(employee.toJson());
                }

            }
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Employee employee = reqBodyToEmployeeObj(request);
            response.getWriter().write(employeeDao.addEmployee(employee).toJson());
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = getIdFromReq(request.getRequestURI(), 2);
            Employee employee = reqBodyToEmployeeObj(request);
            employeeDao.updateEmployee(id, employee);
            response.getWriter().write(employee.toJson());
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int deletedRows = employeeDao.deleteEmployee(getIdFromReq(request.getRequestURI(), 2));
            response.getWriter().write("Deleted " + deletedRows + " Employees");
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    private static Employee reqBodyToEmployeeObj(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return objectMapper.readValue(requestBody.toString(), Employee.class);
    }

    private static int getIdFromReq(String reqUri, int index) {
        index++;
        String[] pathArray = reqUri.split("/");
        if (pathArray.length > index && StringUtils.isStrictlyNumeric(pathArray[index])) {
            return Integer.parseInt(pathArray[index]);
        }
        return -1;
    }
}