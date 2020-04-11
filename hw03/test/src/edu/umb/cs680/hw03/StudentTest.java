package edu.umb.cs680.hw03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class StudentTest {

	@Test
	public void InStateStudentStatus_INSTATE() {
		Student intl = Student.createinStateStudent("Owais", "Boston");
		assertEquals(StudentStatus.INSTATE, intl.getStatus());
	}

	@Test
	public void OutStateStudentStatus_OUTSTATE() {
		Student intl = Student.createOutStateStudent("Owais", "Boston", 5);
		assertEquals(StudentStatus.OUTSTATE, intl.getStatus());
	}

	@Test
	public void IntlStudentSTATUS_INTL() {
		Student intl = Student.createIntlStudent("Owais", "Boston", 12, "India");
		assertEquals(StudentStatus.INTL, intl.getStatus());
	}

	@Test
	public void IntlStudentTUITION_17000() {
		Student intl = Student.createIntlStudent("Owais", "Boston", 12, "India");
		assertEquals(17000, intl.getTuition());
	}

	@Test
	public void OutStateStudentTUITION_12000() {
		Student intl = Student.createOutStateStudent("Owais", "Boston", 5);
		assertEquals(12000, intl.getTuition());
	}

	@Test
	public void InStateStudentTUITION_9000() {
		Student intl = Student.createinStateStudent("Owais", "Boston");
		assertEquals(9000, intl.getTuition());
	}

	@Test
	public void InStateStudentTUITION_Not10000() {
		Student intl = Student.createinStateStudent("Owais", "Boston");
		assertNotEquals(10000, intl.getTuition());
	}

	@Test
	public void InStateStudentTUITION_Not17000() {
		Student intl = Student.createinStateStudent("Owais", "Boston");
		assertNotEquals(17000, intl.getTuition());
	}

	@Test
	public void InStateStudentTUITION_Not15000() {
		Student intl = Student.createinStateStudent("Owais", "Boston");
		assertNotEquals(15000, intl.getTuition());
	}

	@Test
	public void InStateStudentTUITION_Not9000() {
		Student intl = Student.createinStateStudent("Owais", "Boston");
		assertNotEquals(17000, intl.getTuition());
	}

	@Test
	public void OutStateStudentTUITION_Not17000() {
		Student intl = Student.createOutStateStudent("Owais", "Boston", 5);
		assertNotEquals(17000, intl.getTuition());
	}

	@Test
	public void OutStateStudentTUITION_Not9000() {
		Student intl = Student.createOutStateStudent("Owais", "Boston", 5);
		assertNotEquals(9000, intl.getTuition());
	}

	@Test
	public void IntlStudentTUITION_Not7800() {
		Student intl = Student.createIntlStudent("Owais", "Boston", 12, "India");
		assertNotEquals(7800, intl.getTuition());
	}

	@Test
	public void IntlStudentTUITION_Not120000() {
		Student intl = Student.createIntlStudent("Owais", "Boston", 12, "India");
		assertNotEquals(120000, intl.getTuition());
	}

	@Test
	public void IntlStudentTUITION_Not12000() {
		Student intl = Student.createIntlStudent("Owais", "Boston", 12, "India");
		assertNotEquals(12000, intl.getTuition());
	}

}
