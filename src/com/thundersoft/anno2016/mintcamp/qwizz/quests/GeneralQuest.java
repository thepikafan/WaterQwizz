package com.thundersoft.anno2016.mintcamp.qwizz.quests;

/**
 * The <code>GeneralQuest</code> class represents what is a question in a quiz. It is held abstract due to the
 * fact that there are incredibly many possible types of Questions, only to mention the Multiple-Choice
 * and the estimation.
 *
 * @author Anton Schlömer
 * @version 1.0 - 11.07.2016.
 * @see MCQuest
 */
public abstract class GeneralQuest {

    protected boolean mCorrect;
    protected boolean mAnswered;
    protected Object mUserAnswer;
    protected String mDesc;

    /**
     * The method handles the request of the user to answer the question.
     *
     * @param answer:Object The answer our user is giving. It is an object to be casted/evaluated in the function.
     * @return If the answer was correctly given (can be done later with <code>isAnswerCorrect()</code>)
     * @throws InvalidAnswerTypeException Is thrown if the answer does not match the expected type
     * @throws InvalidArgumentException Thrown if the answer, though of the correct type, cannot be evaluated
     */
    public abstract boolean answer(Object answer) throws InvalidAnswerTypeException, InvalidArgumentException;

    public Object getUserAnswer(){
        return mUserAnswer;
    }
    public boolean isAnswerCorrect(){
        return mCorrect;
    }
    public boolean hasUserAnswered(){
        return mAnswered;
    }
    public String getDescription() {
        return mDesc;
    }

    public void retry(){
        mAnswered = false;
        mCorrect = false;
        mUserAnswer = null;
    }
}