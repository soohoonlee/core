package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class MemberApp

fun main() {
    val applicationContext: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService: MemberService = applicationContext.getBean("memberService", MemberService::class.java)
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val findMember: Member = memberService.findMember(1L)
    println("new member = ${member.name}")
    println("find member = ${findMember.name}")
}