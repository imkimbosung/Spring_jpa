package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // Spring과 같이 실행여부
@SpringBootTest // SpringBoot를 띄운 상태에서 테스트 하고싶을때 (autowired할떄 필요)
@Transactional  // Test를 끝나면 자동 rollback하기 위해서
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(value = false) // 기본적인 rollback이 이뤄지기 때문에 insert 쿼리를 보고싶으면 설정 (rollback 안됨)
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long saveId = memberService.join(member);

        // then
        //em.flush(); // DB에 반영(insert 문을 볼 수 있다.) 이건 rollback됨
        assertEquals(member, memberRepository.findOne(saveId));
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        // when
        memberService.join(member1);
        memberService.join(member2); // 예외 발생


        // then
        fail("예외가 발생해야 한다.");
    }
}