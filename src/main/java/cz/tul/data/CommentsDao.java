package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public class CommentsDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Comment comment) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idcomment", comment.getIdcomment());
        params.addValue("text", comment.getText());
        params.addValue("date_creation", sdf.format(comment.getDate_creation()));
        params.addValue("date_edit", sdf.format(comment.getDate_edit()));
        params.addValue("likes", comment.getLikes());
        params.addValue("dislikes", comment.getDislikes());
        params.addValue("image_idimage", comment.getImage_idimage());
        params.addValue("user_iduser", comment.getUser_iduser());

        return jdbc.update("insert into comment (idcomment, text, date_creation, date_edit, likes, dislikes, image_idimage, user_iduser) values (NULL, :text, :date_creation, :date_edit, :likes, :dislikes, :image_idimage, :user_iduser)", params) == 1;
    }

    public List<Comment> getAllComments() {
        return jdbc.query("select * from comment", BeanPropertyRowMapper.newInstance(Comment.class));
    }

    public boolean lajk(int idcomment) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbc.update("UPDATE `comment` SET likes = likes + 1 WHERE `idcomment` = " + idcomment, param) == 1;
    }

    public boolean dislajk(int idcomment) {
        MapSqlParameterSource par = new MapSqlParameterSource();
        return jdbc.update("UPDATE `comment` SET dislikes = dislikes + 1 WHERE `idcomment` = " + idcomment, par) == 1;
    }

    public int getLajks(int idcomment) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idcomment", idcomment);

        return jdbc.queryForObject("select likes from comment where idcomment = :idcomment", params, Integer.class);
    }

    public int getDislajks(int idcomment) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idcomment", idcomment);

        return jdbc.queryForObject("select dislikes from comment where idcomment = :idcomment", params, Integer.class);
    }

    public String getText(int idcomment) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idcomment", idcomment);

        return jdbc.queryForObject("select text from comment where idcomment = :idcomment", params, String.class);
    }

    public boolean editComment(int idcomment, String newText) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idcomment", idcomment);
        params.addValue("newKomentar", newText);
        params.addValue("datum", sdf.format(new Date()));
        return jdbc.update("UPDATE `comment` SET text = :newKomentar, datum_aktualizace = :datum " +
                "WHERE `idcomment` = :idcomment", params) == 1;
    }

    public String getDateEdit(int idcomment) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idcomment", idcomment);

        return jdbc.queryForObject("select date_edit from comment where idcomment = :idcomment", params, String.class);
    }

    public void deleteComments() {
        jdbc.getJdbcOperations().execute("DELETE FROM comment");
    }
}