package maeg.hellospring.repository;

import maeg.hellospring.domain.member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {
    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public member save(member member) {
        String sql = "insert into member(name) values(?)";

        Connection conn = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()){
                member.setId(rs.getLong(1));
            }else{
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally{
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()){
                member member = new member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally{
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if (rs.next()){
                member member = new member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally{
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<member> members = new ArrayList<>();
            while (rs.next()){
                member member = new member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally{
            close(conn, pstmt, rs);
        }
    }

    // DataSourceUtils를 이용해서 Connection을 받아오는 것이 유지관리상 유용하다.
    // 트랜잭션에서 커넥션을 동일하게 유지하게 돕는다.
    private Connection getConnection(){
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try{
            if (rs != null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if (pstmt != null){
                pstmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if (conn != null){
                close(conn);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
