package ww.assessment;

public enum DayOfWeek {


	  SUN(1), MON(2),TUE(3),WED(4),THU(5),FRI(6),SAT(7);
	  private int id;
	  DayOfWeek(int id) {
	    this.id = id;
	  }

	  public int getId() { return id;}
	}