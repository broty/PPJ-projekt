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


public class ImagesDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private KeyHolder keyHolder = new GeneratedKeyHolder();

    public int getLastKey() {
        return (int) keyHolder.getKey();
    }

    @Transactional
    public void create(Image image) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", image.getId());
        params.addValue("url", image.getUrl());
        params.addValue("name", image.getName());
        params.addValue("dateCreate", image.getDateCreate());
        params.addValue("dateEdit", image.getDateEdit());
        params.addValue("likes", image.getLikes());
        params.addValue("dislikes", image.getDislikes());
        params.addValue("idUser", image.getIdUser());

        jdbc.update("insert into image (id, url, name, dateCreate, dateEdit, likes, dislikes, idUser) values (NULL, " +
                ":url, :name, :dateCreate, :dateEdit, :likes, :dislikes, :idUser)", params, this.keyHolder);
    }

    public List<Image> getAllImages() {
        return jdbc.query("select * from image", BeanPropertyRowMapper.newInstance(Image.class));
    }


   public Image getImage(int id) {
       MapSqlParameterSource params = new MapSqlParameterSource();
       params.addValue("id", id);
       return jdbc.queryForObject("select * from image where id=:id", params, BeanPropertyRowMapper.newInstance(Image.class));
   }

    public boolean update(Image image) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                image);

        return jdbc.update("update image set url=:url, name=:name, dateCreate=:dateCreate" +
                ", dateEdit=:dateEdit, likes=:likes, dislikes=:dislikes " +
                ", idUser=:idUser" +
                " where id=:id", params) == 1;
    }

    public void update(Image image, boolean updateDateEdit) {
        if (updateDateEdit) {
            image.setDateEdit(new Date());
        }
        update(image);
    }

    public void deleteImages() {
        jdbc.getJdbcOperations().execute("DELETE FROM image");
    }
}
