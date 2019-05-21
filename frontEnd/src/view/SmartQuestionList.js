import React, { Component } from "react";
import model from "../model/model";
import QuestionsList from "./QuestionList";
import questionListPresenter from "../presenter/questionListPresenter";
import findByTitleQuestionPresenter from "../presenter/findByTitleQuestionPresenter";

const mapModelStateToComponentState = modelState => ({
    questions: modelState.questions

});

export default class SmartQuestionList extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(model.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        model.addListener("change", this.listener);
        model.loadAllQuestions();

    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <QuestionsList
                onViewDetails={questionListPresenter.onViewDetails}
                onCreateQuestion={questionListPresenter.onCreateQuestion}
                onSearchQuestionByTitle={questionListPresenter.onSearchQuestionByTitle}
                onSearchQuestionByTag={questionListPresenter.onSearchQuestionByTag}
                onChange={questionListPresenter.onChangeSearchedTitle}
                questions={this.state.questions} />
        );
    }
}
