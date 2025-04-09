package microservices.task;

import microservices.task.model.User;
import microservices.task.service.UsersApiService;
import org.springframework.http.*;

public class App {
    public static void main( String[] args ) {

        User tester = new User(3L,"James","Brown", (byte) 33);

        UsersApiService usersApiService = new UsersApiService();
        HttpHeaders curSessionHeaders = usersApiService.getCurSessionHeaders();
        String addUserCode = usersApiService.addUser(tester, curSessionHeaders).getBody();
        tester.setName("Thomas");
        tester.setLastName("Shelby");
        String editUserCode = usersApiService.updateUser(tester, curSessionHeaders).getBody();
        String deleteUserCode = usersApiService.deleteUser(tester, curSessionHeaders).getBody();

        System.out.println("Final code: "+addUserCode+editUserCode+deleteUserCode);

    }
}
