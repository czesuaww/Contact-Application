package code;

public class CreateWindowFactory {

    public ICreateWindowFactory chooseForm(String formName) {

        ICreateWindowFactory ic = null;

        if(formName == "LoginForm") {
            ic = new CreateWindowLogin();
        }else if(formName == "SignUpForm"){
            ic = new CreateWindowSignUpForm();
        }

        return ic;

    }
}
