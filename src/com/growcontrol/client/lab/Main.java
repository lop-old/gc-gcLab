package com.growcontrol.client.lab;

import com.growcontrol.client.MainBootstrap;


public class Main extends MainBootstrap {



	protected Main() {
		super();
	}



	public static void main(final String[] argsArray) {
		final MainBootstrap main = new Main();
		main.doMain(argsArray);
	}



	@Override
	protected gcLab newClient() {
		return new gcLab();
	}



}
