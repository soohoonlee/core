package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member

class FixDiscountPolicy : DiscountPolicy {
    private val discountFixAmount: Int = 1000

    override fun discount(member: Member, price: Int): Int {
        return if (member.grade == Grade.VIP) {
            discountFixAmount
        } else {
            0
        }
    }
}