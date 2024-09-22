package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    private EmployeeDao employeeDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDao();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("id");
        response.setContentType("application/json");
        if (path == null) {
            try {
                List<Employee> employees = employeeDao.getAllEmployee();
                for (Employee employee : employees) {
                    response.getWriter().write(employee.toString() + "\n");
                }
            } catch (Exception e) {
                response.getWriter().write("No Employee found");
            }
        } else {
            String idParam = request.getParameter("id");
            int id = Integer.parseInt(idParam);
            try {
                Employee employee = employeeDao.getEmployee(id);
                response.getWriter().write(employee.toString());
            } catch (Exception e) {
                response.getWriter().write("No employee found with id: " + id);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("Entered Post");
        String requestBody = reqBodyToString(request);
        Employee employee = new Employee();
        try {
            employee = objectMapper.readValue(requestBody, Employee.class);
            employeeDao.addEmployee(employee.getId(), employee.getName(), employee.getAge(), employee.getPosition());
            response.getWriter().write("Successfully Added");
        } catch (Exception e) {
            response.getWriter().write("Cant add employee");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");

        String idParam = request.getParameter("id");
        int idToChange = Integer.parseInt(idParam);

        String reqBody = reqBodyToString(request);

        Employee employee = objectMapper.readValue(reqBody, Employee.class);

        try {
            employeeDao.updateEmployee(idToChange, employee.getId(), employee.getName(), employee.getAge(), employee.getPosition());
            response.getWriter().write("Successfully Edited");
        } catch (Exception e) {
            response.getWriter().write("No employee found at id: " + idToChange);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int deletedRows = 0, id = Integer.parseInt(idParam);
        try {
            deletedRows = employeeDao.deleteEmployee(id);
        } catch (Exception e) {
            System.out.println("error");
        }
        if (deletedRows > 1) {
            response.getWriter().write("Deleted " + deletedRows + " records");
        } else {
            response.getWriter().write("ID not found");
        }
    }


    private static String reqBodyToString(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return requestBody.toString();
    }
}