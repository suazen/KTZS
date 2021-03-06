package me.daylight.ktzs.model.dao;

import me.daylight.ktzs.model.entity.Course;
import me.daylight.ktzs.model.entity.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Daylight
 * @date 2019/02/28 15:03
 */
public interface CourseRepository extends JpaRepository<Course,Long> {
    boolean existsCourseById(Long id);

    @Query(value = "select * from course where id in " +
            "(select course_id from course_students where students_id=?1) and semester=?2 order by time",nativeQuery = true)
    List<Course> findCoursesByStudentAndSemester(Long userId, String semester);

    @Query(value = "select * from course where teacher_id=?1 and semester=?2 order by time",nativeQuery = true)
    List<Course> findCoursesByTeacherAndSemester(Long teacherId,String semester);

    List<Course> findCoursesBySemesterAndMajor(String semester, Major major);

    Page<Course> findCoursesBySemester(String semester, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "delete from course_students where course_id=?1 and students_id=?2",nativeQuery = true)
    void deleteStudent(Long courseId,Long studentId);

    List<Course> findCoursesByMajor(Major major);

    List<Course> findAllBySemesterAndTimeLikeOrderByTime(String semester, String weekDay);

    List<Course> findAllBySemesterAndTimeLikeAndMajorOrderByTime(String semester, String weekDay, Major major);
}
