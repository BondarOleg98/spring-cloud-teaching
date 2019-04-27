package com.olegb.client.Model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coach")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "belt", nullable = false)
    private String belt;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Student> students;

    public  List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public Coach(){

    }
    public Coach(String name, String surname, String belt){

        this.name = name;
        this.surname = surname;
        this.belt = belt;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public String getBelt() {
        return belt;
    }

    public String getSurname(){return surname;}

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){this.id = id;}

}
