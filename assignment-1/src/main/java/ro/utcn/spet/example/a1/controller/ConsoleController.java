/*package ro.utcn.spet.example.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.entity.*;
import ro.utcn.spet.example.a1.exception.QuestionNotFoundException;
import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
import ro.utcn.spet.example.a1.service.*;

import java.security.PrivilegedAction;
import java.util.*;
import java.util.stream.Collectors;

import static ro.utcn.spet.example.a1.entity.VoteValue.DOWN;
import static ro.utcn.spet.example.a1.entity.VoteValue.UP;
import static ro.utcn.spet.example.a1.entity.VoteValue.ZERO;

@Component
@RequiredArgsConstructor
// Command line runners are executed by Spring after the initialization of the app has been done
// https://www.baeldung.com/spring-boot-console-app
public class ConsoleController implements CommandLineRunner {
	private final Scanner scanner = new Scanner(System.in);
	private StackUser stackUser;

	private final StackUserManagementService stackUserManagementService;
	private final QuestionManagementService questionManagementService;
	private final TagManagementService tagManagementService;
	private final TagQuestionManagementService tagQuestionManagementService;
	private final AnswerManagementService answerManagementService;
	private final VoteAnswerManagementService voteAnswerManagementService;
	private final VoteQuestionManagementService voteQuestionManagementService;

	@Override
	public void run(String... args) {
		String action = "";
		print("Please log in");
		boolean done = handleLogIn();
		if(!done ){
			done = false;
			print("Do you want to handle users [u] or questions [q]?");
			action = scanner.nextLine().trim();
		}
		while (!done) {
			print("Enter a command: ");
			String command = scanner.nextLine().trim();
			switch (action){
				case "u":
					try { done = handleCommandStackUser(command); }
					catch (StackUserNotFoundException stackUserNotFoundException) {
						print("The User with the given ID was not found!"); }
					break;
				case "q":
					try { done = handleCommandQuestion(command); }
					catch (QuestionNotFoundException questionNotFoundException) {
						print("The question with the given ID was not found!");
					}
					break;
				case "exit":
						done = true;
					break;
				default:
					print("Wrong type");
					return;

			}


		}
	}


	private boolean handleCommandStackUser(String command) {

		switch (command) {
			case "list":
				handleStackUserList();
				return false;
			case "add":
				handleStackUserAdd();
				return false;
			case "update":
				handleStackUserUpdateEmail();
				return false;
			case "remove":
				handleStackUserRemove();
				return false;
			case "exit":
				return true;
			default:
				print("Unknown command user handle. Try again.");
				return false;
		}
	}


	private boolean handleCommandQuestion(String command) {

		switch (command) {
			case "add q":
				handleQuestionAdd();
				return false;
			case "list q":
				handleFindAllQuestions();
				return false;
			case "remove q":
				handleQuestionRemove();
				return false;
			case "filter title":
				handleFilterTitleQuestions();
				return false;
			case "filter tag":
				handleFilterTagQuestions();
				return false;
			case "see tags":
				handleListAllTags();
				return false;
			case "add answer":
				handleAddAnswerForParticularQuestion();
				return false;
			case "delete answer":
				handleDeleteUserAnswer();
				return false;
			case "update answer":
				handleUpdateUserAnswer();
				return false;
			case "vote q":
				handleVoteQuestion();
				return false;
			case "vote answer":
				handleVoteAnswer();
				return false;
			case "exit":
				return true;
			default:
				print("Unknown command question handdle. Try again.");
				return false;
		}
	}


	private boolean handleLogIn(){
		print("First name:");
		String firstName = scanner.nextLine().trim();
		print("Last name:");
		String lastName = scanner.nextLine().trim();
		print("Password");
		String password = scanner.nextLine().trim();
		if( stackUserManagementService.findStackUser(firstName,lastName,password).isPresent()){
			print("Welcome!");
			stackUser = stackUserManagementService.findStackUser(firstName, lastName,password).get();
			return false;
		}else{
			print("No such user");
			return true;
		}

	}

	private void handleStackUserList() {
		stackUserManagementService.listStackUser().forEach(s -> print(s.toString()));
	}

	private void handleStackUserAdd() {
		print("First name:");
		String firstName = scanner.next().trim();
		print("Last name:");
		String lastName = scanner.next().trim();
		StackUser stackUser = stackUserManagementService.addStackUser(firstName, lastName);
		print("Created stackUser: " + stackUser + ".");
	}

	private void handleStackUserUpdateEmail() {
		print("handleStackUserList ID:");
		int id = scanner.nextInt();
		print("Email address:");
		String emailAddress = scanner.next().trim();
		stackUserManagementService.updateEmailAddress(id, emailAddress);
	}

	private void handleStackUserRemove() {
		print("handleStackUserList ID:");
		int id = scanner.nextInt();
		stackUserManagementService.removeStudent(id);
	}


	private void handleQuestionAdd(){
		print("Title:");
		String title = scanner.nextLine().trim();

		print("Text:");
		String text = scanner.nextLine().trim();
		questionManagementService.addQuestion(stackUser.getId(), title, text);

		print("Do you want to add a tag? [y]/[n]");
		boolean role = false;
		String option = scanner.nextLine().trim();
		if(option.equals("y")){
			role = true;
		}
		while(role){
			print("Enter tag. Press [x] to exit." );
			String tagName = scanner.nextLine().trim();
			if(tagName.equals("x"))
				role = false;
			else{
				tagManagementService.addTag(tagName);
				tagQuestionManagementService.addTagQuestion(questionManagementService.filterTitle(title).get().getId(), tagManagementService.findTag(tagName).get().getId());
			}
		}


		print("Created question.");

	}

	public void handleListAllTags(){
		tagManagementService.findAll().forEach(s -> print(s.toString()));
	}


	public void handleQuestionRemove(){
		print("Title:");
		String title = scanner.nextLine().trim();
		questionManagementService.remove(title);
		print("Question removed");
	}



	private void handleFindAllQuestions(){
		List<Question> reverseQuestion = questionManagementService.findAllQuestions();
		Collections.reverse(reverseQuestion);
		reverseQuestion.forEach(s -> print("VC: "+ calculateVoteCountQuestion(s) +" " + s.toString()));
	}

	private void handleFilterTitleQuestions(){
		print("Title:");
		int i =0;
		String title = scanner.nextLine().trim();
		Question question = questionManagementService.filterTitle(title).get();
		print("VC: "+ calculateVoteCountQuestion(question) +" "+ questionManagementService.filterTitle(title).get().getText());
		if(answerManagementService.findQuestionAllAnswers(question.getId()).size()  == 0){
			print("For the moment there is no answer");
		}
		else {
			List<Answer> filterTitle =  answerManagementService.findQuestionAllAnswers(question.getId());

			//sort the answer list for a question with respect to VC
			Collections.sort(filterTitle, new Comparator<Answer>() {
				@Override
				public int compare(Answer o1, Answer o2) {
					return calculateVoteCountAnswer(o1) > calculateVoteCountAnswer(o2) ? -1 : (calculateVoteCountAnswer(o1) < calculateVoteCountAnswer(o2) ? 1 :0);
				}
			});

			for(Answer answer : filterTitle)
			{
			//	questionsWithTag.add(answerManagementService.findById(answer.getQuestionId()).get());
				print( "VC: "+ calculateVoteCountAnswer(answer)+ " Answer: " +  answer.getText() );

			}
		}
	}

	private void handleVoteQuestion(){
		List<Question> questionsToVote = questionManagementService.findAllQuestions().stream().filter(
				(Question q) -> q.getUserId() != stackUser.getId()).collect(Collectors.toList());
		//mai sus fac toate intrebarile pe care le pot vota

		if(questionsToVote.size() == 0){
			print("You have no questions to vote.");
		}
		else{
			questionsToVote.forEach((Question q)-> print("VC: "+ calculateVoteCountQuestion(q)+ q.toString()));
			print("Enter the id of the question you want to vote");
			String qid = scanner.nextLine().trim();
			if(questionsToVote.stream().filter((Question q)-> q.getId().equals(Integer.parseInt(qid))).collect(Collectors.toList()).size() != 0){
				// verific daca id question pe care l a dat userul chiar este printre intrebarile lui si nu in alta parte
				print("Enter [up] / [down] / [zero]");
				String readValue = scanner.nextLine().trim();
				int value=0;
				switch (readValue){
					case "up":
						value = UP;
						break;
					case "down":
						value = DOWN;
						break;
				}
				voteQuestionManagementService.addVoteQuestion(stackUser,Integer.parseInt(qid) , value);
			}
		}

	}



	private void handleFilterTagQuestions(){
		print("Tag:");
		String tag = scanner.nextLine().trim();
		Optional<Tag> myTag = tagManagementService.findTag(tag);
		List<Tag_question> filterTag =  tagQuestionManagementService.findTagforQuestion(myTag.get().getId());//avem toate tag questionurile cu tagu nostru
		List<Question> questionsWithTag = new ArrayList<>();
		for(Tag_question tag_question : filterTag)
		{
			questionsWithTag.add(questionManagementService.findById(tag_question.getQuestionId()).get());
			print("VC: "+ calculateVoteCountQuestion(questionManagementService.findById(tag_question.getQuestionId()).get()) +" "+"Title: " + questionManagementService.findById(tag_question.getQuestionId()).get().getTitle());
			print("Text: " + questionManagementService.findById(tag_question.getQuestionId()).get().getText());

		}
	}


	private void handleAddAnswerForParticularQuestion(){
		print("Enter the question`s title.");
		String questionTitle = scanner.nextLine().trim();
		Question question = questionManagementService.filterTitle(questionTitle).get();

		print("Enter the answer.");
		String questionAnswer = scanner.nextLine().trim();
		answerManagementService.addAnswer(stackUser.getId(), question.getId(), questionAnswer);

		print("Your answer was saved.");
	}


	private void handleDeleteUserAnswer(){
		List<Answer> usersAns = answerManagementService.findUserAllAnswers(stackUser.getId());
		usersAns.forEach(s-> print("VC: "+ calculateVoteCountAnswer(s)+" "+s.toString()));

		if(usersAns.size()==0){
			print("You have given no answer.");
		}
		else{
			print("Enter the id of the answer you want to delete");
			String answerId = scanner.nextLine().trim();
			if(stackUser.getId().equals(answerManagementService.findById(Integer.parseInt(answerId)).get().getUserId())){
				answerManagementService.remove(Integer.parseInt(answerId));
				print("Removed");
			}
			else{
				print("You have no such answer.");
			}
		}

	}


	private void handleUpdateUserAnswer(){
		List<Answer> usersAns = answerManagementService.findUserAllAnswers(stackUser.getId());
		usersAns.forEach(s-> print("VC: "+ calculateVoteCountAnswer(s)+" "+s.toString()));

		if(usersAns.size()==0){
			print("You have given no answer.");
		}
		else{
			print("Enter the id of the answer you want to update");
			String answerId = scanner.nextLine().trim();
			if(stackUser.getId().equals(answerManagementService.findById(Integer.parseInt(answerId)).get().getUserId())){
				print("Enter the new text.");
				String newText = scanner.nextLine().trim();
				answerManagementService.updateText(Integer.parseInt(answerId), newText);
				print("Done.");
			}
			else{
				print("You have no such answer.");
			}
		}

	}



	private void handleVoteAnswer(){
		List<Answer> answersToVote = answerManagementService.findAllAnswers().stream().filter(
				(Answer q) -> q.getUserId() != stackUser.getId()).collect(Collectors.toList());
		//mai sus fac toate intrebarile pe care le pot vota

		if(answersToVote.size() == 0){
			print("You have no questions to vote.");
		}
		else{
			answersToVote.forEach((Answer q)-> print("VC: "+ calculateVoteCountAnswer(q)+ q.toString()));
			print("Enter the id of the question you want to vote");
			String qid = scanner.nextLine().trim();
			if(answersToVote.stream().filter((Answer q)-> q.getId().equals(Integer.parseInt(qid))).collect(Collectors.toList()).size() != 0){
				// verific daca id question pe care l a dat userul chiar este printre intrebarile lui si nu in alta parte
				print("Enter [up] / [down] / [zero]");
				String readValue = scanner.nextLine().trim();
				int value=0;
				switch (readValue){
					case "up":
						value = UP;
						break;
					case "down":
						value = DOWN;
						break;
					case "zero":
						value = ZERO;
						break;
				}
				voteAnswerManagementService.addVoteAnswer(stackUser,Integer.parseInt(qid) , value);
			}
		}

	}



	private int calculateVoteCountQuestion(Question question){
		List<VoteQuestion> voteQuestions = voteQuestionManagementService.listAllVotesQuestion(question.getId());
		int count = 0;
		for (VoteQuestion v : voteQuestions ){
			count += v.getValue();
		}
		return count;
	}

	private int calculateVoteCountAnswer(Answer answer){
		List<VoteAnswer> voteAnswers = voteAnswerManagementService.listAllVotesAnswer(answer.getId());
		int count = 0;
		for (VoteAnswer v : voteAnswers ){
			count += v.getValue();
		}
		return count;
	}


	private void print(String value) {
		System.out.println(value);
	}
}
*/