package test;

import java.util.ArrayList;
import java.util.List;

import pojo.Teacher;

public class ObjectTest {
	public static void main(String... args) {
		Teacher a = new Teacher();
		a.setName("qian");
		Teacher b = a;
		b.setName("zhou");
		System.out.println(a.getName());
	}
}
