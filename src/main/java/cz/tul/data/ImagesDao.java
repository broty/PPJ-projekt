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


public class ImagesDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Image image) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idimage", image.getIdimage());
        params.addValue("url", image.getUrl());
        params.addValue("name", image.getName());
        params.addValue("date_creation", sdf.format(image.getDate_creation()));
        params.addValue("date_edit", sdf.format(image.getDate_edit()));
        params.addValue("likes", image.getLikes());
        params.addValue("dislikes", image.getDislikes());
        params.addValue("user_iduser", image.getUser_iduser());

        return jdbc.update("insert into image (idimage, url, name, date_creation, date_edit, likes, dislikes, user_iduser) values (NULL, :url, :name, :date_creation, :date_edit, :likes, :dislikes, :user_iduser)", params) == 1;
    }

    public List<Image> getAllImages() {
        return jdbc.query("select * from image", BeanPropertyRowMapper.newInstance(Image.class));
    }

    public List<Image> getImageName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        return jdbc.query("select * from image WHERE name = :name", params, BeanPropertyRowMapper.newInstance(Image.class));
    }

    public boolean exists(String name) {
        return jdbc.queryForObject("select count(*) from image where name=:name",
                new MapSqlParameterSource("name", name), Integer.class) > 0;
    }

    public boolean lajk(int idimage) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbc.update("UPDATE image SET likes = likes + 1 WHERE idimage = " + idimage, param) == 1;
    }

    public boolean dislajk(int idimage) {
        MapSqlParameterSource par = new MapSqlParameterSource();
        return jdbc.update("UPDATE image SET dislikes = dislikes + 1 WHERE idimage = " + idimage, par) == 1;
    }

    public int getLajks(int idimage) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idimage", idimage);

         return jdbc.queryForObject("select likes from image where idimage = :idimage", params, Integer.class);
    }

    public int getDislajks(int idimage) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idimage", idimage);

        return jdbc.queryForObject("select dislikes from image where idimage = :idimage", params, Integer.class);
    }


    public boolean editName(int idimage, String newName) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idimage", idimage);
        params.addValue("newName", newName);
        params.addValue("datum", sdf.format(new Date()));
        return jdbc.update("UPDATE image SET name = :newName, date_edit = :datum " +
                "WHERE idimage = :idimage", params) == 1;
    }

    public boolean editUrl(int idimage, String newUrl) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idimage", idimage);
        params.addValue("newUrl", newUrl);
        params.addValue("datum", sdf.format(new Date()));
        return jdbc.update("UPDATE image SET url = :newUrl, date_edit = :datum " +
                "WHERE idimage = :idimage", params) == 1;
    }

    public String getName(int idimage) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idimage", idimage);

        return jdbc.queryForObject("select name from image where idimage = :idimage", params, String.class);
    }

    public String getUrl(int idimage) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idimage", idimage);

        return jdbc.queryForObject("select url from image where idimage = :idimage", params, String.class);
    }

    public String getDateEdit(int idimage) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idimage", idimage);

        return jdbc.queryForObject("select date_edit from image where idimage = :idimage", params, String.class);
    }

    public void deleteImages() {
        jdbc.getJdbcOperations().execute("DELETE FROM image");
    }
}
