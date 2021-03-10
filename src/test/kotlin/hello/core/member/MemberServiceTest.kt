package hello.core.member

import hello.core.AppConfig
import hello.core.member.Grade.VIP
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MemberServiceTest {
    private lateinit var memberService: MemberService

    @BeforeEach
    internal fun setUp() {
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
    }

    @DisplayName(value = "회원 가입")
    @Test
    internal fun join() {
        //given
        val member = Member(1L, "memberA", VIP)

        //when
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        //then
        assertThat(findMember).isEqualTo(member)
    }
}