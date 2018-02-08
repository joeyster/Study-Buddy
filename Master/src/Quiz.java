import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Quiz implements Serializable{
	private int questionCounter;
	private ArrayList<Question> q;

	public Quiz(){
		this.q = new ArrayList<>();
		this.questionCounter = 0;
	}
	
	public int getSize(){
		return questionCounter;
	}
	
	public void addQuestion(Question question){
		q.add(question);
		questionCounter++;
	}
	
	public Question getQuestion(int index) {
		Question tempQuestion = q.get(index);
		return tempQuestion;
	}
	


	@Override
	public String toString(){
		String temp = "";
		for(int i = 0; i < this.questionCounter; i++){
			temp += ((i+1) + ") " + q.get(i) + "\n");
 	 	}
 	 	return temp;
	}
}