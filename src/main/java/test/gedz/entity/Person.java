package test.gedz.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gedz on 16-Jun-18.
 */
@Data
@Entity
public class Person  implements Serializable {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	private Date birthday;
}
