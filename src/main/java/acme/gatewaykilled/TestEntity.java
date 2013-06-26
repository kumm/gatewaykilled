/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme.gatewaykilled;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author kumm
 */
@Entity
@Table(name = "TESTTABLE")
public class TestEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(unique = true, name = "NAME")
    @Basic
    private String name;

    public TestEntity() {
    }

    public TestEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
