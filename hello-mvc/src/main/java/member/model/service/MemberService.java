package member.model.service;

import static common.JdbcTemplate.*;
import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.dto.Member;

/**
 * 
 * 1. connection생성
 * 2. dao요청(connection)
 * 3. dml경우 transaction처리
 * 4. connection반환
 * 5. controller로 값 반환처리
 */
public class MemberService {

	private MemberDao memberDao = new MemberDao();
	
	public Member findByMemberId(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.findByMemberId(conn, memberId);
		close(conn);
		return member;
	}
	
	/**
	 * 1. Connection객체 생성
	 * 2. dao요청
	 * 3. 트랜잭션처리
	 * 4. Connection객체 반환
	 * 
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller에게 통보용!
		} finally {
			close(conn);			
		}
		return result;
	}

	public int updateMember(Member member) {
		int result = 0;
		// 1. Connection객체 생성
		Connection conn = getConnection();
		try {
			// 2. dao 요청
			result = memberDao.updateMember(conn, member);
			// 3. 트랜잭션 처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller 통보용
		} finally {
			// 4. Connection객체 반환
			close(conn);
		}
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		// 1. Connection객체 생성
		Connection conn = getConnection();
		try {			
			// 2. dao 요청
			result = memberDao.deleteMember(conn, memberId);
			// 3. 트랜잭션 처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller 통보용(예외던지기)
		} finally {
			// 4. Connection객체 반환
			close(conn);
		}
		return result;
	}

}