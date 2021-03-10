package hello.core.order

import hello.core.discount.FixDiscountPolicy
import hello.core.member.MemberRepository
import hello.core.member.MemoryMemberRepository

class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    private val discountPolicy: FixDiscountPolicy
) : OrderService {
    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member, itemPrice)
        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}