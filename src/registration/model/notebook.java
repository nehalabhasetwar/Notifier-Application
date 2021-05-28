package registration.model;

public class notebook {
   private String notebook_name;
   private String username;
   private int notes_count;
   private Long id;
   
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNotebook_name() {
	return notebook_name;
}
public void setNotebook_name(String notebook_name) {
	this.notebook_name = notebook_name;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

public int getNotes_count() {
	return notes_count;
}
public void setNotes_count(int notes_count) {
	this.notes_count = notes_count;
}
public notebook(String notebook_name, String username,int notes_count) {
	super();
	this.notebook_name = notebook_name;
	this.username = username;
	this.notes_count=notes_count;
}
   
public notebook(String notebook_name, String username, int notes_count, Long id) {
	super();
	this.notebook_name = notebook_name;
	this.username = username;
	this.notes_count = notes_count;
	this.id = id;
}
public notebook() {
	
}  
}
