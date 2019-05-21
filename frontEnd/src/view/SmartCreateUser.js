import React, { Component } from "react";
import model from "../model/model"
import CreateQuestion from "./CreateQuestion";
import createUserPresenter from "../presenter/createUserPresenter";
import CreateUser from "./CreateUser";

const mapModelStateToComponentState = modelState => ({
    firstName: modelState.newUser.firstName,
    lastName: modelState.newUser.lastName,
    email: modelState.newUser.email,
    password: modelState.newUser.password

});

export default class SmartCreateUser extends Component{
    constructor()
    {
        super();
        this.state = mapModelStateToComponentState(model.state)
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        model.addListener("change", this.listener);

    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render(){
        return(
            <CreateUser
                onCreate={createUserPresenter.onCreate}
                onChange={createUserPresenter.onChange}

                firstName={this.state.firstName}
                lastName={this.state.lastName}
                email={this.state.email}
                password={this.state.password} />
        );
    }

}