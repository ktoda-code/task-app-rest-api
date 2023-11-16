package ktoda.dev.swiftly.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private long id;
    @Column(length = 50, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(
            name = "task_id",
            foreignKey = @ForeignKey(name = "fk_tag_task_id")
    )
    @JsonBackReference
    @ToString.Exclude
    private Task task;

    public Tag(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Tag tag)) return false;
        return getId() == tag.getId() && Objects.equals(getName(), tag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
