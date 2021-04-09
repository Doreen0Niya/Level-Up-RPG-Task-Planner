package entity;

public class Task {
	
	private Subject subject;
	private boolean finished = false;
	private String comment;
	private String deadline;
	
	public Task(Subject subject) {
		this.subject = subject;
		deadline = "00000000";
	}
	
	@Override
	public String toString() {
		return this.subject.getSubjectName() + "[" + this.subject.getSubjectCategory().getCategoryName() + "]";
	}
	
	public void finishTask() {
		this.finished = true;
	}
	
	public boolean ifFinished() {
		return finished;
	}

	public Subject getSubject() {
		return subject;
	}

	public boolean isSame(String subjectName, String deadline) {
		return(this.subject.getSubjectName().equals(subjectName)&&this.deadline.equals(deadline));
	}
	//implemented later
//	private String startDate;
//	private String endDate;
//	private String startTime;
//	private String endTime;

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
}
