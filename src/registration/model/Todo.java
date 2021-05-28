package registration.model;

import java.time.LocalDate;

public class Todo {

	private Long id;
	private String title;
	private String username;
	private String description;
	private LocalDate StartDate;
	private LocalDate EndDate;
	private LocalDate RemainderDate;
	private boolean status;
	private String notebook_name;
	
	protected Todo() {
		
	}
	
	public Todo(long id, String title, String username, String description, LocalDate StartDate,LocalDate EndDate,LocalDate RemainderDate, boolean isDone,String notebook_name) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
		this.description = description;
		this.StartDate = StartDate;
		this.EndDate=EndDate;
		this.RemainderDate=RemainderDate;
		this.status = isDone;
		this.notebook_name=notebook_name;
	}

	public Todo(String title, String username, String description, LocalDate StartDate,LocalDate EndDate,LocalDate RemainderDate, boolean isDone,String notebook_name) {
		super();
		this.title = title;
		this.username = username;
		this.description = description;
		this.StartDate = StartDate;
		this.EndDate=EndDate;
		this.RemainderDate=RemainderDate;
		this.status = isDone;
		this.notebook_name=notebook_name;
	}
	
	public LocalDate getStartDate() {
		return StartDate;
	}

	public void setStartDate(LocalDate startDate) {
		StartDate = startDate;
	}

	public LocalDate getEndDate() {
		return EndDate;
	}

	public void setEndDate(LocalDate endDate) {
		EndDate = endDate;
	}

	public LocalDate getRemainderDate() {
		return RemainderDate;
	}

	public void setRemainderDate(LocalDate remainderDate) {
		RemainderDate = remainderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNotebook_name() {
		return notebook_name;
	}

	public void setNotebook_name(String notebook_name) {
		this.notebook_name = notebook_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}
}