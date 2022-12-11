package personal;

import personal.controllers.UserController;
import personal.model.FileOperation;
import personal.model.FileOperationImpl;
import personal.model.NewRepositoryFile;
import personal.model.Repository;
import personal.model.RepositoryFile;
import personal.views.ViewUser;



public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("users.txt");
        FileOperation fileOperation2  = new FileOperationImpl("users1.txt");
        Repository repository = new RepositoryFile(fileOperation);
        Repository repository2 = new NewRepositoryFile(fileOperation2);
        UserController controller = new UserController(repository);
        UserController controller2 = new UserController(repository2);
        ViewUser view = new ViewUser(controller);
        ViewUser view2 = new ViewUser(controller2);
        view.run();
        view2.run();
    }
}
