package com.ehealth.HistogramOfBigram;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HistogramOfBigramApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HistogramOfBigramApplication.class, args);
	}

	@Override
	public void run(String... args){

		if(args.length == 0 ){
			System.out.print("Please pass the filepath as an argument.");
			return;
		}

		try{
			LinkedHashMap<String, Integer> histogramMap =  HistogramOfBigram.getBigramMap(args[0]);
			histogramMap.forEach((k,v) -> System.out.println(k + "  " + v));
		}
		catch (FileNotFoundException ex){
			System.out.println("File not found at the path provided.");
		} catch(IOException ex){
			System.out.println("File to read the file.");
		}
	}
}