import React from "react";

const QuestionDetails = ({ title, text, tags}) => (

    <div >


        <section className="hero is-fullheight is-primary is-bold">
            <div className="hero-body">

                <div className="container is-centered">


                    <div className="tile is-ancestor">
                        <div className="tile is-4 is-vertical is-parent">


                            <p className="title is-1">Question</p>

                            <p className="title is-2 is-spaced">Title:</p>
                            <span className="subtitle is-3"> { title }</span>
                            <br />

                            <p className="title is-2 is-spaced">Text:</p>
                            <span className="subtitle is-3"> { text }</span>
                            <br />
                            <br />


                            <div>

                                <div className="tags">
                                    {
                                        tags.map((tag, index) => (
                                            <li key = {index}>
                                                {

                                                    <span className="tag is-warning is-large" > {tag}  </span>

                                                }
                                            </li>
                                        ))
                                    }


                                </div>



                            </div>




                        </div>
                    </div>

                </div>
            </div>



        </section>










    </div>







);


export default QuestionDetails;