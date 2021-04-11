/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NangCao2;

/**
 *
 * @author jason
 */
public class Choice {

    private String question;
    private String choice;
    private boolean selected;
    private String marked;

    public Choice(String question, String choice, boolean selected, String marked) {
        this.question = question;
        this.choice = choice;
        this.selected = selected;
        this.marked = marked;
    }

    public Choice() {
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    
}
