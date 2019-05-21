import model from "../model/model";
import findByTitleQuestionPresenter from "./findByTitleQuestionPresenter";
import findByTagQuestionPresenter from "./findByTagQuestionPresenter";

class QuestionsListPresenter {



    onCreateQuestion(){
        window.location.assign("#/create-question")
    }

    onViewDetails(index){
        window.location.assign("#/questions-details/" + index)
    }

    onChangeSearchedTitle(property, value)
    {
        model.changeSearchTitleProperty(property, value);
    }

    onSearchQuestionByTitle(){

        findByTitleQuestionPresenter.onSearchTitlePresenter();
        window.location.assign("#/search-question-title")
    }

    onSearchQuestionByTag(){
        findByTagQuestionPresenter.onSearchTagPresenter();
        window.location.assign("#/search-question-tag")
    }






}

const questionListPresenter = new QuestionsListPresenter();

export default questionListPresenter;