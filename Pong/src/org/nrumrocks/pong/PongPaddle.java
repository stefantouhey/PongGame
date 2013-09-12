package org.nrumrocks.pong;

import jgame.ImageCache;
import jgame.GSprite;


public class PongPaddle extends GSprite {
	
	public PongPaddle () {
		super(ImageCache.forClass(Pong.class).get("paddle.png"));
	}
	
}