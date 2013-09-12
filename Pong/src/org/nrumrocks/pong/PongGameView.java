package org.nrumrocks.pong;

import jgame.GContainer;

public class PongGameView extends GContainer {
	
	public PongGameView () {
	    // Set the size.
	    setSize(640, 480);

	    // Create and add a paddle.
	    add(new PongPaddle());
	}

}