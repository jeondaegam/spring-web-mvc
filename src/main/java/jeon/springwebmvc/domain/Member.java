package jeon.springwebmvc.domain;

public class Member {
    Long id; // 시퀀스
    String name; // 이름: 사용자가 직접 입력

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
