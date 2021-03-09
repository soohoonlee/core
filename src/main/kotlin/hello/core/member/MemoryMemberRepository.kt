package hello.core.member

class MemoryMemberRepository : MemberRepository {
    private var store: MutableMap<Long, Member> = HashMap()

    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(memberId: Long): Member? {
        return store[memberId]
    }
}