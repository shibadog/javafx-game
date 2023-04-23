package com.github.shibadog.azugon.game.gamepad;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.EventQueue;
import net.java.games.input.Rumbler;

public class NullController implements Controller {
    public static final NullController INSTANCE = new NullController();

	@Override
	public Component getComponent(Identifier arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getComponent'");
	}

	@Override
	public Component[] getComponents() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getComponents'");
	}

	@Override
	public Controller[] getControllers() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getControllers'");
	}

	@Override
	public EventQueue getEventQueue() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getEventQueue'");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getName'");
	}

	@Override
	public int getPortNumber() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPortNumber'");
	}

	@Override
	public PortType getPortType() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPortType'");
	}

	@Override
	public Rumbler[] getRumblers() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getRumblers'");
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getType'");
	}

	@Override
	public boolean poll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'poll'");
	}

	@Override
	public void setEventQueueSize(int arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setEventQueueSize'");
	}
}
