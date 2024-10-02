package com.yuu.cruddemo.dao;

import com.yuu.cruddemo.entity.Course;
import com.yuu.cruddemo.entity.Instructor;
import com.yuu.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorByID(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorByID(int theId) {

        //retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //get the courses
        List<Course> courses = tempInstructor.getCourses();

        //break association of all courses for instructor
        for (Course tempCourses : courses ){
            tempCourses.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailByID(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        //retrieve the instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //remove the associated object reference
        //break bi-directional link
        //
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        //delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery("from Course WHERE instructor.id= :data", Course.class);
        query.setParameter("data", theId);

        //execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        //create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                          "select i from Instructor i "+
                                            "JOIN FETCH i.courses "+
                                            "JOIN FETCH i.instructorDetail "+
                                            "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        //execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        //retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        //delete the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                   "JOIN FETCH c.reviews " +
                    "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        //execute query
        Course course = query.getSingleResult();
        return course;
    }
}
