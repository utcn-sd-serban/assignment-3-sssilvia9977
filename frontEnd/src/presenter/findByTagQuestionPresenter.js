import model from "../model/model";

class FindByTagQuestionPresenter {

    onSearchTagPresenter() {

        model.findByTag();

    }



}

const findByTagQuestionPresenter = new FindByTagQuestionPresenter();

export default findByTagQuestionPresenter;