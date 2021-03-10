package hello.core

import hello.core.member.*
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl

class OrderApp

fun main() {
    val memberService: MemberService = MemberServiceImpl()
    val orderService: OrderService = OrderServiceImpl()

    val memberId = 1L
    val member = Member(memberId, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId, "itemA", 10000)
    println("order = $order")
    println("order.calculatePrice = ${order.calculatePrice()}")
}