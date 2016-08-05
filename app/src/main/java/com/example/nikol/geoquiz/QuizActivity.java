package com.example.nikol.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private TextView mQuestionTextView;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX ="index";

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
        Button mTrueButton = (Button) findViewById(R.id.true_button);
        assert mTrueButton != null;
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        Button mFalseButton = (Button) findViewById(R.id.false_button);
        assert mFalseButton != null;
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        int orientation=this.getResources().getConfiguration().orientation;
        if (orientation == 2){
            Button mNextButton = (Button) findViewById(R.id.next_button);
            assert mNextButton != null;
            mNextButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    setNextCurrentIndex();
                    updateQuestion();
                }
            });
        }else {
            ImageButton mNextImageButton = (ImageButton) findViewById(R.id.next_button);
            assert mNextImageButton != null;
            mNextImageButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    setNextCurrentIndex();
                    updateQuestion();
                }
            });

            ImageButton mPrevImageButton = (ImageButton) findViewById(R.id.prev_button);
            assert mPrevImageButton != null;
            mPrevImageButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    setPrevCurrentIndex();
                    updateQuestion();
                }
            });
        }

        mQuestionTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setNextCurrentIndex();
                updateQuestion();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void setNextCurrentIndex() {
        if(mQuestionBank.length - 1 == mCurrentIndex)
        {mCurrentIndex=0;}
        else
        { mCurrentIndex++;}
    }

    private void setPrevCurrentIndex() {
        if(0 == mCurrentIndex)
        {mCurrentIndex= mQuestionBank.length - 1;}
        else
        { mCurrentIndex--;}
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId;
        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
