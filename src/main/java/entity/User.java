package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cg_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Please, enter your login!")
    @Size(min = 3, max = 15, message = "Your login must between 3 and 15 characters")
    private String login;

    @NotEmpty(message = "Please, enter your password!")
    @Size(min = 4, max = 15, message = "Your password must between 4 and 15 characters")
    private String pass;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int lvl;
    private int points;
    private String cards;
    private String classs;
    private int money;
    private String city;
    private String phone;
    private String email;
    private char prem;

    public User() {
    }

    public User(int id, String login, String pass, Date date, int lvl, int points, String cards, String classs, int money, String city, String phone, String email, char prem) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.date = date;
        this.lvl = lvl;
        this.points = points;
        this.cards = cards;
        this.classs = classs;
        this.money = money;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.prem = prem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getPrem() {
        return prem;
    }

    public void setPrem(char prem) {
        this.prem = prem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.pass);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + this.lvl;
        hash = 97 * hash + this.points;
        hash = 97 * hash + Objects.hashCode(this.cards);
        hash = 97 * hash + Objects.hashCode(this.classs);
        hash = 97 * hash + this.money;
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.phone);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + this.prem;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.lvl != other.lvl) {
            return false;
        }
        if (this.points != other.points) {
            return false;
        }
        if (this.money != other.money) {
            return false;
        }
        if (this.prem != other.prem) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.cards, other.cards)) {
            return false;
        }
        if (!Objects.equals(this.classs, other.classs)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

}
