import java.io.Serializable;

//Joey Liao
//Assignment 8

public class MultipleChoiceQuestion extends Question{
    private String[] possibleAnswers;
    
    public MultipleChoiceQuestion(String question, String answer, String[] possibleAnswers){
        super(question, answer);
        this.possibleAnswers = possibleAnswers;
    }

    public void setPossibleAnswers(String[] possibleAnswers){
        this.possibleAnswers = possibleAnswers;
    }

    public String[] getPossibleAnswers(){
        return possibleAnswers;
    }
    
    @Override
	public String getClassName() {
    	return "MultipleChoiceQuestion";
    }
    
    @Override
    public MultipleChoiceQuestion clone(){
        MultipleChoiceQuestion temp = new MultipleChoiceQuestion(super.askQuestion(), super.getAnswer(), possibleAnswers.clone());
        return temp;
    }

//    @Override
//    public String askQuestion(){
//        String result = super.askQuestion() + "\n";
//        for(int i=0; i<possibleAnswers.length; i++){
//            result += ((i+1) + ". " + possibleAnswers[i] + "\n");
//        }
//        return result;
//    }

    @Override
    public String toString(){
        String result = super.askQuestion() + "\n";
        for(int i=0; i<possibleAnswers.length; i++){
            result += ((i+1) + ". " + possibleAnswers[i] + "\n");
        }
        return result;
    }
}