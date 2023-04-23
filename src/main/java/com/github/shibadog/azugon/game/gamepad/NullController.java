package com.github.shibadog.azugon.game.gamepad;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.EventQueue;
import net.java.games.input.Rumbler;

public class NullController implements Controller {
    public static final NullController INSTANCE = new NullController();

	@Override
	public Component getComponent(Identifier id) {
		return null;
	}

	@Override
	public Component[] getComponents() {
		return new Component[0];
	}

	@Override
	public Controller[] getControllers() {
		return new Controller[0];
	}

	@Override
	public EventQueue getEventQueue() {
		return null;
	}

	@Override
	public String getName() {
		return "NullController";
	}

	@Override
	public int getPortNumber() {
		return -1;
	}

	@Override
	public PortType getPortType() {
		return Controller.PortType.UNKNOWN;
	}

	@Override
	public Rumbler[] getRumblers() {
		return new Rumbler[0];
	}

	@Override
	public Type getType() {
		return Controller.Type.UNKNOWN;
	}

	@Override
	public boolean poll() {
		return false;
	}

	@Override
	public void setEventQueueSize(int size) {
		// nothing
	}
}
