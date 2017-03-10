package org.parma.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * A Tag.
 */
@Entity
@Table(name = "tag")
@Data
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Entry> entries = new ArrayList<Entry>();

    public Tag addEntry(Entry entry) {
        this.entries.add(entry);
        entry.getTags().add(this);
        return this;
    }

    public Tag removeEntry(Entry entry) {
        this.entries.remove(entry);
        entry.getTags().remove(this);
        return this;
    }
}
