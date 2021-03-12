package hello.core.beanfind

import hello.core.member.MemberRepository
import hello.core.member.MemoryMemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextSameBeanFindTest {
    private val ac = AnnotationConfigApplicationContext(SameBeanConfig::class.java)

    @DisplayName(value = "타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    @Test
    fun findBeanByTypeDuplicate() {
        assertThatThrownBy { ac.getBean(MemberRepository::class.java) }
            .isInstanceOf(NoUniqueBeanDefinitionException::class.java)
    }

    @DisplayName(value = "타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    @Test
    fun findBeanByName() {
        val memberRepository = ac.getBean("memberRepository1", MemberRepository::class.java)
        assertThat(memberRepository).isInstanceOf(MemberRepository::class.java)
    }

    @DisplayName(value = "특정 타입을 모두 조회하기")
    @Test
    fun findAllBeanByType() {
        val beansOfType = ac.getBeansOfType(MemberRepository::class.java)
        for (key in beansOfType.keys) {
            println("key = $key value = ${beansOfType[key]}")
        }
        println("beansOfType = $beansOfType")
        assertThat(beansOfType.size).isEqualTo(2)
    }

    @Configuration
    internal class SameBeanConfig {
        @Bean
        fun memberRepository1(): MemberRepository {
            return MemoryMemberRepository()
        }

        @Bean
        fun memberRepository2(): MemberRepository {
            return MemoryMemberRepository()
        }
    }
}