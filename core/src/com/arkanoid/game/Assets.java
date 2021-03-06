/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.arkanoid.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {	
	public static Texture background;
	public static TextureRegion backgroundRegion;

	public static Texture items;
	public static TextureRegion mainMenu;
	public static TextureRegion pauseMenu;
	public static TextureRegion pauseResumeMenu;
	public static TextureRegion pauseQuitMenu;
	public static TextureRegion ready;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;
	
	public static Texture ball;
	public static Texture bonus;
	public static Texture brick;
	public static Texture vaus;
	
	public static TextureRegion defaultBall;
	public static TextureRegion bonusAddLife;
	public static TextureRegion defaultBrick;
	public static TextureRegion defaultVaus;	

	public static BitmapFont font;

	public static Music music;
	
	public static Sound reboundSound;
	public static Sound vausSound;
	
	public static Sound balloon0Sound;
	public static Sound balloonSound;
	
	public static Sound end0Sound;
	public static Sound endSound;	
	
	public static Sound fail0Sound;
	public static Sound failSound;
	
	public static Sound menu0Sound;
	public static Sound menuSound;
	
	public static Sound pauseSound;

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load() {
		ball = loadTexture("data/balls/defaultBall.png");
		bonus = loadTexture("data/bonuses/bonusAddLife.png");
		brick = loadTexture("data/bricks/defaultBrick.png");
		vaus = loadTexture("data/vauses/defaultVaus.png");
		
		defaultBall = new TextureRegion(ball, 0, 0, 16, 16);
		bonusAddLife = new TextureRegion(bonus, 0, 0, 57, 25);
		defaultBrick = new TextureRegion(brick, 0, 0, 57, 25);
		defaultVaus = new TextureRegion(vaus, 0, 0, 100, 22);
		
		background = loadTexture("data/background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);

		items = loadTexture("data/items.png");

		mainMenu = new TextureRegion(items, 0, 224, 300, 110);
		
		pauseResumeMenu = new TextureRegion(items, 224, 128, 192, 48);
		pauseQuitMenu = new TextureRegion(items, 224, 176, 192, 48);
		
		ready = new TextureRegion(items, 320, 224, 192, 32);
		gameOver = new TextureRegion(items, 352, 256, 160, 96);
		highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
		logo = new TextureRegion(items, 0, 352, 274, 142);
		soundOff = new TextureRegion(items, 0, 0, 64, 64);
		soundOn = new TextureRegion(items, 64, 0, 64, 64);
		arrow = new TextureRegion(items, 0, 64, 64, 64);
		pause = new TextureRegion(items, 64, 64, 64, 64);
		
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);

		music = Gdx.audio.newMusic(Gdx.files.internal("data/sounds/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		//if (Settings.soundEnabled) music.play();
		
		reboundSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/rebound.wav"));
		vausSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/vaus.wav"));
		balloon0Sound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/balloon0.wav"));
		balloonSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/balloon.wav"));
		
		end0Sound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/end0.wav"));
		endSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/end.wav"));		
		
		fail0Sound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/fail0.wav"));
		failSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/fail.wav"));
		
		menu0Sound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/menu0.wav"));
		menuSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/menu.wav"));
		
		pauseSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pause.wav"));
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled) sound.play(1);
	}
}
