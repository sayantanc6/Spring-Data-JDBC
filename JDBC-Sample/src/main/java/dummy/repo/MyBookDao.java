package dummy.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import dummy.entity.Book;

@Component
public abstract class MyBookDao {
	
	
	public MyBookDao() {
		super();
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate.setDataSource(dataSource);        
    }

	public int[] batchInsert(final List<Book> books) {
        return jdbcTemplate.batchUpdate("INSERT INTO BOOK VALUES (?, ?, ?, ?)", new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                ps.setString(1, books.get(i).getAuthor());
                ps.setInt(2, books.get(i).getIsbn());
                ps.setString(3, books.get(i).getTitle());
                ps.setDouble(4, books.get(i).getPrice());
            }

            @Override
            public int getBatchSize() {
                return 4;
            }
        });
    }
}
