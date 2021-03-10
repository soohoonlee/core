package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RateDiscountPolicyTest {
    private val discountPolicy: RateDiscountPolicy = RateDiscountPolicy()

    @DisplayName(value = "VIP는 10%할인이 적용되어야 한다.")
    @Test
    fun vipAllowDiscount() {
        //given
        val member = Member(1L, "memberVIP", Grade.VIP)
        //when
        val discount = discountPolicy.discount(member, 10000)
        //then
        assertThat(discount).isEqualTo(1000)
    }

    @DisplayName(value = "VIP가 아니면 할인이 적용되지 않아야 한다.")
    @Test
    fun basicNotAllowDiscount() {
        //given
        val member = Member(2L, "memberBASIC", Grade.BASIC)
        //when
        val discount = discountPolicy.discount(member, 10000)
        //then
        assertThat(discount).isEqualTo(0)
    }
}