package pojo;

public class Student {
	private String name;
	private String age;
	private String id;
	private Integer teacherId;
	private Teacher teacher;
	private Integer computerId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Integer getComputerId() {
		return computerId;
	}
	public void setComputerId(Integer computerId) {
		this.computerId = computerId;
	}
	
}
