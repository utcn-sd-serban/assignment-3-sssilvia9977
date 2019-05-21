import React from "react";

const CreateQuestion = ({  title, text, tags, onCreate, onChange, onChangeTags }) => (
    <div >


        <section className="hero is-fullheight is-primary is-bold">
            <div className="hero-body">

                <div className="container is-centered">


                    <div className="tile is-ancestor">
                        <div className="tile is-4 is-vertical is-parent">
                            <div className="tile is-child ">
                                <p className="title">  Post a question </p>

                            </div>
                            <div className="tile is-child ">

                                <article className="tile is-child">
                                    <p className="control">
                                        <div className="level-left" >
                                            <div  className = "level-item">
                                                <input
                                                    className="input is-rounded is-focused" type="text" placeholder="Enter title"
                                                    value={title} onChange={ e => onChange("title", e.target.value)  }/>
                                            </div>
                                        </div>
                                    </p>

                                    <p className="control">
                                        <div className="level-left" >
                                            <div  className = "level-item">
                                                <input
                                                    className="input is-rounded is-focused" type="text" placeholder="Enter text"
                                                    value={text} onChange={e => onChange("text", e.target.value)}/>
                                            </div>
                                        </div>
                                    </p>

                                    <p className="control">
                                        <div className="level-left" >
                                            <div  className = "level-item">
                                                <input
                                                    className="input is-rounded is-focused" type="text" placeholder="Enter tags, split them by comma"
                                                    value={tags} onChange={e => onChangeTags(e.target.value)} />
                                            </div>
                                        </div>
                                    </p>

                                    <p className="control">
                                        <div className="level-left" >
                                            <div  className = "level-item">
                                                <a className="button is-rounded is-focused"
                                                   onClick={onCreate}>Create!
                                                </a>
                                            </div>
                                        </div>
                                    </p>

                                </article>


                            </div>
                        </div>

                    </div>
                </div>

            </div>




</section>










</div>

);

export default CreateQuestion;