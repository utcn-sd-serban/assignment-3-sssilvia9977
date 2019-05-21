import React from "react";

const QuestionSearchTag = ({ questionsByTags  }) => (



    <div >


        <section className="hero is-fullheight is-primary is-bold">
            <div className="hero-body">

                <div className="container is-centered">


                    <div className="tile is-ancestor">
                        <div className="tile is-4 is-vertical is-parent">
                            <div className="tile is-child ">
                                <p className="title">Searched by tag</p>

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
                                    </tr>
                                    </thead>

                                    <tbody>
                                    {
                                        questionsByTags.map((question, index) => (    //here i could pass the id of the question/answer
                                            <tr key={index}>
                                                <td>{question.idQuestion}</td>
                                                <td>{question.user}</td>
                                                <td>{question.title}</td>
                                                <td>{question.text}</td>
                                                <td>{question.creationDate}</td>
                                            </tr>
                                        ))
                                    }
                                    </tbody>
                                </table>



                            </div>
                        </div>
                    </div>

                </div>
            </div>



        </section>




    </div>

);

export default QuestionSearchTag;