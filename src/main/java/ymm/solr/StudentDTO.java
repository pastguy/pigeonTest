package ymm.solr;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class StudentDTO {

	@Field("id")
	private Long id; 
	@Field("name")
	private String name;
	@Field("age")
	private Integer age;
	@Field("score")
	private Double score;
	@Field("birthday")
	private String birthday;
	@Field("updateTime")
	private String updateTime;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", name=" + name + ", age=" + age + ", score=" + score + ", birthday="
				+ birthday + ", updateTime=" + updateTime + "]";
	}
	
	
}
