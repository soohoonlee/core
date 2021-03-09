package hello.core.member

import hello.core.member.Grade.VIP
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MemberServiceTest {
    private val memberService: MemberService = MemberServiceImpl()

    @DisplayName(value = "회원 가입")
    @Test
    fun join() {
        //given
        val member = Member(1L, "memberA", VIP)

        //when
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        //then
        assertThat(findMember).isEqualTo(member)
    }
}