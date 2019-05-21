import { EventEmitter } from "events";
import RestClient from "../rest/RestClient";

const client = new RestClient("A.B@example.com", "AB");

class Model extends EventEmitter{
    constructor() {
        super();
        this.client = new RestClient();
        this.state = {
            questions: [{
                idQuestion: 1,
                user: "Billy",
                title: "Mancare",
                text: "Cum gatiti macroul?",
                tags: ["food", "for soul"],
                creationDate: "Mon Apr 13 2019"


            }, {
                idQuestion: 2,
                user: "Sally",
                title: "Desert",
                text: "Inainte sau dupa?",
                tags: ["lavaCake", "fructe", "dupa SD si rasplata"],
                creationDate: "Tus Apr 14 2019"
            }],

            newQuestion: {
                userId: "" ,
                title: "",
                text: "",
                tags: [],
                creationDate: ""
            },

            questionsByTitle: [],
            questionsByTags:[] ,
            searchedTitle: "",
            idQuestionRemember: 2,


            appUsers: [{
                idUser:1,
                email: "A.B@example.com",
                firstName:"A",
                lastName: "B",
                password: "AB"
            },{
                idUser:2,
                email: "C.D@example.com",
                firstName:"C",
                lastName: "D",
                password: "CD"
            }],

            newUser: {
                email: "",
                firstName: "",
                lastName: "",
                password: ""
            },

            currentUser: {
                userId: 1,
                email: "",
                password: "",
                firstName:""
            },
            idUserRemember:2,
            userFound : ""



        };

    }


    setAuthorization()
    {
        debugger;
        this.client.auth(this.state.currentUser.email, this.state.currentUser.password);
        this.client.setAuthorization();
        debugger;
    }

    addUser(email, firstName, lastName, password){
        this.state = {
            ...this.state,
            appUsers: this.state.appUsers.concat([{
                idUser: ++this.state.idUserRemember,
                email: email,
                firstName: firstName,
                lastName: lastName,
                password: password
            }])
        };
        this.emit("change", this.state);
    }

    //i have to change the url and to check if the user exists
    login(){

        this.setAuthorization();
        const id = this.state.appUsers.find(user => user.emailAddress === this.state.currentUser.email).id;
        if(id === 'undefined')
        {
            window.location.assign("#/");
        }
        else
        {
            this.state.currentUser.id = id;
            this.client.login(id, this.state.currentUser.email, this.state.currentUser.password).then(response=>{

                if (response.status === 200)
                {
                    window.location.assign("#/all-questions");
                }
                else
                {
                    window.location.assign("#/");
                }
            });
        }


    }


    changeCurrentUserProperty(property, value) {
        this.state = {
            ...this.state,
            currentUser: {
                ...this.state.currentUser,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }


    changeNewUserProperty(property, value) {
        this.state = {
            ...this.state,
            newUser: {
                ...this.state.newUser,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }



    loadAllQuestions(){

        debugger;
        return client.loadAllQuestions(this.state.currentUser.email, this.state.currentUser.password).then(questions =>{

            this.state = {
                ...this.state, questions: questions
            };
            debugger;
            this.emit("change", this.state);
        })
    }

    loadAllUsers()
    {

        return client.loadAllUsers().then(users =>{

            this.state = { ...this.state, appUsers: users  };
            this.emit("change", this.state);

        })
    }


    addQuestion(title, text, tags) {

        //here we get the id
        //nu uita de tag
        debugger
       return client.createQuestion( title, text, this.state.questions.length + 1, this.state.currentUser.email, tags).then(question =>
        {
            this.state = {
                ...this.state,
                questions: this.state.questions.concat([question])
            };
            this.emit("change", this.state);
            debugger

        })


    }


    changeNewQuestionProperty(property, value) {
            this.state = {
                ...this.state,
                newQuestion: {
                    ...this.state.newQuestion,
                    [property]: value
                }
            };
            this.emit("change", this.state);
    }

    changeSearchTitleProperty(property, value) {
        this.state = {
            ...this.state,
            searchedTitle: {
                ...this.state.searchedTitle,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }


    findByTag(){

        this.state = {
            ...this.state,
            questionsByTags : this.state.questions.filter( x => x.tags.indexOf(this.state.searchedTitle.searchedTitle) !== -1 )
        };
        this.emit("change", this.state);

    }

    findByTtile(){

        this.state = {
            ...this.state,
            questionsByTitle : this.state.questions.filter( x => x.title === (this.state.searchedTitle.searchedTitle)  )
        };
        this.emit("change", this.state);


    }



}

const  model = new Model();

export default model;

