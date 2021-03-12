package hello.core.beanfind

import hello.core.AppConfig
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextBasicFindTest {
    private val ac: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    @DisplayName(value = "빈 이름으로 조회")
    @Test
    fun findBeanByName() {
        val memberService = ac.getBean("memberService", MemberService::class.java)
        assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @DisplayName(value = "이름 없이 타입으로만 조회")
    @Test
    fun findBeanByType() {
        val memberService = ac.getBean(MemberService::class.java)
        assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @DisplayName(value = "구체 타입으로 조회")
    @Test
    fun findBeanByName2() {
        val memberService = ac.getBean("memberService", MemberServiceImpl::class.java)
        assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @DisplayName(value = "빈 이름으로 조회X")
    @Test
    fun findBeanByNameX() {
        assertThatThrownBy { ac.getBean("XXXXX", MemberService::class.java) }
            .isInstanceOf(NoSuchBeanDefinitionException::class.java)
            .hasMessageContaining("No bean named 'XXXXX' available")
    }
}