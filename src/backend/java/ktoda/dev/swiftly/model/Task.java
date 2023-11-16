package ktoda.dev.swiftly.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 500)
    private String description;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdOn;
    @Temporal(TemporalType.DATE)
    private Date updatedOn;
    private Timestamp trackingDateStart;
    private long timeWorkedOn; // Reserved for feature uses
    @ManyToOne
    @JoinColumn(
            name = "status_id",
            foreignKey = @ForeignKey(name = "fk_task_status_id")
    )
    private Status status;
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<Tag> tags = new HashSet<>();

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Task task)) return false;
        return getId() == task.getId() &&
                Objects.equals(getTitle(), task.getTitle()) &&
                Objects.equals(getCreatedOn(), task.getCreatedOn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getCreatedOn());
    }
}
