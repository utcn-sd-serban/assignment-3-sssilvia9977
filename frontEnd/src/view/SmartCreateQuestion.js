import React, { Component } from "react";
import model from "../model/model";
import CreateQuestion from "./CreateQuestion";
import createQuestionPresenter from "../presenter/createQuestionPresenter";

const mapModelStateToComponentState = modelState => ({
    user: modelState.newQuestion.user,
    title: modelState.newQuestion.title,
    text: modelState.newQuestion.text,
    tags: modelState.newQuestion.tags
});

export default class SmartCreateQuestionList extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(model.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        model.addListener("change", this.listener);
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <CreateQuestion
                onCreate={createQuestionPresenter.onCreate}
                onChange={createQuestionPresenter.onChange}
                onChangeTags={createQuestionPresenter.onChangeTags}

                user={this.state.user}
                title={this.state.title}
                text={this.state.text}
                tags={this.state.tags}
            />
        );
    }
}
