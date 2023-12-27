package com.example.web.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.example.web.model.Answer;
@Mapper
public interface AnswerDao {

    String TABLE_NAME = "answer";
    String INSERT_FIELDS = "content,useful,created_date,code,id_user,id_topic";
    String SELECT_FIELDS = "id,content,useful,created_date,code,id_user,id_topic";

    int addAnswer(Answer answer);

    void setUsefulForAnswer(@Param("bool") Boolean bool, @Param("id") Long id);

    void deleteAnswerById(@Param("id") Long id);

    void deleteAnswerByTopic_id(@Param("topic_id") Long topic_id);

    Long countAnswersByUser_Id(@Param("IdUser") Long IdUser);

    Long countAnswersByUser_IdAndUseful(@Param("IdUser") Long IdUser, @Param("useful") boolean useful);

    Long countAnswersByTopic_Id(@Param("idTopic") Long idTopic);

    List<Answer> findAnswerByUser_IdOrderByCreatedDateDesc(@Param("id") Long id);

    List<Answer> findAnswerByUser_IdAndUsefulOrderByCreatedDateDesc(@Param("idUser") Long idUser,
                                                                    @Param("useful") boolean useful);

    List<Answer> findAnswerByTopic_Id(@Param("idTopic") Long idTopic);

    Long getIdUserById(@Param("id") Long id);
}
