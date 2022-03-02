package maeg.hellospring.domain;

import javax.persistence.*;

@Entity
public class member {
    // Identity : DB가 자동으로 값을 생성해주는 전략
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // DB에 칼럼명이 다를 경우 아래와 같이 지정해준다.
    //@Column(name="username")
    private String name;

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
