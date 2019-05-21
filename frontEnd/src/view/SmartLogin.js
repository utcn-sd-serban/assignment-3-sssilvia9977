import React, { Component } from "react";
import model from "../model/model"
import loginPresenter from "../presenter/loginPresenter";
import CreateUser from "./CreateUser";
import Login from "./Login";

const mapModelStateToComponentState = modelState => ({
    firstName: modelState.newUser.firstName,
    lastName: modelState.newUser.lastName,
    email: modelState.newUser.email,
    password: modelState.newUser.password

});

export default class SmartLogin extends Component{
    constructor()
    {
        super();
        this.state = mapModelStateToComponentState(model.state)
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        model.addListener("change", this.listener);
        model.loadAllUsers();
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render(){
        return(
            <Login
                onLogin={loginPresenter.onLogin}
                onChange={loginPresenter.onChange}
                onCreateUser={loginPresenter.onCreateUser}

                email={this.state.email}
                password={this.state.password} />
        );
    }

}