import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class EmployeeServlet {
    private EmployeeDao employeeDao;

    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);
        Employee employee = employeeDao.getEmployee(id);
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/employee-detail.jsp").forward(request, response);
    }
}
