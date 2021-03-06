package hello.core.member

class MemoryMemberRepository : MemberRepository {
    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(memberId: Long): Member {
        return store[memberId]!!
    }

    companion object {
        private val store: MutableMap<Long, Member> = hashMapOf()
    }
}