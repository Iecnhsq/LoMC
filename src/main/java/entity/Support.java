package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cg_support")
public class Support implements Serializable {

    @Id
    private int id;

    @NotEmpty(message = "Please, enter your problem!")
    @Size(min = 10, message = "Should be more than 10 characters")
    private String problem;

    @NotEmpty(message = "Please, enter your subject!")
    @Size(min = 10, message = "Should be more than 10 characters")
    private String subject;

    @NotEmpty(message = "Please, enter your email!")
    @Size(min = 5, message = "Should be more than 5 characters")
    private String email;

    @NotEmpty(message = "Please, enter your message!")
    @Size(min = 25, message = "Should be more than 25 characters")
    private String message;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Support() {
    }

    public Support(int id, String problem, String subject, String email, String message, Date date) {
        this.id = id;
        this.problem = problem;
        this.subject = subject;
        this.email = email;
        this.message = message;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.problem);
        hash = 71 * hash + Objects.hashCode(this.subject);
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.message);
        hash = 71 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Support other = (Support) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.problem, other.problem)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

}
