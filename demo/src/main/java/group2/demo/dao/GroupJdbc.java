package group2.demo.dao;

import group2.demo.model.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GroupJdbc {


    private final JdbcTemplate jdbcTemplate;

    public GroupJdbc(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public Group get(int id){
        return jdbcTemplate.queryForObject(sql: "SELECT * FROM study_group WHERE id = ?", this::mapStudy_group, id);
    }

    private Group mapStudy_group(ResultSet rs, int i) throws SQLException{
        Group group= new Group(
                rs.getInt( columnLabel: "id"),
        rs.getString(columnLabel: "name")
        );
        return group;
    }

    public Group show_all(){
        return JdbcTemplate.queryForObject(sql: "SELECT * FROM study_group");
    }

    public Group create(int id, String name){
        return JdbcTemplate.queryForObject(sql: "INSERT INTO study_group VALUES(?,?)", Group.class, id, Group.class, name);
    }

    public Group delete(int id){
        return jdbcTemplate.queryForObject(sql: "DELETE FROM study_group WHERE id= ?", Group.class, id);
    }
    public Group modify( String name, int id){
        return jdbcTemplate.queryForObject(sql: "UPDATE study_group SET name=? WHERE id= ?", Group.class, name, Group.class, id);
    }

}
