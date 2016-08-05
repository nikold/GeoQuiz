package com.example.nikol.geoquiz;

/**
 * Created by nikol on 02.08.2016.
 */
class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId,boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
