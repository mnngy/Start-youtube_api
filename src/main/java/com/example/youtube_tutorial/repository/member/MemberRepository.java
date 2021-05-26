package com.example.youtube_tutorial.repository.member;

import com.example.youtube_tutorial.dto.youtube.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberRepository {

    private final DataSource dataSource;

    @Autowired
    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 아이디 중복 확인
     */
    public int memberSelectCountById(String memberId) {
        String sql = "select count(*) from member where memberId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = -1;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            rs.next();
            result = rs.getInt("count(*)");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result; // 1: 존재하는 아이디, 0: 존재하지 않는 아이디, -1: 데이터베이스 오류
    }

    /**
     * 회원가입
     */
    public void memberSave(MemberDto memberDto) {
        String sql = "insert into member(memberId, memberPassword) values(?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDto.getMemberId());
            pstmt.setString(2, memberDto.getMemberPassword());
            pstmt.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * 로그인
     */
    public int memberSelectPasswordById(MemberDto memberDto) throws SQLException {
        String sql = "select memberPassword from member where memberId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDto.getMemberId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                if (rs.getString("memberPassword").equals(memberDto.getMemberPassword())) {
                    return 1; // 로그인 성공
                } else {
                    return 0; // 비밀번호 불일치
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (rs != null) {rs.close();}
            if (pstmt != null) {pstmt.close();}
            if (conn != null) {conn.close();}
        }
        return -1; // 아이디 불일치
    }
}
