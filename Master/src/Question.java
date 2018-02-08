import java.io.Serializable;

//Joey Liao
//Assignment 8

public class Question implements Serializable{
	private String question, answer;
	
	public Question(String question){
		this.question = question;
	}
	
	public Question(String question, String answer){
		this.question = question;
		this.answer = answer;
	}

	public String askQuestion(){
		return question;
	}

	public boolean check(String userAnswer){
		return answer.equals(userAnswer);
	}

    protected String getAnswer(){
      return answer;
    }	

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public void setQuestion(String question){
		this.question = question;
	}
	
	public String getClassName() {
    	return "Question";
    }

    @Override
	public Question clone(){
		return new Question(question, answer);
	}

	@Override
	public String toString(){
		return question + "\n" + answer + "\n";
	}
}