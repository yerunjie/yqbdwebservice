package com.yqbd.mapper;

import com.yqbd.model.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TaskMapper {
    @Delete({
        "delete from task",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taskId);

    @Insert({
        "insert into task (task_id, company_id, ",
        "user_id, classification, ",
        "task_title, task_status, ",
        "pay, publish_time, ",
        "deadline, start_time, ",
        "complete_time, sign_up_people_number, ",
        "current_people_number, max_people_number, ",
        "simple_drawing_address, group_id, ",
        "province, city, ",
        "district, task_address, ",
        "task_description)",
        "values (#{taskId,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER}, #{classification,jdbcType=VARCHAR}, ",
        "#{taskTitle,jdbcType=VARCHAR}, #{taskStatus,jdbcType=INTEGER}, ",
        "#{pay,jdbcType=DOUBLE}, #{publishTime,jdbcType=TIMESTAMP}, ",
        "#{deadline,jdbcType=TIMESTAMP}, #{startTime,jdbcType=TIMESTAMP}, ",
        "#{completeTime,jdbcType=TIMESTAMP}, #{signUpPeopleNumber,jdbcType=INTEGER}, ",
        "#{currentPeopleNumber,jdbcType=INTEGER}, #{maxPeopleNumber,jdbcType=INTEGER}, ",
        "#{simpleDrawingAddress,jdbcType=VARCHAR}, #{groupId,jdbcType=INTEGER}, ",
        "#{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
        "#{district,jdbcType=VARCHAR}, #{taskAddress,jdbcType=VARCHAR}, ",
        "#{taskDescription,jdbcType=LONGVARCHAR})"
    })
    int insert(Task record);

    int insertSelective(Task record);

    @Select({
        "select",
        "task_id, company_id, user_id, classification, task_title, task_status, pay, ",
        "publish_time, deadline, start_time, complete_time, sign_up_people_number, current_people_number, ",
        "max_people_number, simple_drawing_address, group_id, province, city, district, ",
        "task_address, task_description",
        "from task",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    Task selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    @Update({
        "update task",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "classification = #{classification,jdbcType=VARCHAR},",
          "task_title = #{taskTitle,jdbcType=VARCHAR},",
          "task_status = #{taskStatus,jdbcType=INTEGER},",
          "pay = #{pay,jdbcType=DOUBLE},",
          "publish_time = #{publishTime,jdbcType=TIMESTAMP},",
          "deadline = #{deadline,jdbcType=TIMESTAMP},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "complete_time = #{completeTime,jdbcType=TIMESTAMP},",
          "sign_up_people_number = #{signUpPeopleNumber,jdbcType=INTEGER},",
          "current_people_number = #{currentPeopleNumber,jdbcType=INTEGER},",
          "max_people_number = #{maxPeopleNumber,jdbcType=INTEGER},",
          "simple_drawing_address = #{simpleDrawingAddress,jdbcType=VARCHAR},",
          "group_id = #{groupId,jdbcType=INTEGER},",
          "province = #{province,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "district = #{district,jdbcType=VARCHAR},",
          "task_address = #{taskAddress,jdbcType=VARCHAR},",
          "task_description = #{taskDescription,jdbcType=LONGVARCHAR}",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Task record);

    @Update({
        "update task",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "classification = #{classification,jdbcType=VARCHAR},",
          "task_title = #{taskTitle,jdbcType=VARCHAR},",
          "task_status = #{taskStatus,jdbcType=INTEGER},",
          "pay = #{pay,jdbcType=DOUBLE},",
          "publish_time = #{publishTime,jdbcType=TIMESTAMP},",
          "deadline = #{deadline,jdbcType=TIMESTAMP},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "complete_time = #{completeTime,jdbcType=TIMESTAMP},",
          "sign_up_people_number = #{signUpPeopleNumber,jdbcType=INTEGER},",
          "current_people_number = #{currentPeopleNumber,jdbcType=INTEGER},",
          "max_people_number = #{maxPeopleNumber,jdbcType=INTEGER},",
          "simple_drawing_address = #{simpleDrawingAddress,jdbcType=VARCHAR},",
          "group_id = #{groupId,jdbcType=INTEGER},",
          "province = #{province,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "district = #{district,jdbcType=VARCHAR},",
          "task_address = #{taskAddress,jdbcType=VARCHAR}",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Task record);


    @Select({
           " select * from task where user_id=#{userId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<Task> getPublishedTasksById(Integer userId);

    @Select({
            " select * from task where task_id in (SELECT task_id from user_take WHERE user_id= #{userId,jdbcType=INTEGER})"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<Task> getTasksById(Integer userId);


    @Select({
            "  SELECT * FROM task"
    })

    @ResultMap("ResultMapWithBLOBs")
    List<Task> selectAllTasks();

    @ResultMap("ResultMapWithBLOBs")
    List<Task>getSearchTasks(List<Integer>list);

}