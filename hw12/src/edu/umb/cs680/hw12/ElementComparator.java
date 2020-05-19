package edu.umb.cs680.hw12;

import java.util.Comparator;

public class ElementComparator implements Comparator<ApfsElement>{

	@Override
	public int compare(ApfsElement f_One, ApfsElement f_Two) {
		// TODO Auto-generated method stub
		if(f_One instanceof ApfsDirectory && f_Two instanceof ApfsDirectory)
			return 3;
		else if(f_One instanceof ApfsDirectory && f_Two instanceof ApfsFile)
			return 2;
		else if(f_Two instanceof ApfsDirectory && f_One instanceof ApfsFile)
			return 1;
		else 
			return 0;
	}

	public static void main(String[] args) {
		System.out.println("Class ElementComparator Executed!");
	}
}
