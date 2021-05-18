package de.fhswf.ea.edit.algorithmn;

public class EditDistance {

	private String x = "algorithmn";
	private String zFinal = "altruistic";
	private char[] z = new char[zFinal.length()];
	
	private int i = 0;
	private int j = 0;
	
	
	int[][] arr = new int[x.length() + 1][zFinal.length() + 1];
	
	public EditDistance() {
		System.out.println(maxFormula(x.length(), zFinal.length()));
		
		
		for(int j = 0; j <= zFinal.length(); j++) {
			for(int i = 0; i <= zFinal.length(); i++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private int min(int x, int y, int z) {
		if(x <= y && x <= z)
			return x;
		if(y <= x && y <= z)
			return y;
		return z;
	}
	
	private int maxFormula(int i, int j) {
		if(i == 0) {
			arr[i][j] = j;
			return j;
		}
		if(j == 0) {
			arr[i][j] = i;
			return i;
		}
		if(x.charAt(i - 1) == zFinal.charAt(j - 1)) {
			int r = min(maxFormula(i - 1, j - 1), maxFormula(i - 1, j) + 1, maxFormula(i, j - 1) + 1);
			arr[i][j] = r;
			return(r);
		} else {
			int r = min(maxFormula(i - 1, j - 1), maxFormula(i - 1, j), maxFormula(i, j - 1)) + 1;
			arr[i][j] = r;
			return(r);
		}
	}
	
	
	
	private void copy() {
		z[j] = x.charAt(i);
		i++;
		j++;
	}
	
	private void insert(char c) {
		z[j] = c;
		j++;
	}
	
	private void delete() {
		i++;
	}
	
	private void replace(char c) {
		z[j] = c;
		i++;
		j++;
	}
	
	private void swap() {
		z[j] = x.charAt(i + 1);
		z[j + 1] = x.charAt(i);
		
		i+=2;
		j+=2;
	}
	
	private void destroy() {
		i = zFinal.length();
	}
	
}
