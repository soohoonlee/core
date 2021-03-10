package hello.core.order

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class OrderServiceTest {
    private val memberService: MemberService = MemberServiceImpl()
    private val orderService: OrderService = OrderServiceImpl()

    @DisplayName(value = "주문 생성")
    @Test
    fun createOrder() {
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        val order = orderService.createOrder(memberId, "itemA", 10000)
        assertThat(order.discountPrice).isEqualTo(1000)
    }
}