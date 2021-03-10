package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import hello.core.order.OrderService

class OrderApp

fun main() {
    val appConfig = AppConfig()
    val memberService: MemberService = appConfig.memberService()
    val orderService: OrderService = appConfig.orderService()

    val memberId = 1L
    val member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)
    println("order = $order")
}