package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Person extends Model {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

    public String name;

    public static Finder<Long,Person> find = new Finder<Long, Person>(Person.class);
}
