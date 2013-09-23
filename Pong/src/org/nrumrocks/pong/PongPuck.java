package org.nrumrocks.pong;

import java.util.List;

import jgame.Context;
import jgame.GObject;
import jgame.ImageCache;
import jgame.GSprite;
import jgame.controller.ConstantMovementController;
import jgame.listener.BoundaryRemovalListener;
import jgame.listener.FrameListener;
import jgame.listener.HitTestListener;
import jgame.listener.ParentBoundsListener;


public class PongPuck extends GSprite {
	
	private ConstantMovementController cmc;
	
    public PongPuck () {
        super(ImageCache.forClass(Pong.class).get("tennisball.png"));
        cmc = new ConstantMovementController(-5, 0);
        addController(cmc);
        
     

     addListener(new BoundaryRemovalListener());
     
     
     
     HitTestListener htl = new HitTestListener(PongPaddle.class) {
    	
    	    @Override
    	    public void invoke(GObject target, Context context) {
    	    	// Flip the direction of the puck.
    	        flip();

    	        // Get a list of all paddles hit.
    	        List<PongPaddle> paddlesHit = context.hitTestClass(PongPaddle.class);

    	        // Get the relevant paddle.
    	        PongPaddle paddle = paddlesHit.get(0);

    	        // Get the vertical distance between the centers.
    	        double offset = getY() - paddle.getY();

    	        // Move vertically.
    	    	 cmc.setVelocityY(cmc.getVelocityY()
    	    		        + (offset / (paddle.getHeight() / 2)) * 7);
    	    	 
    	    	 
    	    	// Set the primitive shape to a circle.
    	     	setPrimitive(PrimitiveShape.CIRCLE);
    	        
    	    }
    	};
    	
    	// Create the bounce listener.
    	ParentBoundsListener bounce = new ParentBoundsListener() {
    	    @Override
    	    public void invoke(GObject target, Context context) {
    	        cmc.setVelocityY(-cmc.getVelocityY());
    	    }
    	};
    	
    	// Create an acceleration listener.
    	FrameListener accelerate = new FrameListener() {
    	    @Override
    	    public void invoke(GObject target, Context context) {
    	    	// Get the current velocity.
    	    	double vx = cmc.getVelocityX();

    	    	// Test the sign.
    	    	if (vx > 0) {
    	    	    vx += 0.01;
    	    	} else if (vx < 0) {
    	    	    vx -= 0.01;
    	    	} else {
    	    	    // It's zero; do nothing.
    	    	}

    	    	// Set the velocity.
    	    	cmc.setVelocityX(vx);
    	    }
    	};
    	
    	// Add the listener.
    	addListener(accelerate);

    	// Only bounce vertically.
    	bounce.setValidateHorizontal(false);

    	// Add the bounce listener.
    	addListener(bounce);

    	// Add the listener.
    	addListener(htl);
    	
    	
    	
    	
    }
    
    public void flip() {
        // Get the current x velocity.
        double vx = cmc.getVelocityX();

        // Reverse the direction.
        vx = -vx;

        // Set the x velocity to the new value.
        cmc.setVelocityX(vx);
        
        
    }

}