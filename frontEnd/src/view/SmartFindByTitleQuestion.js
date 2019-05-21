import React, { Component } from "react";
import model from "../model/model";
import findByTitleQuestionPresenter from "../presenter/findByTitleQuestionPresenter"
import QuestionSearchTitleList from "./QuestionSearchTitleList";

const mapModelStateToComponentState = modelState => ({
    questionsByTitle: modelState.questionsByTitle
});

export default class SmartFindByTitleQuestion extends Component {
    constructor() {
        super();
        const x = model.state;
        this.state = mapModelStateToComponentState(model.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        model.addListener("change", this.listener);
        debugger;
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <QuestionSearchTitleList
                onSearchTitlePresenter={findByTitleQuestionPresenter.onSearchTitlePresenter}
                questionsByTitle={this.state.questionsByTitle}
            />
        );
    }
}
