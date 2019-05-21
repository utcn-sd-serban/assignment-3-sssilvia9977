import React, { Component } from "react";
import model from "../model/model";
import findByTagQuestionPresenter from "../presenter/findByTagQuestionPresenter"
import QuestionSearchTag from "./QuestionSearchTag";

const mapModelStateToComponentState = modelState => ({
    questionsByTags: modelState.questionsByTags
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
            <QuestionSearchTag
                onSearchTagPresenter={findByTagQuestionPresenter.onSearchTagPresenter}
                questionsByTags={this.state.questionsByTags}
            />
        );
    }
}
