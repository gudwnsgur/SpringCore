package study.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.spring.core.member.Grade;
import study.spring.core.member.Member;
import study.spring.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        /**
         * 기존 : MemberServiceImpl 을 직접 생성했다.
         * 현재 : AppConfig에서 결정된 memberService를 제공
         */
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // @Bean을 관리
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}