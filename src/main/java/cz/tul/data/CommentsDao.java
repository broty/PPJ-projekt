package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public class CommentsDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private KeyHolder keyHolder = new GeneratedKeyHolder();

    public int getLastKey() {
        return (int) keyHolder.getKey();
    }

    @Transactional
    public void create(Comment comment) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("id", comment.getId());
        params.addValue("text", comment.getText());
        params.addValue("dateCreate", sdf.format(comment.getDateCreate()));
        params.addValue("dateEdit", sdf.format(comment.getDateEdit()));
        params.addValue("likes", comment.getLikes());
        params.addValue("dislikes", comment.getDislikes());
        params.addValue("idImage", comment.getIdImage());
        params.addValue("idUser", comment.getIdUser());

        jdbc.update("insert into comment (id, text, dateCreate, dateEdit, likes, dislikes, idImage, idUser) " +
                "values (NULL, :text, :dateCreate, :dateEdit, :likes, :dislikes, :idImage, :idUser)", params, this.keyHolder);
    }

    public List<Comment> getAllComments() {
        return jdbc.query("select * from comment", BeanPropertyRowMapper.newInstance(Comment.class));
    }

    public Comment getComment(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.queryForObject("select * from comment where id=:id", params, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    public boolean update(Comment comment) {
        comment.setDateEdit(new Date());
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                comment);

        return jdbc.update("update comment set text=:text, dateCreate=:dateCreate, dateEdit=:dateEdit" +
                ", likes=:likes, dislikes=:dislikes, idImage=:idImage, idUser=:idUser" +
                " where id=:id", params) == 1;
    }

    public void deleteComments() {
        jdbc.getJdbcOperations().execute("DELETE FROM comment");
    }
}