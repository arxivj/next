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
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false, unique = true, updatable = false, length = 50))
    private Email email;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "last", column = @Column(name = "last_name", nullable = false)),
    })
    private Name name;

    public Member(Email email, Name name){
        this.email = email;
        this.name = name;
    }

    /* @Builder 구현 */
    public static class MemberBuilder{
        private Email email;
        private Name name;
        public MemberBuilder email(Email email){
            this.email = email;
            return this;
        }
        public MemberBuilder name(Name name){
            this.name = name;
            return this;
        }
    public Member build(){return new Member(email,name);}
    }
    public static MemberBuilder builder(){return new MemberBuilder();}
    /* @Builder 끝 */
    public void updateProfile(final Name name){
        this.name = name;
    }

}
