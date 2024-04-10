import java.util.List;
import java.util.Iterator;

/**
 *  Problem Set 3 - CSIS 225
 * 
 * SurveyResponseIterator class for use with the SurveyResponse class
 * 
 * @author Ira Goldstein
 * @version Spring 2024
 */

public class SurveyResponseIterator implements Iterator<SurveyResponse> {

    // Variables for iterator
    private List<SurveyResponse> responses;
    private int current;

    /**
     * SurveyResponseIterator constructs an iterator object.
     * 
     * @param List<SurveyResponse> responses, list of the survey responses.
     */
    public SurveyResponseIterator(List<SurveyResponse> responses) {
        this.responses = responses;
    }

    /**
     * Iterator hasNext method
     * 
     * @return boolean, returns true we have more responses
     */
    @Override
    public boolean hasNext() {
        return current < responses.size();
    }

    /**
     * Iterator next method
     * 
     * @return the next SurveyResponse
     */
    @Override
    public SurveyResponse next() {
        return responses.get(current++);
    }
}
