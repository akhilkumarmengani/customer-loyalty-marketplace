package models;


public class Activity {
	int ActivityId;
	String ActivityName;
	String ActivityCode;

	public String getActivityCode() {
		return ActivityCode;
	}

	public void setActivityCode(String activityCode) {
		ActivityCode = activityCode;
	}

	public Activity(String activityName) {
		ActivityName = activityName;
	}

	public Activity() {
		// TODO Auto-generated constructor stub
	}

	public int getActivityId() {
		return ActivityId;
	}

	public void setActivityId(int activityId) {
		ActivityId = activityId;
	}

	public String getActivityName() {
		return ActivityName;
	}

	public void setActivityName(String activityName) {
		ActivityName = activityName;
	}

}