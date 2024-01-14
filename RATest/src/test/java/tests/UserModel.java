package tests;

public class UserModel {

	public static class RequestCreateUser {
	   
		private String name;
	    private String job;

		    public RequestCreateUser() {}
	
		    public RequestCreateUser(String name, String job) {
		        this.name = name;
		        this.job = job;
		    }
	
			public String getName() {
				return name;
			}
	
			public void setName(String name) {
				this.name = name;
			}
	
			public String getJob() {
				return job;
			}
	
			public void setJob(String job) {
				this.job = job;
			}

	}

	public class ResponseCreateUser {
		
	    private String id;
	    private String createdAt;
	    
		    public ResponseCreateUser() {}
		
		    public ResponseCreateUser(String id, String createdAt) {
		        this.id = id;
		        this.createdAt = createdAt;
		    }
	
			public String getId() {
				return id;
			}
	
			public void setId(String id) {
				this.id = id;
			}
	
			public String getCreatedAt() {
				return createdAt;
			}
	
			public void setCreatedAt(String createdAt) {
				this.createdAt = createdAt;
			}
	
	}

}
