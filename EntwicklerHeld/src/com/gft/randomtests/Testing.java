package com.gft.randomtests;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class Testing {

	public static void main(String[] args) {
			
		PascalsTriangleJava.getPascalsTriangleRow(5);
	}

	public class PascalsTriangleJava {
		public static void getPascalsTriangleRow(int rowNumber) {
			List<Long> pascalsList = new ArrayList<>();

			for (int i = 0; i < rowNumber; i++) {
				long number = 1;

				for (int j = 0; j <= i; j++) {
					System.out.print(number + " ");
					number = number * (i - j) / (j + 1);
				}
				System.out.println();
			}
			
		}
	}

}
