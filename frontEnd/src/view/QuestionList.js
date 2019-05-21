import React from "react";
import "bulma/css/bulma.css";


const QuestionsList = ({ onChange: onChangeSearchedTitle, questions, onCreateQuestion,onViewDetails , onSearchQuestionByTitle , onSearchQuestionByTag, searchedTitle}) => (


    <div >


        <section className="hero is-fullheight is-primary is-bold">
            <div className="hero-body">

            <div className="container is-centered">


                <div className="tile is-ancestor">
                    <div className="tile is-4 is-vertical is-parent">
                        <div className="tile is-child ">
                            <p className="title">Your existential Questions</p>
                            <p className="subtitle">Where no one cares</p>


                        </div>
                        <div className="tile is-child ">

                            <article className="tile is-child">
                                <p className="control">
                                    <div className="level-left" >
                                        <div  className = "level-item">
                                            <input
                                                className="input is-rounded is-focused" type="text" placeholder="Search question"
                                                value={searchedTitle} onChange = {e => onChangeSearchedTitle("searchedTitle", e.target.value)}/>
                                        </div>
                                    </div>
                                </p>

                                <p className="control">
                                    <a className="button is-rounded is-focused"
                                       onClick={onSearchQuestionByTitle}>Search by title
                                    </a>
                                </p>


                                <p className="control">
                                    <a className="button is-rounded is-focused"
                                       onClick={onSearchQuestionByTag}>Search by tag
                                    </a>
                                </p>

                            </article>


                        </div>
                    </div>
                    <div className="tile is-parent">
                        <div className="tile is-child ">

                            <table className="table  is-striped " >
                                <thead>
                                <tr>
                                    <th>IdQ</th>
                                    <th>User</th>
                                    <th>Title</th>
                                    <th>Question</th>
                                    <th>Date</th>
                                    <th>View Details</th>
                                </tr>
                                </thead>

                                <tbody>
                                {
                                    questions.map((question, index) => (    //here i could pass the id of the question/answer
                                        <tr key={index}>
                                            <td>{question.idQuestion}</td>
                                            <td>{question.user}</td>
                                            <td>{question.title}</td>
                                            <td>{question.text}</td>
                                            <td>{question.creationDate}</td>
                                            <td>

                                                <a className="button is-rounded is-focused"
                                                   onClick={() => onViewDetails(index)}>Apasa-l!
                                                </a>

                                            </td>
                                        </tr>
                                    ))
                                }
                                </tbody>
                            </table>

                            <a className="button is-rounded is-focused"
                               onClick={onCreateQuestion} >Click here to add new question
                            </a>

                        </div>
                    </div>
                </div>

            </div>
            </div>



        </section>










    </div>
);

export default QuestionsList;