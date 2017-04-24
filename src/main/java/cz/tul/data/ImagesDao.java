package cz.tul.data;

/**
 * Created by Martin on 03.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class ImagesDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Image image) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", image.getId());
        params.addValue("url", image.getUrl());
        params.addValue("name", image.getName());
        params.addValue("dateCreate", image.getDateCreate());
        params.addValue("dateEdit", image.getDateEdit());
        params.addValue("likes", image.getLikes());
        params.addValue("dislikes", image.getDislikes());
        params.addValue("idUser", image.getIdUser());

        return jdbc.update("insert into image (id, url, name, dateCreate, dateEdit, likes, dislikes, idUser) values (NULL, :url, :name, :dateCreate, :dateEdit, :likes, :dislikes, :idUser)", params) == 1;
    }

    public List<Image> getAllImages() {
        return jdbc.query("select * from image", BeanPropertyRowMapper.newInstance(Image.class));
    }

   /* public List<Image> getImageName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        return jdbc.query("select * from image WHERE name = :name", params, BeanPropertyRowMapper.newInstance(Image.class));
    }*/

   public Image getImage(int id) {
       MapSqlParameterSource params = new MapSqlParameterSource();
       params.addValue("id", id);
       return jdbc.queryForObject("select * from image where id=:id", params, BeanPropertyRowMapper.newInstance(Image.class));
   }

    public boolean update(Image image) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                image);

        return jdbc.update("update image set url=:url and name=:name and dateCreate=:dateCreate" +
                " and dateEdit=:dateEdit and like=:likes and dislikes=:dislikes" +
                "and idUser:=idUser" +
                " where id=:id", params) == 1;
    }

    public boolean exists(String name) {
        return jdbc.queryForObject("select count(*) from image where name=:name",
                new MapSqlParameterSource("name", name), Integer.class) > 0;
    }

    /*public boolean lajk(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbc.update("UPDATE image SET likes = likes + 1 WHERE id = " + id, param) == 1;
    }

    public boolean dislajk(int id) {
        MapSqlParameterSource par = new MapSqlParameterSource();
        return jdbc.update("UPDATE image SET dislikes = dislikes + 1 WHERE id = " + id, par) == 1;
    }

    public int getLajks(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

         return jdbc.queryForObject("select likes from image where id = :id", params, Integer.class);
    }

    public int getDislajks(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select dislikes from image where id = :id", params, Integer.class);
    }


    public boolean editName(int id, String newName) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("id", id);
        params.addValue("newName", newName);
        params.addValue("datum", sdf.format(new Date()));
        return jdbc.update("UPDATE image SET name = :newName, dateEdit = :datum " +
                "WHERE id = :id", params) == 1;
    }

    public boolean editUrl(int id, String newUrl) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("id", id);
        params.addValue("newUrl", newUrl);
        params.addValue("datum", sdf.format(new Date()));
        return jdbc.update("UPDATE image SET url = :newUrl, dateEdit = :datum " +
                "WHERE id = :id", params) == 1;
    }

    public String getName(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select name from image where id = :id", params, String.class);
    }

    public String getUrl(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select url from image where id = :id", params, String.class);
    }

    public String getDateEdit(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select dateEdit from image where id = :id", params, String.class);
    }*/

    public void deleteImages() {
        jdbc.getJdbcOperations().execute("DELETE FROM image");
    }
}
