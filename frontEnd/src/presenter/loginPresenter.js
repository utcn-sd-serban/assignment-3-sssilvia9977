import model from "../model/model";

class LoginPresenter {

    onLogin() {
        model.login();
        model.changeNewUserProperty("email", "");
        model.changeNewUserProperty("password", "");



    }

    onChange(property, value) {
        model.changeCurrentUserProperty(property, value);
    }

    onCreateUser(){
        window.location.assign("#/create-user")
    }

}

const loginPresenter = new LoginPresenter();

export default loginPresenter;