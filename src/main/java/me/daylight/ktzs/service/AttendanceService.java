package me.daylight.ktzs.service;

import me.daylight.ktzs.model.entity.Attendance;
import me.daylight.ktzs.model.entity.Course;
import me.daylight.ktzs.model.entity.User;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author daylight
 * @date 2019/03/01 00:08
 */
public interface AttendanceService {
    Attendance save(Attendance attendance);

    void saveAll(Collection<Attendance> attendances);

    Page<Attendance> findByCourse(Course course,int page,int limit);

    Page<Attendance> findByStudentPageable(User student,int page,int limit);

    List<Attendance> findByStudent(User student);

    Page<Attendance> findAllPageable(int page,int limit);

    Page<Attendance> findAllByMajorPageable(Long userId,int page,int limit);

    int countByCourseAndStateAndUniqueId(Long courseId, int state, String uniqueId);

    Attendance getLatestByCourseId(Long id);

    List<Map<String,Object>> getRecordOfTeacher(Long userId);

    List<Attendance> getRecordsByUniqueId(String uniqueId);

    int getAbsentCountOfToday();

    int getAbsentCountOfTodayByCourseIn(List<Course> courses);

    List<Map<String,Object>> getRecordOfToday();

    List<Map<String,Object>> getRecordOfTodayByMajorId(Long majorId);
}
