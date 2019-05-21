import React from 'react';
import './App.css';
import SmartQuestionList from "./view/SmartQuestionList";
import {HashRouter, Switch, Route} from "react-router-dom";
import SmartCreateQuestion from "./view/SmartCreateQuestion";
import SmartQuestionDetails from "./view/SmartQuestionDetails";
import SmartFindByTitleQuestion from "./view/SmartFindByTitleQuestion";
import SmartFindByTagQuestion from "./view/SmartFindByTagQuestion";
import SmartLogin from "./view/SmartLogin";
import SmartCreateUser from "./view/SmartCreateUser";




const App = () => (
    <div className = "App">
        <HashRouter >
            <Switch>
              <Route exact = {true} component={SmartLogin} path = "/" />
              <Route exact = {true} component={SmartQuestionList} path = "/all-questions" />
              <Route exact = {true} component={SmartCreateQuestion} path = "/create-question" />
              <Route exact = {true} component={SmartFindByTitleQuestion} path = "/search-question-title" />
              <Route exact = {true} component={SmartFindByTagQuestion} path = "/search-question-tag" />
              <Route exact = {true} component={SmartQuestionDetails} path = "/questions-details/:index" />
              <Route exact = {true} component={SmartCreateUser} path = "/create-user" />


            </Switch>



        </HashRouter>


    </div>
);

export default App;
