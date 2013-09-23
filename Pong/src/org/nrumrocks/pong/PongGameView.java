package org.nrumrocks.pong;

import java.util.List;

import jgame.Context;
import jgame.GContainer;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.SoundManager;
import jgame.controller.ControlScheme;
import jgame.listener.FrameListener;

public class PongGameView extends GContainer {
	
	
	public PongGameView () {
		
		super(new GSprite(ImageCache.forClass(Pong.class).get("tenniscourt.png")));
		// Set the size.
	    setSize(640, 480);

	    // Create a paddle to add.
	    PongPaddle paddle = new PongPaddle(ControlScheme.WASD);

	    // Add the paddle to the game view.
	    add(paddle);
	    
	 // Set the paddle's location.
	    paddle.setLocation(50, 480 / 2);
	    
	 // Create a puck.
		PongPuck puck = new PongPuck();

		// Add the puck.
		addAtCenter(puck);

		// Center the puck.
		snapChild(puck);
		
		// Create another paddle to add.
		PongPaddle paddle2 = new PongPaddle(ControlScheme.IJKL);

		// Add the paddle to the game viikkwew.
		add(paddle2);

		// Set the paddle's location.
		paddle2.setLocation(640 - 50, 480 / 2);
	    
		FrameListener fl = new FrameListener() {

		    @Override
		    public void invoke(GObject target, Context context) {
		        // Get all the pucks.
		        List<PongPuck> pucks = context.getInstancesOfClass(PongPuck.class);

		        // Is it empty?
		        boolean noPucksLeft = pucks.isEmpty();
		        
		     // Set the current game view.
		        if (noPucksLeft) {
		            // Play the sound.
		            SoundManager.forClass(Pong.class).play("dead.wav");

		            // Show the game over screen.
		            context.setCurrentGameView(Pong.View.GAME_OVER);
		        }
		    }
		};
		
		addListener(fl);
	    
	}

}