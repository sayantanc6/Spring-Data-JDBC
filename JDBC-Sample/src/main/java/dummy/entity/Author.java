package dummy.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

	@Id
	private int Authorid;
	
	private String fullname;
	
	private String email;
	
}
