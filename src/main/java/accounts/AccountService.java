package accounts;

import java.util.ArrayList;
import java.util.List;


public class AccountService {

    private final List<String> passwordList;
    private final List<String> loginList;

    public AccountService() {
        passwordList = new ArrayList<>();
        loginList = new ArrayList<>();
    }

    public void addNewLogin(String login) {
        loginList.add(login);
    }

    public void addNewPassword(String pass) {
        passwordList.add(pass);
    }

    public List<String> getPasswordList() {
        return passwordList;
    }

    public List<String> getLoginList() {
        return loginList;
    }

    public boolean deleteUser(String login) {
        for (int i = 0; i < loginList.size(); i++) {
            if (login.equals(loginList.get(i))) {
                loginList.remove(i);
                passwordList.remove(i);
                return true;
            }
        }
        return false;
    }


}
