package com.example.task32;

public class QuestionModel {
    private String questionTxt;
    private String[] opstions;
    private int correctIndex =0;

    public QuestionModel(String questionTxt, String[] opstions, int correctIndex) {
        this.questionTxt = questionTxt;
        this.opstions = opstions;
        this.correctIndex = correctIndex;
    }

    public String getQuestionTxt() {
        return questionTxt;
    }

    public void setQuestionTxt(String questionTxt) {
        this.questionTxt = questionTxt;
    }

    public String[] getOpstions() {
        return opstions;
    }

    public void setOpstions(String[] opstions) {
        this.opstions = opstions;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void setCorrectIndex(int correctIndex) {
        this.correctIndex = correctIndex;
    }
}
