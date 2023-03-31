package model;

public class Cat {

	private String name;
	private CatRace race;
	private int age;
	
	public Cat(String name, CatRace race, int age) {
		this.name = name;
		this.race = race;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CatRace getRace() {
		return race;
	}
	public void setRace(CatRace race) {
		this.race = race;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
