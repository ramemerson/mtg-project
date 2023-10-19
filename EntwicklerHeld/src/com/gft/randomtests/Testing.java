package com.gft.randomtests;

import java.math.BigInteger;

import javax.naming.spi.DirStateFactory.Result;

public class Testing {

	public static void main(String[] args) {

		System.out.println(Grains.grainsOnSquare(7));
		
	}

	public class Grains {
		
		public static BigInteger grainsOnSquare(final int square) {
			if (square < 1 || square > 64) throw new IllegalArgumentException();
			
			BigInteger result = BigInteger.valueOf(2).pow(square - 1);
			
			return result;
			
		}

	}

}
