package nine_ten;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class SlideShow extends Presenter {

	private JComponent pictureComponent = null;
	//Instance Variables
	private BufferedImage[] images;
	private String[] text;
	private int currentIndex = -1;

	public SlideShow(File[] imageFiles, String[] texts) {
		// read and remember (create instance variables ) images from the indicated files
		BufferedImage[] pictures = new BufferedImage[imageFiles.length];
		String[] description = new String[texts.length];
		for (int i = 0; i < imageFiles.length; i++) {
			try {
				pictures[i] = ImageIO.read(imageFiles[i]);
				if(texts[i] != null){
					description[i] = texts[i];
				}else
					description[i] = null;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.images = pictures;
		// and also remember texts in an instance field
		this.text = description;
	}

	@Override
	public JComponent createCenterComponent() {
		// create picture Component
		// you may want to create a class for that
		// (class PictureComponent extends JComponent { ... } )
		BufferedImage currentImage = null;
		JTextArea description = null;
		try {
			currentImage = ImageIO.read(new File("pic3.jpg"));
			description = new JTextArea("Default Description");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (images != null && currentIndex < images.length) {
			currentImage = images[currentIndex];
		}

		if (text != null && currentIndex < text.length) {
			//description = new JTextArea(text[currentIndex]);
			assert description != null;
			showText(text[currentIndex],description);
		}

		if (currentImage != null){
			pictureComponent = new PictureComponent(currentImage);
		}

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(pictureComponent, BorderLayout.CENTER);

		if (description != null){
			panel.add(description, BorderLayout.SOUTH);
		}
		return panel;
	}

	@Override
	public void eastButtonPressed() {
		currentIndex++;
		currentIndex = currentIndex % images.length;

		// make pictureComponent display the next picture
		//BufferedImage currentImage = images[currentIndex];
		//pictureComponent = new PictureComponent(currentImage);

		//call showText(...) to update the text associated with picture
		//showText(text[currentIndex]);
		System.out.println("East Button Pressed");
	}

	@Override
	public void westButtonPressed() {
		currentIndex--;
		if ( currentIndex < 0){
			currentIndex = images.length - 1;
		}

		// make pictureComponent display the next picture
		//BufferedImage currentImage = images[currentIndex];
		//pictureComponent = new PictureComponent(currentImage);

		//call showText(...) to update the text associated with picture
		//showText(text[currentIndex]);
		System.out.println("West Button Pressed");
	}

	@Override
	public void southButtonPressed() {
		System.out.println("South Button Pressed");
	}

	@Override
	public void northButtonPressed() {
		System.out.println("North Button Pressed");
	}

	public class PictureComponent extends JComponent {
		private BufferedImage picture;

		public PictureComponent(BufferedImage picture ){
			this.picture = picture;
		}

		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			int x = (getWidth() - picture.getWidth()) / 2;
			int y = (getHeight() - picture.getHeight()) / 2;
			g.drawImage(picture, x, y, null);
		}
	}

	public static void main(String[] args) {
		SlideShow app = new SlideShow(
				new File[] { new File("pic1.jpg"), new File("pic2.jpg")},
				new String[] {"Description 1", "Description 2"});
	}
}
