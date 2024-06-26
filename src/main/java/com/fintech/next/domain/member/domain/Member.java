package com.fintech.next.domain.member.domain;

import com.fintech.next.domain.model.Email;
import com.fintech.next.domain.model.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Embedded //@Embeddable과의 관계를 생각!
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false,
            unique = true, updatable = false, length = 50))
    private Email email;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "last", column = @Column(name = "last_name", nullable = false)),
    })
    private Name name;

    private String password;

    private String salt;

    public Member(Email email, Name name, String password, String salt){
        this.email = email;
        this.name = name;
        this.password = password;
        this.salt = salt;
    }

    /* @Builder 구현 */
    public static class MemberBuilder{
        private Email email;
        private Name name;

        private String password;

        private String salt;

        public MemberBuilder email(Email email){
            this.email = email;
            return this;
        }
        public MemberBuilder name(Name name){
            this.name = name;
            return this;
        }
        public MemberBuilder password(String password){
            this.password = password;
            return this;
        }
        public MemberBuilder salt(String salt){
            this.salt = salt;
            return this;
        }
    public Member build(){return new Member(email,name,password,salt);}
    }
    public static MemberBuilder builder(){return new MemberBuilder();}
    /* @Builder 끝 */
    public void updateProfile(final Name name){
        this.name = name;
    }

}
