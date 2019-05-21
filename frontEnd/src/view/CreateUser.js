import React from "react";

const CreateUser = ({  email, firstName, lastName, password, onCreate, onChange }) => (
    <div >


        <section className="hero is-fullheight is-primary is-bold">
            <div className="hero-body">

                <div className="container is-centered">


                    <div className="tile is-ancestor">
                        <div className="tile is-4 is-vertical is-parent">
                            <div className="tile is-child ">
                                <p className="title">  Create an account </p>

                            </div>
                            <div className="tile is-child ">

                                <article className="tile is-child">
                                    <p className="control">
                                        <div className="level-left" >
                                            <div  className = "level-item">
                                                <input
                                                    className="input is-rounded is-focused" type="text" placeholder="Enter firstName"
                                                    value={firstName} onChange={ e => onChange("firstName", e.target.value)  }/>
                                            </div>
                                        </div>
                                    </p>

                                    <p className="control">
                                        <div className="level-left" >
                                            <div  className = "level-item">
                                                <input
                                                    className="input is-rounded is-focused" type="text" placeholder="Enter lastName"
                                                    value={lastName} onChange={e => onChange("lastName", e.target.value)}/>
                                            </div>
                                        </div>
                                    </p>

                                    <p className="control">
                                        <div className="level-left" >
                                            <div  className = "level-item">
                                                <input
                                                    className="input is-rounded is-focused" type="text" placeholder="Enter email"
                                                    value={email} onChange={e => onChange("email", e.target.value)} />
                                            </div>
                                        </div>
                                    </p>


                                    <p className="control">
                                        <div className="level-left" >
                                            <div  className = "level-item">
                                                <input
                                                    className="input is-rounded is-focused" type="text" placeholder="Enter password"
                                                    value={password} onChange={e => onChange("password", e.target.value)} />
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

export default CreateUser;