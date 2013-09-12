package org.nrumrocks.pong;

import jgame.ImageCache;
import java.awt.Image;
import jgame.GSprite;


public class PongPaddle extends GSprite {
	{
		
		ImageCache cache = ImageCache.forClass(Pong.class);
		Image paddle = cache.get("paddle.png");
		super(paddle);
}
}