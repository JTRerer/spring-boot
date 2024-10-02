package com.yuu.cruddemo;

import com.yuu.cruddemo.dao.AppDAO;
import com.yuu.cruddemo.entity.Course;
import com.yuu.cruddemo.entity.Instructor;
import com.yuu.cruddemo.entity.InstructorDetail;
import com.yuu.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
			//createCourseAndReviews(appDAO);
			//retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Deleted!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		//get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		//print the course
		System.out.println(tempCourse);

		//print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		//create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		//add some reviews
		tempCourse.addReview(new Review("Great course .... loved it!"));
		tempCourse.addReview(new Review("Cool course, job well done"));
		tempCourse.addReview(new Review("What a dumb course..."));

		//save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 11;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Deleted!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 12;

		//find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		//update the course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("C#");

		appDAO.update(tempCourse);

		System.out.println("Updated!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByID(theId);

		//update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("Tester");

		appDAO.update(tempInstructor);

		System.out.println("Updated!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 2;
		//find instructor
		System.out.println("Finding instructor id: " +  theId );

		Instructor tempInstructor = appDAO.findInstructorByID(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		//find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		//associate the object
		//Option 1
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses " + tempInstructor.getCourses());

		//Option 2 - No need setCourses
		//System.out.println("the associated courses " + courses);
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " +  theId );

		Instructor tempInstructor = appDAO.findInstructorByID(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Logan", "Paul", "logan@gmail.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.youtube.com/loganpaul",
						"Frank");

		//create some courses
		Course tempCourse1 = new Course("Youtuber");
		Course tempCourse2 = new Course("Python");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save the instructor
		//NOTE: this will ALSO save the courses
		//because of CascadeType.PERSIST
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Deleted!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		//get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail =  appDAO.findInstructorDetailByID(theId);

		//print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		//print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);

		appDAO.deleteInstructorByID(theId);

		System.out.println("Deleted!");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding instructor id: " +  theId );

		Instructor tempInstructor = appDAO.findInstructorByID(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		/*
		// create the instructor
		Instructor tempInstructor = new Instructor("Chad", "Darby", "chad@gmail.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!");
		*/

		// create the instructor
		Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@gmail.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		//NOTE: this will ALSO save the details object
		//because of CascadeType.ALL
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}
