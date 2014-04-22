package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends ActionBarActivity {

//    private Button mTrueButton;
//    private Button mFalseButton;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

    	private Button mNextButton;
    	private TextView mQuestionTextView;
    	private TrueFalse[] mQuestionBank = new TrueFalse[]{
    		new TrueFalse(R.string.question_oceans,true),	
    		new TrueFalse(R.string.question_mideast,false),
    		new TrueFalse(R.string.question_africa, false),
    		new TrueFalse(R.string.question_america, true),
    		new TrueFalse(R.string.question_asia, true),
    	};
    	private int mCurrentIndex = 0;
        public PlaceholderFragment() {
        }
        
        private void updateQuestion() {
        	int question = mQuestionBank[mCurrentIndex].getQuestion();
        	mQuestionTextView.setText(question);
        }

        private void checkAnswer(boolean userPressedTrue) {
        	boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        	int messageResId = 0;
        	if (userPressedTrue == answerIsTrue) {
        		messageResId = R.string.correct_toast;
        	} else {
        		messageResId = R.string.incorrect_toast;
        	}
        	Toast.makeText(getActivity(), messageResId, Toast.LENGTH_SHORT)
        		.show();
        	
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

            Button mTrueButton = (Button)rootView.findViewById(R.id.true_button);
            mTrueButton.setOnClickListener(new  View.OnClickListener(){
                @Override
                public void onClick(View v) {
                	checkAnswer(true);
                }
            });

            Button mFalseButton = (Button)rootView.findViewById(R.id.false_button);
            mFalseButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                	checkAnswer(false);
                }
            });
            
            mQuestionTextView = (TextView) rootView.findViewById(R.id.question_text_view);
            
            mNextButton = (Button) rootView.findViewById(R.id.next_button);
            mNextButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
					updateQuestion();
				}
			});

            return rootView;
        }
    }
}
