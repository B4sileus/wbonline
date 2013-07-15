// delete me

package Util;

import java.io.BufferedReader;
import java.io.FileReader;

public class WhateverUtil {
	
	public static int WINDOWS = 1, LINUX = 2;
	public static int os;
	
	// yes, this is ridiculous, but I will change it later
	public static void setConfig(String a, String b, String c) {
		String end;
		FileReader ent;
		BufferedReader buffered;
		
		try { // windows
			end = a + "\\" + b + "\\" + c;
			
			ent = new FileReader(end);
			buffered = new BufferedReader(ent);
			
			String in = "";
			String split[];
			
			while( (in = buffered.readLine()) != null ) {
				split = in.split("=");
				
				if(split[0].contentEquals("end")) {
					buffered.close();
					ent.close();
					
					return;
				} else if(split[0].contentEquals("os")) { // operation system
					os = WINDOWS;
				}
			}
			
			buffered.close();
			ent.close();
			
			return;
		} catch (Exception e) { }
		
		try { // linux
			end = a + "/" + b + "/" + c;
			
			ent = new FileReader(end);
			buffered = new BufferedReader(ent);
			
			String in = "";
			String split[];
			
			while( (in = buffered.readLine()) != null ) {
				split = in.split("=");
				
				if(split[0].contentEquals("end")) {
					buffered.close();
					ent.close();
					
					return;
				} else if(split[0].contentEquals("os")) { // operation system
					os = LINUX;
				}
			}
			
			buffered.close();
			ent.close();
		} catch (Exception e) {
			System.out.println("Arquivo de configuração não encontrado");
		}
	}
	
}
