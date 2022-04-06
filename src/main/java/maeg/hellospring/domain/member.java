package maeg.hellospring.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class member {
    // Identity : DB가 자동으로 값을 생성해주는 전략
    // 현재 전략: AUTO => h2 데이터베이스 이므로 SEQUENCE가 채택된다.
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    // DB에 칼럼명이 다를 경우 아래와 같이 지정해준다.
    //@Column(name="username")
    private String name;

    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
