package hello.core.beanfind

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.discount.RateDiscountPolicy
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextExtendsFindTest {
    private val ac = AnnotationConfigApplicationContext(TestConfig::class.java)

    @DisplayName(value = "부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    @Test
    fun findBeanByParentTypeDuplicate() {
        assertThatThrownBy { ac.getBean(DiscountPolicy::class.java) }
            .isInstanceOf(NoUniqueBeanDefinitionException::class.java)
    }

    @DisplayName(value = "부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    @Test
    fun findBeanByParentTypeBeanName() {
        val rateDiscountPolicy: DiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy::class.java)
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @DisplayName(value = "특정 하위 타입으로 조회")
    @Test
    fun findBeanBySubType() {
        val bean: RateDiscountPolicy = ac.getBean(RateDiscountPolicy::class.java)
        assertThat(bean).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @DisplayName(value = "부모 타입으로 모두 조회하기")
    @Test
    fun findAllBeanByParentType() {
        val beansOfType: MutableMap<String, DiscountPolicy> = ac.getBeansOfType(DiscountPolicy::class.java)
        assertThat(beansOfType.size).isEqualTo(2)
        for (key in beansOfType.keys) {
            println("key = $key value = ${beansOfType[key]}")
        }
    }

    @DisplayName(value = "부모 타입으로 모두 조회하기 - Object")
    @Test
    fun findAllBeanByObjectType() {
        val beansOfType: MutableMap<String, Any> = ac.getBeansOfType(Any::class.java)
        for (key in beansOfType.keys) {
            println("key = $key value = ${beansOfType[key]}")
        }
    }

    @Configuration
    internal class TestConfig {
        @Bean
        fun rateDiscountPolicy(): DiscountPolicy {
            return RateDiscountPolicy()
        }

        @Bean
        fun fixDiscountPolicy(): DiscountPolicy {
            return FixDiscountPolicy()
        }
    }
}