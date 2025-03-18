package Service;

import DAO.AccountDAO;
import Model.Account;

import java.util.List;

public class AccountService {
    public AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account createAccount(Account account) {
        if(checkExists(account.getAccount_id()) || account.username.isBlank() || account.password.length() < 4) {
            return null;
        }

        return accountDAO.createAccount(account);
    }

    public boolean checkExists(int id) {
        if(accountDAO.getAccountBy_id(id) != null) {
            return true;
        }
        return false;
    }

    public Account login(Account account) {
        if(accountDAO.login(account) == null) {
            return null;
        }

        else if (accountDAO.login(account).username.equals(account.username) && accountDAO.login(account).password.equals(account.password)) {
            return accountDAO.login(account);
        }
        return null;
    }
}