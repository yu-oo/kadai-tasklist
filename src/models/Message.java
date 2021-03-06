package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity

@NamedQueries({
    @NamedQuery(
            name = "getMessage",
            query = "SELECT m FROM Message AS m ORDER BY m.id DESC"
            ),//Hibernate用の言語定義の為 ↑はEntityクラス(DTO)を参照する形（矢印の先は、クラス名を記入）
    @NamedQuery(
            name = "getMessageCount",
            query = "SELECT COUNT(m) FROM Message AS m"
            )
})

@Table(name = "tasks")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content" , length = 255 , nullable = false)
    private String content;

    @Column(name = "created_at" , nullable = false)
    private Timestamp created_at;

    @Column(name = "updatad_at" , nullable = false)
    private Timestamp updatad_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdatad_at() {
        return updatad_at;
    }

    public void setUpdatad_at(Timestamp updatad_at) {
        this.updatad_at = updatad_at;
    }




}
