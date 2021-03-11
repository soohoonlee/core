package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import hello.core.order.Order
import hello.core.order.OrderService
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class OrderApp

fun main() {
    val applicationContext: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService: MemberService = applicationContext.getBean("memberService", MemberService::class.java)
    val orderService: OrderService = applicationContext.getBean("orderService", OrderService::class.java)

    val memberId = 1L
    val member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order: Order = orderService.createOrder(memberId, "itemA", 20000)
    println("order = $order")
}