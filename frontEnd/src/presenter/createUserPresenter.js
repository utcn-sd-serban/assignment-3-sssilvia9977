import model from "../model/model";

class CreateUserPresenter {

    onCreate() {
        model.addUser(model.state.newUser.email, model.state.newUser.firstName, model.state.newUser.lastName, model.state.newUser.password);
        model.changeNewUserProperty("email", "");
        model.changeNewUserProperty("firstName", "");
        model.changeNewUserProperty("lastName", "");
        model.changeNewUserProperty("password", "");
        window.location.assign("#/");
    }

    onChange(property, value) {
        model.changeNewUserProperty(property, value);
    }

}

const createUserPresenter = new CreateUserPresenter();

export default createUserPresenter;