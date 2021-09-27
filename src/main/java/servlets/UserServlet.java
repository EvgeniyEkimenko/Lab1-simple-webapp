package servlets;

import accounts.AccountService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private final AccountService accountService;
    private Map<String, Object> pageVariables = new HashMap<>();

    public UserServlet(AccountService accountService) {
        this.accountService = accountService;
    }

         public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8"); //application/json ???
            response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String loginForDelete = request.getParameter("loginForDelete");

        if (loginForDelete!=null) {
            if (accountService.getLoginList().size()!=0) {
                accountService.deleteUser(loginForDelete);
                pageVariables.put("login", getStringFromArray(accountService.getLoginList()));
                pageVariables.put("password", getStringFromArray(accountService.getPasswordList()));
                response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            else {
                response.setContentType("text/html;charset=utf-8");
                System.out.println("Не найдено!");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }

        accountService.addNewLogin(login);
        accountService.addNewPassword(pass);

        pageVariables.put("login", getStringFromArray(accountService.getLoginList()));
        pageVariables.put("password", getStringFromArray(accountService.getPasswordList()));

        response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


    public String getStringFromArray(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(" <br> ");
            sb.append(list.get(i));

        }
        return sb.toString();
    }
}
