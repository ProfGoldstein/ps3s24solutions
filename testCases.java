
/* 
* Test cases for PS3 SurveyResponse
* Spring 2024
*
*/


// Create a list of testResponses
List<SurveyResponse> testResponses = new ArrayList<>();
testResponses.add(new SurveyResponse(123, "MATH", 1, "Boring"));
testResponses.add(new SurveyResponse(456, "MATH", 5, "Good"));
testResponses.add(new SurveyResponse(789, "MATH", 4, "Fun"));
testResponses.add(new SurveyResponse(246, "ART",  2, "Bad"));
testResponses.add(new SurveyResponse(468, "CSIS", 5, "Amazing Class!"));
testResponses.add(new SurveyResponse(902, "CSIS", 3, "VERY Hard!"));
testResponses.add(new SurveyResponse(111, "PHY",  4, "Good class!"));
testResponses.add(new SurveyResponse(789, "PHY",  2, "OK"));
testResponses.add(new SurveyResponse(123, "ART",  5, "Love the Prof"));
testResponses.add(new SurveyResponse(456, "CSIS", 4, "Homework was hard"));

// Testing SurveyResponse myIterator
System.out.println("Testing SurveyResponse myIterator:");
System.out.println("Expecting ten lines of output");
SurveyResponseIterator myIterator = new SurveyResponseIterator(testResponses);

while (myIterator.hasNext()) {
	SurveyResponse response = myIterator.next();
	System.out.println("Course: " + response.course + " Student ID: " + response.studentId + ", Rating: " + response.rating + ", Comment: " + response.comment);
}



// Testing calculateAverageRating
double averageRating = SurveyResponse.calculateAverageRating(testResponses);
System.out.println();
System.out.println("Expecting 3.5 Average Rating for all courses: " + averageRating);	


// Testing courseFeedback
System.out.println();
System.out.println("Testing courseFeedback() for course ART:");
System.out.println("Expecting two lines. ART 246 2 Bad  and  ART 123 10 Love the Prof");

List<SurveyResponse> artFeedback = SurveyResponse.courseFeedback(testResponses, "ART");
myIterator = new SurveyResponseIterator(artFeedback);
while (myIterator.hasNext()) {
	SurveyResponse response = myIterator.next();
	System.out.println("Course: " + response.course + " Student ID: " + response.studentId + ", Rating: " + response.rating + ", Comment: " + response.comment);
}	


// Testing courseFeedback for non-existant course
System.out.println();
System.out.println("Testing courseFeedback() non-existant course:");
System.out.println("Expecting no output");

List<SurveyResponse> xxxFeedback = SurveyResponse.courseFeedback(testResponses, "XXX");
myIterator = new SurveyResponseIterator(xxxFeedback);
System.out.println();
while (myIterator.hasNext()) {
	SurveyResponse response = myIterator.next();
	System.out.println("Course: " + response.course + " Student ID: " + response.studentId + ", Rating: " + response.rating + ", Comment: " + response.comment);
	
}


// Create an empty list to test with
List<SurveyResponse> testResponses2 = new ArrayList<>();

// Testing SurveyResponse myIterator
System.out.println("Testing SurveyResponse myIterator:");
System.out.println("Expecting no output");
myIterator = new SurveyResponseIterator(testResponses2);

while (myIterator.hasNext()) {
	SurveyResponse response = myIterator.next();
	System.out.println("Course: " + response.course + " Student ID: " + response.studentId + ", Rating: "
			+ response.rating + ", Comment: " + response.comment);
}

// Testing calculateAverageRating
averageRating = SurveyResponse.calculateAverageRating(testResponses2);
System.out.println();
System.out.println("Expecting output of 0.0: " + averageRating);

// Testing courseFeedback
System.out.println();
System.out.println("Testing courseFeedback() for nonexistent course ART:");
System.out.println("Expecting no output");

List<SurveyResponse> nullFeedback = SurveyResponse.courseFeedback(testResponses2, "ART");
myIterator = new SurveyResponseIterator(nullFeedback);
while (myIterator.hasNext()) {
	SurveyResponse response = myIterator.next();
	System.out.println("Course: " + response.course + " Student ID: " + response.studentId + ", Rating: "
			+ response.rating + ", Comment: " + response.comment);
}
