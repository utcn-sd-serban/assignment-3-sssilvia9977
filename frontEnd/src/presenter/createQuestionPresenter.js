import model from "../model/model";

class CreateQuestionsPresenter {

    onCreate() {
        model.addQuestion(model.state.newQuestion.title, model.state.newQuestion.text, model.state.newQuestion.tags);
        model.changeNewQuestionProperty("usderId", "");
        model.changeNewQuestionProperty("title", "");
        model.changeNewQuestionProperty("text", "");
        model.changeNewQuestionProperty("tags", []);
        window.location.assign("#/all-questions");
    }
    onChangeTags(value){
        model.changeNewQuestionProperty("tags",
            value.split(","));
    }
    onChange(property, value) {
        model.changeNewQuestionProperty(property, value);
    }

}

const createQuestionPresenter = new CreateQuestionsPresenter();

export default createQuestionPresenter;