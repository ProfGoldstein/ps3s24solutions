import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Problem Set 3 - CSIS 225
 * 
 * SurveyResponse Class and related methods
 * 
 * @author Ira Goldstein
 * @version Spring 2024
 */

/**
 * The SurveyResponse class represents a survey response from a student.
 * It contains information such as the student ID, course, rating, and comment.
 * 
 * Doing this without getters and setters so making the variables public
 */
public class SurveyResponse {
    public int studentId;
    public String course;
    public int rating;
    public String comment;

    /**
     * Construct a SurveyResponse object using all of the parameters
     *
     * @param studentId the student's ID
     * @param course    the course name
     * @param rating    the student's rating
     * @param comment   the student's comments
     */
    public SurveyResponse(int studentId, String course, int rating, String comment) {
        this.studentId = studentId;
        this.course = course;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Construct a SurveyResponse object if there isn't a comment
     *
     * @param studentId the student's ID
     * @param course    the course name
     * @param rating    the student's rating
     */
    public SurveyResponse(int studentId, String course, int rating) {
        this.studentId = studentId;
        this.course = course;
        this.rating = rating;
        this.comment = ""; // Set the comment to null
    }

    /**
     * Returns all of the SurveyResponse for a specific course
     *
     * @param responseList the list of survey responses
     * @param courseName   a course name that we want to filter on
     * @return a list of SurveyResponse for the just courseName
     */
    public static List<SurveyResponse> courseFeedback(List<SurveyResponse> responseList, String courseName) {
        // initialize the list
        List<SurveyResponse> list = new ArrayList<>();

        // Create iterator
        Iterator<SurveyResponse> iterator = responseList.iterator();

        // Iterate and add matching responses to the list
        while (iterator.hasNext()) {
            SurveyResponse response = iterator.next();
            if (response.course.equalsIgnoreCase(courseName)) {
                list.add(response);
            }
        }
        return list;
    }

    /**
     * Calculates the average rating from a list of survey responses.
     *
     * @param responseList the list containing the survey responses
     * @return the average of the ratings in the responseList
     */
    public static double calculateAverageRating(List<SurveyResponse> responseList) {

        int totalRating = 0;
        SurveyResponseIterator iterator = new SurveyResponseIterator(responseList);
        // Iterate and add to rating
        while (iterator.hasNext()) {
            totalRating += iterator.next().rating;
        }

        // Return the average rating
        // Check the list size to avoid dividing by zero if the list is empty
        if (responseList.size() == 0) {
            return 0.0;
        } else {
            return (double) totalRating / responseList.size();
        }
    }

}
