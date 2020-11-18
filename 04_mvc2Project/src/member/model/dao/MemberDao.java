package member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDao {

	public static int insertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		String query ="insert into membership values(mem_seq.nextval,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		
		int result =0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","webserver","1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getGender());
			result = pstmt.executeUpdate();
			
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public Member selectOneMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select * from membership where member_id=? and member_pw=?";
		ResultSet rset = null;
		Member loginMember = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","webserver","1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				loginMember = new Member();
				int memberNo =  rset.getInt("member_no");
				loginMember.setMemberNo(memberNo);
				String memberId = rset.getString("member_id");
				loginMember.setMemberId(memberId);
				loginMember.setMemberPw(rset.getString("member_pw"));
				loginMember.setMemberName(rset.getString("membername"));
				loginMember.setPhone(rset.getNString("phone"));
				loginMember.setEmail(rset.getString("email"));
				loginMember.setGender(rset.getString("gender"));
				loginMember.setEnrollDate(rset.getString("enroll_date"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return loginMember;
	}

	public int updateMember(Member member) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update membership set member_pw=?,phone=?, email=? where member_no=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","webserver","1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setInt(4, member.getMemberNo());
			result = pstmt.executeUpdate();
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public ArrayList<Member> selectAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select * from membership";
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","webserver","1234");
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member Members = new Member();
				Members.setMemberNo(rset.getInt("member_no"));
				Members.setMemberId(rset.getString("member_id"));
				Members.setMemberPw(rset.getString("member_pw"));
				Members.setMemberName(rset.getString("membername"));
				Members.setPhone(rset.getString("phone"));
				Members.setEmail(rset.getString("email"));
				Members.setGender(rset.getString("gender"));
				Members.setEnrollDate(rset.getString("enroll_date"));
				list.add(Members);
			}			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return list;
	}

	public int deleteMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from membership where member_no=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","webserver","1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, member.getMemberNo());
			result = pstmt.executeUpdate();
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public Member selectOneMember(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select * from membership where member_id=?";
		ResultSet rset = null;
		Member loginMember = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","webserver","1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				loginMember = new Member();
				int memberNo =  rset.getInt("member_no");
				loginMember.setMemberNo(memberNo);
				String memberId1 = rset.getString("member_id");
				loginMember.setMemberId(memberId1);
				loginMember.setMemberPw(rset.getString("member_pw"));
				loginMember.setMemberName(rset.getString("membername"));
				loginMember.setPhone(rset.getNString("phone"));
				loginMember.setEmail(rset.getString("email"));
				loginMember.setGender(rset.getString("gender"));
				loginMember.setEnrollDate(rset.getString("enroll_date"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return loginMember;
	}
}
