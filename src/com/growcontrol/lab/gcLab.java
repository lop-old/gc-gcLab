package com.growcontrol.lab;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fusesource.jansi.AnsiConsole;

import com.growcontrol.lab.guiManager.GUI_MODE;
import com.poixson.commonapp.app.xApp;
import com.poixson.commonapp.app.annotations.xAppStep;
import com.poixson.commonapp.app.annotations.xAppStep.StepType;
import com.poixson.commonjava.xVars;
import com.poixson.commonjava.Utils.utilsString;


public class gcLab extends xApp {



	/**
	 * Get the client class instance.
	 * @return client instance object.
	 */
	public static gcLab get() {
		return (gcLab) xApp.get();
	}



	/**
	 * Application start entry point.
	 * @param args Command line arguments.
	 */
	public static void main(final String[] args) {
		initMain(args, gcLab.class);
	}
	public gcLab() {
		super();
		gcLabVars.init();
		if(xVars.debug())
			this.displayColors();
		this.displayLogo();
	}



	/**
	 * Handle command-line arguments.
	 */
	@Override
	protected void processArgs(final List<String> args) {
		final Iterator<String> it = args.iterator();
		while(it.hasNext()) {
			final String arg = it.next();
			switch(arg) {
			}
		}
	}



	// ------------------------------------------------------------------------------- //
	// startup



	// load config
	@xAppStep(type=StepType.STARTUP, title="Config", priority=20)
	public void __STARTUP_config() {
		gcLabVars.getConfig();
	}



	// start gui
	@xAppStep(type=StepType.STARTUP, title="GUI", priority=40)
	public void __STARTUP_gui() {
		guiManager.get();
	}



//	// load plugins
//	@xAppStep(type=StepType.STARTUP, title="LoadPlugins", priority=50)
//	public void __STARTUP_load_plugins() {
//		final xPluginManager manager = xPluginManager.get();
//		manager.setClassField("Client Main");
//		manager.loadAll();
//		manager.initAll();
//	}



//	// enable plugins
//	@xAppStep(type=StepType.STARTUP, title="EnablePlugins", priority=55)
//	public void __STARTUP_enable_plugins() {
//		xPluginManager.get()
//			.enableAll();
//	}



//	// scripts
//	@xAppStep(type=StepType.STARTUP, title="Scripts", priority=95)
//	public void __STARTUP_scripts() {
//		final gcScriptManager manager = gcScriptManager.get();
//		manager.loadAll();
//		manager.StartAll();
//	}



	// show login window
	@xAppStep(type=StepType.STARTUP, title="LoginWindow", priority=98)
	public void __STARTUP_login_window() {
		guiManager.get()
			.Show(GUI_MODE.LOGIN);
	}



	// ------------------------------------------------------------------------------- //
	// shutdown



//	// scripts
//	@xAppStep(type=StepType.SHUTDOWN, title="Scripts", priority=95)
//	public void __SHUTDOWN_scripts() {
//		gcScriptManager.get()
//			.StopAll();
//	}



//	// close windows
//	@xAppStep(type=StepType.SHUTDOWN, title="CloseWindows", priority=98)
//	public void __SHUTDOWN_close_windows() {
//		final guiManager manager = guiManager.peak();
//		if(manager != null)
//			manager.shutdown();
//TODO:
//		guiManager.Shutdown();
//	}



//	// sockets
//	@xAppStep(type=StepType.SHUTDOWN, title="Sockets", priority=90)
//	public void __SHUTDOWN_sockets() {
//TODO:
//	}



//	// disable plugins
//	@xAppStep(type=StepType.SHUTDOWN, title="DisablePlugins", priority=55)
//	public void __SHUTDOWN_disable_plugins() {
//		xPluginManager.get()
//			.disableAll();
//	}



//	// unload plugins
//	@xAppStep(type=StepType.SHUTDOWN, title="UnloadPlugins", priority=50)
//	public void __SHUTDOWN_unload_plugins() {
//		xPluginManager.get()
//			.unloadAll();
//	}



	// ------------------------------------------------------------------------------- //



/*
	protected void updateConfig() {
		// config version
		{
			boolean configVersionDifferent = false;
			final String configVersion = this.config.getVersion();
			final String clientVersion = this.getVersion();
			if(utils.notEmpty(configVersion) && utils.notEmpty(clientVersion)) {
				if(configVersion.endsWith("x") || configVersion.endsWith("*")) {
					final String vers = utilsString.trims(configVersion, "x", "*");
					if(!clientVersion.startsWith(vers))
						configVersionDifferent = true;
				} else {
					if(!configVersion.equals(clientVersion))
						configVersionDifferent = true;
				}
			}
			if(configVersionDifferent)
				log().warning(gcClientDefines.CONFIG_FILE+" for this client may need updates");
		}
		// log level
		{
			final Boolean debug = this.config.getDebug();
			if(debug != null && debug.booleanValue())
				xVars.debug(debug.booleanValue());
			if(!xVars.debug()) {
				// set log level
				final xLevel level = this.config.getLogLevel();
				if(level != null)
					xLog.getRoot()
						.setLevel(level);
			}
		}
	}
*/



//	// connect to server
//	public void Connect(String host, int port, String user, String pass) {
//		pxnThreadQueue.addToMain("SocketConnect",
//			new doConnect(host, port, user, pass));
//	}



//	private class doConnect implements Runnable {
//
//		public pxnSocketClient socket = null;
//		private final String host;
//		private final int    port;
//@SuppressWarnings("unused")
//		private final String user;
//@SuppressWarnings("unused")
//		private final String pass;
//
//
//		public doConnect(String host, int port, String user, String pass) {
//			if(host == null || host.isEmpty())
//				host = "127.0.0.1";
//			if(port < 1) port = 1142;
//			if(user == null || user.isEmpty()) user = null;
//			if(pass == null || pass.isEmpty()) pass = null;
//			this.host = host;
//			this.port = pxnUtilsMath.MinMax(port, 1, 65535);
//			this.user = user;
//			this.pass = pass;
//		}
//
//
//		// connect to server
//		@Override
//		public synchronized void run() {
//pxnLog.get().info("Connecting..");
//			// create socket
//			if(socket == null)
//				socket = new pxnSocketClient();
//			socket.setHost(this.host);
//			socket.setPort(this.port);
//			// create processor
//			socket.setFactory(new pxnSocketProcessorFactory() {
//				@Override
//				public gcPacketReader newProcessor() {
//					return new gcPacketReader();
//				}
//			});
//			socket.Start();
//			if(!pxnSocketState.CONNECTED.equals(socket.getState())) {
//				pxnLog.get().warning("Failed to connect!");
//				return;
//			}
//			// send HELLO packet
//			gcPacketSender.sendHELLO(
//				socket.getWorker(),
//				gcClient.version);
////				connectInfo.username,
////				connectInfo.password);
//pxnLog.get().severe("CONNECTED!!!!!!!!!!!!!!!!!!!");
//			guiManager.get().Update(guiManager.GUI.DASH);
//		}
//
//
//	}



//	// get zones
//	public List<String> getZones() {
//		synchronized(zones) {
//			return new ArrayList<String>(zones);
//		}
//	}
//	public String[] getZonesArray() {
//		synchronized(zones) {
//			return (String[]) zones.toArray();
//		}
//	}



	// ascii header
	@Override
	protected void displayLogo() {
		// colors
		final String COLOR_POIXSON_P   = "bold,green";
		final String COLOR_POIXSON_OI  = "bold,blue";
		final String COLOR_POIXSON_X   = "bold,green";
		final String COLOR_POIXSON_SON = "bold,blue";
		final String COLOR_COPY        = "bold,black";
		final String COLOR_GROW        = "bold,green";
		final String COLOR_CONTROL     = "bold,white";
		final String COLOR_SOFTWARE    = "cyan";
		final String COLOR_VERSION     = "cyan";
		final String COLOR_GRASS       = "green";
		final String COLOR_FLOWER_STEM     = "green";
		final String COLOR_FLOWER_A_PEDALS = "red";
		final String COLOR_FLOWER_A_CENTER = "bold,red";
		final String COLOR_FLOWER_B_PEDALS = "red";
		final String COLOR_FLOWER_B_CENTER = "bold,red";
		final String COLOR_FLOWER_C_PEDALS = "bold,blue";
		final String COLOR_FLOWER_C_CENTER = "blue";
		final String COLOR_FLOWER_D_PEDALS = "white";
		final String COLOR_FLOWER_D_CENTER = "bold,white";
		final String COLOR_FLOWER_E_PEDALS = "white";
		final String COLOR_FLOWER_E_CENTER = "bold,white";
		final String COLOR_FLOWER_F_PEDALS = "bold,yellow";
		final String COLOR_FLOWER_F_CENTER = "yellow";
		final String COLOR_FLOWER_G_PEDALS = "green";
		final String COLOR_FLOWER_G_CENTER = "bold,green";
		final String COLOR_FLOWER_H_PEDALS = "blue";
		final String COLOR_FLOWER_H_CENTER = "bold,blue";
		// line 1
		final Map<Integer, String> colors1 = new LinkedHashMap<Integer, String>();
		colors1.put(new Integer(7),  COLOR_POIXSON_P);
		colors1.put(new Integer(8),  COLOR_POIXSON_OI);
		colors1.put(new Integer(10), COLOR_POIXSON_X);
		colors1.put(new Integer(11), COLOR_POIXSON_SON);
		// line 2
		final Map<Integer, String> colors2 = new LinkedHashMap<Integer, String>();
		colors2.put(new Integer(4),  COLOR_COPY);
		colors2.put(new Integer(5),  COLOR_GROW);
		colors2.put(new Integer(9),  COLOR_CONTROL);
		colors2.put(new Integer(27), COLOR_FLOWER_D_PEDALS);
		// line 3
		final Map<Integer, String> colors3 = new LinkedHashMap<Integer, String>();
		colors3.put(new Integer(9),  COLOR_SOFTWARE);
		colors3.put(new Integer(26), COLOR_FLOWER_D_PEDALS);
		colors3.put(new Integer(28), COLOR_FLOWER_D_CENTER);
		colors3.put(new Integer(33), COLOR_FLOWER_D_PEDALS);
		colors3.put(new Integer(49), COLOR_FLOWER_F_PEDALS);
		// line 4
		final Map<Integer, String> colors4 = new LinkedHashMap<Integer, String>();
		colors4.put(new Integer(2),  COLOR_VERSION);
		colors4.put(new Integer(18), COLOR_FLOWER_C_PEDALS);
		colors4.put(new Integer(26), COLOR_FLOWER_D_PEDALS);
		colors4.put(new Integer(29), COLOR_FLOWER_D_CENTER);
		colors4.put(new Integer(32), COLOR_FLOWER_D_PEDALS);
		colors4.put(new Integer(37), COLOR_FLOWER_E_PEDALS);
		colors4.put(new Integer(47), COLOR_FLOWER_F_PEDALS);
		// line 5
		final Map<Integer, String> colors5 = new LinkedHashMap<Integer, String>();
		colors5.put(new Integer(18), COLOR_FLOWER_C_PEDALS);
		colors5.put(new Integer(19), COLOR_FLOWER_C_CENTER);
		colors5.put(new Integer(22), COLOR_FLOWER_C_PEDALS);
		colors5.put(new Integer(28), COLOR_FLOWER_D_PEDALS);
		colors5.put(new Integer(29), COLOR_FLOWER_STEM);
		colors5.put(new Integer(31), COLOR_FLOWER_D_PEDALS);
		colors5.put(new Integer(37), COLOR_FLOWER_E_PEDALS);
		colors5.put(new Integer(38), COLOR_FLOWER_E_CENTER);
		colors5.put(new Integer(41), COLOR_FLOWER_E_PEDALS);
		colors5.put(new Integer(46), COLOR_FLOWER_F_PEDALS);
		colors5.put(new Integer(49), COLOR_FLOWER_F_CENTER);
		colors5.put(new Integer(50), COLOR_FLOWER_F_PEDALS);
		colors5.put(new Integer(60), COLOR_FLOWER_H_PEDALS);
		colors5.put(new Integer(62), COLOR_FLOWER_H_CENTER);
		colors5.put(new Integer(63), COLOR_FLOWER_H_PEDALS);
		// line 6
		final Map<Integer, String> colors6 = new LinkedHashMap<Integer, String>();
		colors6.put(new Integer(10), COLOR_FLOWER_B_PEDALS);
		colors6.put(new Integer(11), COLOR_FLOWER_B_CENTER);
		colors6.put(new Integer(12), COLOR_FLOWER_B_PEDALS);
		colors6.put(new Integer(13), COLOR_FLOWER_STEM);
		colors6.put(new Integer(18), COLOR_FLOWER_C_PEDALS);
		colors6.put(new Integer(29), COLOR_FLOWER_STEM);
		colors6.put(new Integer(37), COLOR_FLOWER_E_PEDALS);
		colors6.put(new Integer(46), COLOR_FLOWER_STEM);
		colors6.put(new Integer(48), COLOR_FLOWER_F_PEDALS);
		colors6.put(new Integer(60), COLOR_FLOWER_STEM);
		colors6.put(new Integer(61), COLOR_FLOWER_H_PEDALS);
		colors6.put(new Integer(64), COLOR_FLOWER_STEM);
		// line 7
		final Map<Integer, String> colors7 = new LinkedHashMap<Integer, String>();
		colors7.put(new Integer(2),  COLOR_FLOWER_A_PEDALS);
		colors7.put(new Integer(10), COLOR_FLOWER_STEM);
		colors7.put(new Integer(11), COLOR_FLOWER_B_PEDALS);
		colors7.put(new Integer(12), COLOR_FLOWER_STEM);
		colors7.put(new Integer(19), COLOR_FLOWER_C_PEDALS);
		colors7.put(new Integer(20), COLOR_FLOWER_STEM);
		colors7.put(new Integer(21), COLOR_FLOWER_C_PEDALS);
		colors7.put(new Integer(27), COLOR_FLOWER_STEM);
		colors7.put(new Integer(38), COLOR_FLOWER_E_PEDALS);
		colors7.put(new Integer(39), COLOR_FLOWER_STEM);
		colors7.put(new Integer(40), COLOR_FLOWER_E_PEDALS);
		colors7.put(new Integer(44), COLOR_FLOWER_STEM);
		colors7.put(new Integer(54), COLOR_FLOWER_G_PEDALS);
		colors7.put(new Integer(55), COLOR_FLOWER_G_CENTER);
		colors7.put(new Integer(56), COLOR_FLOWER_G_PEDALS);
		colors7.put(new Integer(62), COLOR_FLOWER_STEM);
		// line 8
		final Map<Integer, String> colors8 = new LinkedHashMap<Integer, String>();
		colors8.put(new Integer(3),  COLOR_FLOWER_STEM);
		colors8.put(new Integer(4),  COLOR_FLOWER_A_CENTER);
		colors8.put(new Integer(5),  COLOR_FLOWER_STEM);
		// line 9
		final Map<Integer, String> colors9 = new LinkedHashMap<Integer, String>();
		colors9.put(new Integer(2),  COLOR_FLOWER_STEM);
		// line 10
		final Map<Integer, String> colors10 = new LinkedHashMap<Integer, String>();
		colors10.put(new Integer(1),  COLOR_GRASS);
		// line 11
		final Map<Integer, String> colors11 = new LinkedHashMap<Integer, String>();
		colors11.put(new Integer(1),  COLOR_GRASS);
		// build art
		final String version = utilsString.padCenter(15, this.mvnprops.version, ' ');
		final PrintStream out = AnsiConsole.out;
		out.println();
		this.displayLogoLine(out, colors1, "      PoiXson                                                    ");
		this.displayLogoLine(out, colors2, "   ©GROWCONTROL           ,`--',                                 ");
		this.displayLogoLine(out, colors3, "        Lab              . _\\/_ .               _                ");
		this.displayLogoLine(out, colors4, "  "+version+".\\|/.   `. /\\ .'   .\\|/.     _(_)_              ");
		this.displayLogoLine(out, colors5, "                 -(:)-     \"||\"     -(:)-    (_)@(_)       .\\V/, ");
		this.displayLogoLine(out, colors6, "         \\V/,    \"/|\\\"      || /\\   \"/|\\\"    ./(_)         \\`V'/ ");
		this.displayLogoLine(out, colors7, " .vVv.   `V'      '|'     /\\||//\\)   '|'   |\\| ,     `@'     |   ");
		this.displayLogoLine(out, colors8, "  \\#/    ~|/    .\\ | ,/  (/\\||/    .\\ | ,   \\|/|     \\|/   . |/, ");
		this.displayLogoLine(out, colors9, " \\\\|//  \\\\|//   /-\\|/_\\     ||     /-\\|/_\\   |/     \\\\|/,  \\\\|// ");
		this.displayLogoLine(out, colors10,"^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^"            );
		this.displayLogoLine(out, colors11,"/////////////////////////////////////////////////////////////////"            );
		out.println();
		out.println(" Copyright (C) 2007-2015 PoiXson, Mattsoft");
		out.println(" - Brainchild of the one known as lorenzo -");
		out.println(" This program comes with absolutely no warranty. This is free software");
		out.println(" and you are welcome to modify it or redistribute it under certain");
		out.println(" conditions. Type 'show license' for license details.");
		out.println();
		out.flush();
	}

//   |   A      B        C        D         E       F       G      H   |
// 1 |      PoiXson                                                    |
// 2 |   ©GROWCONTROL           ,`--',                                 |
// 3 |        Lab              . _\/_ .               _                |
// 4 |  <---version--->.\|/.   `. /\ .'   .\|/.     _(_)_              |
// 5 |                 -(:)-     "||"     -(:)-    (_)@(_)       .\V/, |
// 6 |         \V/,    "/|\"      || /\   "/|\"    ./(_)         \`V'/ |
// 7 | .vVv.   `V'      '|'     /\||//\)   '|'   |\| ,     `@'     |   |
// 8 |  \#/    ~|/    .\ | ,/  (/\||/    .\ | ,   \|/|     \|/   . |/, |
// 9 | \\|//  \\|//   /-\|/_\     ||     /-\|/_\   |/     \\|/,  \\|// |
//10 |^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^/^|
//11 |/////////////////////////////////////////////////////////////////|
//   0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 |
//   0         1         2         3         4         5         6     |



}
