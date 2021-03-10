package hello.core.order

import hello.core.AppConfig
import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class OrderServiceTest {
    private lateinit var memberService: MemberService
    private lateinit var orderService: OrderService

    @BeforeEach
    internal fun setUp() {
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
        orderService = appConfig.orderService()
    }

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