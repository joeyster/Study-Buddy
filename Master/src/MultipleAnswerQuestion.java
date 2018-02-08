import java.io.Serializable;
import java.util.ArrayList;

public class MultipleAnswerQuestion extends Question{
	private ArrayList<String> answers;
    private String[] possibleAnswers;

    public MultipleAnswerQuestion(String question, ArrayList<String> answers, String[] possibleAnswers){
        super(question);
        this.answers = answers;
        this.possibleAnswers = possibleAnswers;
    }    
    
//    @Override
//    public String askQuestion(){
//        String result = super.askQuestion() + "\n";
//        for(int i=0; i<possibleAnswers.length; i++){
//            result += ((i+1) + ". " + possibleAnswers[i] + "\n");
//        }
//        
//        return result;
//    }
    

    @Override
    public String getAnswer(){
    	String result = "";
	    for(int i = 0; i < answers.size(); i++) {
	    	result += answers.get(i) + " ";
	    } 	    	
    	return result;
    }

    public void setPossibleAnswers(String[] possibleAnswers){
        this.possibleAnswers = possibleAnswers;
    }

    public String[] getPossibleAnswers(){
        return possibleAnswers;
    }

    @Override
    public MultipleAnswerQuestion clone(){
        MultipleAnswerQuestion temp = new MultipleAnswerQuestion(super.askQuestion(), this.answers, this.possibleAnswers.clone());
        return temp;
    }
    
    @Override
	public String getClassName() {
    	return "MultipleAnswerQuestion";
    }
    
    @Override
    public String toString(){
        String result = super.askQuestion() + "\n";
        for(int i=0; i< possibleAnswers.length; i++){
            result += ((i+1) + ". " + possibleAnswers[i] + "\n");
        }
        return result;
    }
}