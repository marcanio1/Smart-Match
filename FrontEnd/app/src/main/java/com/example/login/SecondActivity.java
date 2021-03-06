package com.example.login;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView question, qCount;
    private Button option1, option2, option3, option4, option5;
    private List<Question> questionList;
    int questNum;
    String quizResults = "";

    /**
     * Sets the global variables to the display boxes that will show the quiz
     *
     * @param savedInstanceState - Saves the current activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AppController();
        setContentView(R.layout.activity_second);
        //Map varibles to there objects in the xml
        question = findViewById(R.id.question);
        qCount = findViewById(R.id.quest_num);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        getQuestionList();
    }

    /**
     * Sets up the values to every question and corresponding answers for the quiz
     */
    private void getQuestionList() {
        questionList = new ArrayList<>();
        //todo - Place these questions in the database
        questionList.add(new Question("I am full of energy", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I can get blue/depressed", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I am quiet", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I am compassionate and have a soft heart", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I can be rude to others", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I am fascinated by art and literature", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I have few artistic interests", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I prefer to have others take charge", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I am dominate and act as a leader", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I am reliable and can always be counted on", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        questionList.add(new Question("I am original and come up with new ideas", "Disagree strongly", "Disagree a little", "Neutral", "Agree a little", "Agree strongly", 3));
        setQuestion();
    }

    /**
     * Sets the initial question and options
     */
    private void setQuestion() {
        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());
        option5.setText(questionList.get(0).getOptionE());
        qCount.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));
        questNum = 0;
    }

    /**
     * Saves the data from each question in the form of an integer array.
     *
     * @param view -The current view of the activity
     */
    @Override
    public void onClick(View view) {
        int selectedOption = 0;
        switch (view.getId()) {
            case R.id.option1:
                selectedOption = 1;
                break;
            case R.id.option2:
                selectedOption = 2;
                break;
            case R.id.option3:
                selectedOption = 3;
                break;
            case R.id.option4:
                selectedOption = 4;
                break;
            case R.id.option5:
                selectedOption = 5;
                break;
            default:
        }
        scoreAnswer(selectedOption);
    }

    /**
     * Saves the users data in a well formatted array
     *
     * @param selectedOption - The answer to the question
     */
    private void scoreAnswer(int selectedOption) {
        quizResults += selectedOption;
        //Added so that I would be able to parse the string
        quizResults += "/";
        changeQuestion();
    }

    /**
     * Plays an animation when the button is clicked
     * If there is no more questions moves to the next page
     */
    private void changeQuestion() {
        if (questNum < questionList.size() - 1) {
            questNum++;
            playAnim(question, 0, 0);
            playAnim(option1, 0, 1);
            playAnim(option2, 0, 2);
            playAnim(option3, 0, 3);
            playAnim(option4, 0, 4);
            playAnim(option5, 0, 5);
            qCount.setText(String.valueOf(questNum + 1) + "/" + String.valueOf(questionList.size()));
        } else {
            //Display score- Last question
            Intent intent = new Intent(SecondActivity.this, QuizResults.class);
            intent.putExtra("quizScore", quizResults);
            startActivity(intent);
            SecondActivity.this.finish();
        }
    }

    /**
     * Actual animation of the buttons and questions transitions
     *
     * @param view    - Activity page
     * @param value   - If the animations should play or not
     * @param viewNum - The question number
     */
    private void playAnim(final View view, final int value, final int viewNum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0) {
                    switch (viewNum) {
                        case 0:
                            ((TextView) view).setText(questionList.get(questNum).getQuestion());
                            break;
                        case 1:
                            ((Button) view).setText(questionList.get(questNum).getOptionA());
                            break;
                        case 2:
                            ((Button) view).setText(questionList.get(questNum).getOptionB());
                            break;
                        case 3:
                            ((Button) view).setText(questionList.get(questNum).getOptionC());
                            break;
                        case 4:
                            ((Button) view).setText(questionList.get(questNum).getOptionD());
                            break;
                        case 5:
                            ((Button) view).setText(questionList.get(questNum).getOptionE());
                            break;
                    }
                    playAnim(view, 1, viewNum);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }
}