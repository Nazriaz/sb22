package org.nazriaz.sb.entity;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import javax.persistence.CascadeType;
        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.OneToMany;
        import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ValCursDate {
    @Id
    String date;
    String name;
    @OneToMany(mappedBy = "valCursDate",cascade= CascadeType.ALL)
    List<Curs> cursList;

    public ValCursDate(String date, String name) {
        this.date = date;
        this.name = name;
    }
}
